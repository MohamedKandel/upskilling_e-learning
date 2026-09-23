# E-Learning Platform — Implementation Plan & Security Checklist

**Status of review:** 2026-09-19 — Domain models + DB + Flyway done. Everything else pending.
**App location:** `E-Learning/` (Spring Boot 4.1.1, Java 21, Oracle, Flyway, MapStruct, springdoc).

---

## 1. What Already Exists (review results)

| Area | Status | Notes |
|---|---|---|
| README/SRS | Done | Describes full scope; DB says MySQL but code uses **Oracle** (deliberate change — confirm with team). |
| Maven project | Done | Boot 4.1.1, Java 21, springdoc, MapStruct (beta), Flyway + Oracle driver. Offline `compile` passes. |
| DB schema (Flyway V1) | Done | `SYS_ROLE`, `SYS_STATUS`, `SYS_USER`, `SYS_COURSE`, `SYS_SESSION`, `SYS_ENROLLMENT`. Uses `RAW(16)` (UUID), unique email, unique `(USER_ID, COURSE_ID)`. |
| Entities | Done | `User`, `Role`, `Course`, `Session`, `Status`. |
| **Everything else** | Missing | Security, repositories, services, controllers, DTOs/mappers, exception handling, seed data, tests. |

## 2. Issues Found in Existing Code (should fix before building on top)

1. **Enrollment is modeled as a `@ManyToMany` join table** (`User.java:50-56`, `Course.java:47-48`), but the SRS treats enrollment as a meaningful relation (FR-08 wants enrollment *info* in course details, FR-13 uses Enrollment as a hop, BR-12 wants orphan-free deletion). The `SYS_ENROLLMENT` table has its own PK + unique constraint, so model it as a real **`Enrollment` entity** (with `enrolledAt` date) and two one-to-manys instead of the join table. This also makes BR-12 deletion policy explicit.
2. **No seed data.** `SYS_ROLE` and `SYS_STATUS` need rows (STUDENT, INSTRUCTOR; PUBLISHED/DRAFT or ACTIVE/INACTIVE). Add `V2__Seed_Data.sql`. Without it the app has no roles/statuses to reference.
3. **Missing JPA validation / constraints in entities** (unique email is DB-only). Rely on DB as backstop but add Bean Validation (`@Email`, `@NotBlank`, `size`) for clean API errors.
4. **Missing security + validation dependencies** in `pom.xml` (see section 6).
5. **Minor code hygiene:** `Session.java:5` stray unused import `org.springframework.cglib.core.Local`; `Course.getDESCRIPTION()` should be `getDescription()`; `User` has no getters for its `course`/`courses` collections (MapStruct/DTO mapping will need them); `createdAT` vs `createdAt` naming inconsistent.
6. **MapStruct 1.7.0.Beta2** — consider the latest stable (`1.6.x`) to avoid beta surprises with annotation processing.
7. **DB vs SRS mismatch:** SRS says MySQL, project uses Oracle. Confirm Oracle is the accepted decision (it changes JPA subtleties, e.g. `GenerationType.UUID`, Flyway dialect module).

## 3. Recommended Roadmap

### Phase 0 — Fix domain & DB foundations (small)
- Create `Enrollment` entity; replace `@ManyToMany` in `User`/`Course` with explicit one-to-many relations.
- Add `V2__Seed_Data.sql` (roles + statuses).
- Clean up entity lint (getters/setters, naming, stray imports).
- Add `spring-boot-starter-validation`.
- Verify `mvn clean compile` and app boot.

### Phase 1 — Security foundation + Auth
- Add `spring-boot-starter-security`, `jjwt` (api/impl/jackson), `spring-security-test`.
- `SecurityConfig`: stateless, CSRF off, CORS for frontend origin, route rules.
- `JwtService` (issue/parse/validate token) + `JwtAuthenticationFilter` (OncePerRequestFilter, builds Authentication from token).
- `CustomUserDetailsService` + `BCryptPasswordEncoder`.
- `ApiResponse` wrapper (`code`, `message`, `data`) + `@RestControllerAdvice` for consistent errors.
- `POST /api/auth/register`, `POST /api/auth/login`.
- JWT secret/exps via env vars (`JWT_SECRET`, `JWT_EXPIRATION`).

### Phase 2 — Repositories + Services
- `UserRepository`, `RoleRepository`, `StatusRepository`, `CourseRepository`, `SessionRepository`, `EnrollmentRepository`.
- Service layer: AuthService, CourseService, SessionService, EnrollmentService, VideoService. Enforce ownership & enrollment rules here (see section 5).

### Phase 3 — Instructor APIs
- `POST/GET/PUT/DELETE /api/instructors/courses` (+ session endpoints under courses).
- Ownership check on every mutating operation.

### Phase 4 — Student / public APIs
- `GET /api/courses` (paginated browse, FR-07), `GET /api/courses/{id}` (details, FR-08).
- `POST /api/courses/{courseId}/enroll` (FR-09), `GET /api/students/me/courses` (FR-10).
- Session access gated by enrollment (FR-13, 403 if not enrolled).

### Phase 5 — Video upload
- `POST /api/instructors/courses/{courseId}/sessions/{sessionId}/video`.
- Store file on disk (dev) / object storage (prod); DB keeps `VIDEO_URL` only.
- Validate content type, size, extension; never trust the original filename.

### Phase 6 — Tests & acceptance
- Unit tests for services; MockMvc for controllers; security tests (`@WithMockUser`, 401/403 cases).
- Map every SRS acceptance criterion (items 1–20) to a test or manual check.
- Optional: endpoint integration tests against the test DB.

## 4. Endpoint Map (maps directly to SRS)

| SRS | Method + Path | Role | Notes |
|---|---|---|---|
| FR-01/02 | `POST /api/auth/register`, `POST /api/auth/login` | public | JWT issued on login |
| FR-03 | `POST /api/instructors/courses` | INSTRUCTOR | owner = JWT user |
| FR-04 | `GET /api/instructors/courses` | INSTRUCTOR | only own courses |
| FR-05 | `PUT /api/instructors/courses/{courseId}` | INSTRUCTOR | ownership check |
| FR-06 | `DELETE /api/instructors/courses/{courseId}` | INSTRUCTOR | cascade/soft-delete policy |
| FR-07 | `GET /api/courses?page=&size=` | public | paginated browse |
| FR-08 | `GET /api/courses/{courseId}` | public/STUDENT | + enrollment info if enrolled |
| FR-09 | `POST /api/courses/{courseId}/enroll` | STUDENT | student taken from JWT |
| FR-10 | `GET /api/students/me/courses` | STUDENT | from JWT |
| FR-11/12 | `POST .../courses/{courseId}/sessions`, `.../video` | INSTRUCTOR | ownership check first |
| FR-13 | `GET .../courses/{courseId}/sessions` (or session detail) | STUDENT | enrollment gate → 403 |

## 5. Security Considerations (the important part)

### 5.1 Authentication flow
- Register → validate input → check email uniqueness → **BCrypt-hash password** → store.
- Login → load user by email → `passwordEncoder.matches(raw, hash)` → issue JWT.
- JWT claims: `sub` = user UUID, `email`, `role` (stored with `ROLE_` prefix, e.g. `ROLE_STUDENT`), `iat`, `exp`. Do **not** put the password in the token.
- Do not store secrets in `application.properties` source; read from env vars (`${JWT_SECRET}`). `application.properties` is currently committed — add `JWT_SECRET`, DB creds override, and upload root to env vars.

### 5.2 JWT filter
- `OncePerRequestFilter`: read `Authorization: Bearer <token>` → parse & validate (signature, expiry, issuer) → build `UsernamePasswordAuthenticationToken` with the role authority → set `SecurityContextHolder`.
- On missing/invalid/expired token **don't throw inside the filter**; leave the request anonymous so Spring Security returns a clean 401 via `AuthenticationEntryPoint`.

### 5.3 Authorization rules (route level)
- Public: `/api/auth/**`, `GET /api/courses/**`, swagger/OpenAPI, error path.
- `hasRole("INSTRUCTOR")`: all `/api/instructors/**`.
- `hasRole("STUDENT")`: `/api/students/me/**`, enroll, session access.
- Enforce that a route requiring a role is unreachable by the other role (e.g. student must NOT reach `/api/students/me/courses` only; also instructor must not enroll).

### 5.4 Horizontal authorization (ownership / IDOR — most important for this app)
- **Never** trust a client-supplied instructor ID or student ID as the source of truth. Always resolve from `Principal` (JWT `sub`).
- `Course` ops: `course.instructor.getUserId().equals(currentUserId)` else **403**.
- Session ops: resolve session → its course → check course ownership **before** any mutation.
- Enroll: student from JWT, NOT from request body.
- Session access: verify an `Enrollment(studentId, courseId)` row exists → else **403**.

### 5.5 Business rule enforcement (map to services, not just DB)
- BR-07 / Rule 1: duplicate enrollment → reject (also guarded by DB unique constraint).
- Rule 2 / BR-11: only STUDENT role may enroll (instructor can't).
- Rule 3: enrollment requires authentication (route is protected).
- Rule 4: no enrollment for deleted/inactive course.
- BR-08/BR-09/BR-10: ownership + enrollment checks as in 5.4.
- BR-12: deletion policy — pick one and implement consistently: **hard delete with cascades** (delete sessions + enrollments) or **soft delete** (status = INACTIVE, hide from browse, block new enrollments). Recommend soft-delete for courses as it keeps enrollments/analytics; enforce "cannot enroll in inactive course".

### 5.6 Spring Security configuration details
- `http.csrf(disable)` (stateless JWT API — CSRF not applicable).
- `http.cors(...)` — frontend origin must be allowed (env-configurable), otherwise the SPA can't call the API.
- `SessionCreationPolicy.STATELESS`.
- Custom `AuthenticationEntryPoint` (401) and `AccessDeniedHandler` (403) that return the **same `ApiResponse` JSON shape**, so API errors are consistent (acceptance item 20).
- Register the JWT filter before `UsernamePasswordAuthenticationFilter`.
- `PasswordEncoder` = `BCryptPasswordEncoder` (or Spring's `DelegatingPasswordEncoder` default which bcrypts).
- Disable swagger form-login/http-basic noise; swagger paths permitted.
- Add security headers defaults (`X-Content-Type-Options: nosniff`, etc.) — default on.

### 5.7 Input & output hardening
- Bean Validation on every DTO: `@NotBlank`, `@Email`, `@Size`, password min length (e.g. 8). Return 400 with the consistent structure.
- **Never serialize `password`** — MapStruct DTOs exclude it; double-check entity returned from controllers.
- Global `@RestControllerAdvice`: map `MethodArgumentNotValidException`, duplicate-key violation (email/unique), `EntityNotFoundException`, `AccessDeniedException`, `MaxUploadSizeExceededException` → structured JSON.
- Login error should be generic ("invalid credentials"), don't reveal whether the email exists.

### 5.8 Video upload security (FR-12)
- Limit size via `spring.servlet.multipart.max-file-size` + `max-request-size`.
- Whitelist MIME/extension: `video/mp4`, `video/webm`, `video/quicktime`, etc. Validate **content type from the upload stream**, not just the filename.
- Store with a generated UUID filename in a directory **outside** the deployable jar (`uploads/`), never the original name → prevents path traversal / stored-XSS via filename.
- DB stores only the `VIDEO_URL`.
- Serve uploaded files through a controlled endpoint with correct `Content-Type` (don't let Apache/Tomcat guess). For production, use object storage (S3) with pre-signed URLs.
- Memory: stream with `InputStream`/`MultipartFile.transferTo` rather than loading large files into memory (NFR-01).

### 5.9 Token lifecycle & extras (v1 scope)
- Short-lived access tokens (e.g. 15–60 min) save you from needing server-side revocation.
- Optional: refresh token endpoint later; don't over-build in v1.
- Optional hardening (note in backlog): rate limiting on login, `@PreAuthorize` on service methods, security audit logging.

## 6. New Maven Dependencies to Add
- `spring-boot-starter-security`
- `spring-boot-starter-validation`
- `io.jsonwebtoken:jjwt-api` / `jjwt-impl` / `jjwt-jackson` (0.12.x)
- `spring-security-test` (test)
- Optional: `commons-validator` (if you want stricter email checks)

## 7. Testing Strategy (maps to acceptance criteria 1–20)
- **Unit:** service-layer ownership/enrollment rules with mocks.
- **Web slice:** `@WebMvcTest` + MockMvc for each controller; security via `@WithMockUser(roles="INSTRUCTOR"/"STUDENT")`.
- **Security tests:** no token → 401; wrong role → 403; other instructor's course → 403; not-enrolled student on session → 403; duplicate enrollment → 409/400; register with duplicate email → 400.
- **Field checks:** password not present in any JSON response.
- **DB tests:** `@DataJpaTest` for repositories, unique constraints, seeding.

## 8. Definition of Done
- All 20 acceptance criteria verified (each flagged in a checklist doc or test).
- Consistent `ApiResponse` on success and error.
- No plaintext passwords, no password in responses/tokens.
- Ownership + enrollment enforced in service layer (not only URL rules).
- `mvn clean test` green; app boots against Oracle with Flyway clean migration.