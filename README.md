# E-Learning Platform

## Software Requirements Specification (SRS)

**Document Version:** 1.0
**Project Type:** Web Application
**Backend:** Java + Spring Boot
**Frontend:** Modern Web Framework (Angular / React / Vue)
**Database:** MySQL
**Primary Users:** Instructors, Students

---

# 1. Introduction

## 1.1 Purpose

The purpose of this project is to develop a web-based **E-Learning Platform** that allows instructors to create and manage online courses while enabling students to browse available courses, enroll in courses offered by specific instructors, and access course sessions containing educational video content.

The backend will be implemented using Spring Boot, following a layered architecture and RESTful API design. The frontend will be implemented using a modern frontend framework such as Angular or React.

## 1.2 Project Objectives

The main objectives are:

* Allow instructors to create and manage courses.
* Allow instructors to add educational sessions to their courses.
* Allow instructors to upload video content for sessions.
* Allow instructors to delete their courses.
* Allow students to browse available courses.
* Allow students to enroll in courses offered by a specific instructor.
* Allow students to view their enrolled courses.
* Allow students to access course sessions.
* Allow students to watch session videos.
* Maintain a clear relationship between students, instructors, courses, enrollments, and sessions.
* Provide a secure REST API that can be consumed by the frontend application.

---

# 2. Scope

## 2.1 In Scope

The first version of the system will include:

### Authentication & Authorization

* User registration.
* User login.
* JWT-based authentication.
* Role-based authorization.
* Two user roles:

  * Student
  * Instructor

### Instructor Features

* Login.
* View profile.
* Create courses.
* Update courses.
* Delete courses.
* View owned courses.
* Add sessions to courses.
* Update sessions.
* Delete sessions.
* Upload session videos.
* Manage course information.

### Student Features

* Login.
* View available courses.
* View course details.
* View instructor information.
* Enroll in a course.
* View enrolled courses.
* Access course sessions.
* Watch session videos.

### Course Management

Each course will contain:

* Course title.
* Course description.
* Course thumbnail.
* Instructor.
* Creation date.
* Update date.
* Course status.

### Session Management

Each course may contain multiple sessions.

Each session will contain:

* Session title.
* Session description.
* Video.
* Creation date.

---

# 3. User Roles

The system contains two primary roles.

## 3.1 Instructor

An instructor is responsible for creating and managing educational content.

An instructor can:

* Register/login.
* Create courses.
* Update their courses.
* Delete their courses.
* View their courses.
* Add sessions.
* Update sessions.
* Delete sessions.
* Upload session videos.
* View enrolled students for their courses.

An instructor cannot modify courses belonging to another instructor.

## 3.2 Student

A student is a user who consumes educational content.

A student can:

* Register/login.
* Browse courses.
* Search for courses.
* View course details.
* View instructor information.
* Enroll in a course.
* View enrolled courses.
* Open an enrolled course.
* View course sessions.
* Watch available session videos.

A student cannot:

* Create courses.
* Modify courses.
* Delete courses.
* Modify sessions.
* Upload educational content.

---

# 4. Functional Requirements

## FR-01: User Registration

The system shall allow users to create an account.

The registration form shall contain at minimum:

* Full name.
* Email.
* Password.
* Role.

The system shall validate:

* Email format.
* Email uniqueness.
* Password requirements.
* Required fields.

Passwords shall never be stored as plain text.

Passwords must be securely hashed before being stored in the database.

---

# 5. Authentication

## FR-02: User Login

Users shall be able to authenticate using:

* Email
* Password

Upon successful authentication, the backend shall generate a JWT containing the authenticated user's identity and role.

Example:

```http
POST /api/auth/login
```

Example response:

```json
{
  "token": "...",
  "user": {
    "id": 15,
    "name": "Ahmed Ali",
    "email": "ahmed@example.com",
    "role": "STUDENT"
  }
}
```

---

# 6. Course Management

## FR-03: Create Course

Only authenticated instructors shall be able to create courses.

Example:

```http
POST /api/instructors/courses
```

Course information shall include:

* Title.
* Description.
* Thumbnail.
* Status.

The authenticated instructor shall automatically become the owner of the course.

The frontend shall not be allowed to specify another instructor's ID as the course owner.

## FR-04: View Instructor Courses

An instructor shall be able to retrieve all courses owned by them.

```http
GET /api/instructors/courses
```

The API shall return only courses belonging to the authenticated instructor.

## FR-05: Update Course

An instructor shall be able to update their own courses.

```http
PUT /api/instructors/courses/{courseId}
```

The system shall verify that the authenticated user owns the requested course.

If the course belongs to another instructor, the request shall be rejected.

## FR-06: Delete Course

An instructor shall be able to delete their own courses.

```http
DELETE /api/instructors/courses/{courseId}
```

When a course is deleted, its related sessions and enrollments shall also be handled according to the application's deletion policy.

### Recommended Behavior

Deleting the course should remove or deactivate its dependent records to prevent orphaned data.

---

# 7. Course Discovery

## FR-07: Browse Courses

Students shall be able to retrieve available courses.

The response should contain:

* Course ID.
* Title.
* Description.
* Thumbnail.
* Instructor name.
* Number of sessions.
* Creation date.

The API should support pagination.

Example:

```http
GET /api/courses?page=0&size=10
```

---

# 8. Course Details

## FR-08: View Course Details

A user shall be able to retrieve detailed information about a course.

The response shall contain:

* Course information.
* Instructor information.
* Session count.
* Course status.

For students who are enrolled in the course, the response may additionally contain enrollment information.

---

# 9. Enrollment

## FR-09: Student Enrollment

Authenticated students shall be able to enroll in a course.

Example:

```http
POST /api/courses/{courseId}/enroll
```

The system shall automatically identify the student from the authenticated JWT.

The frontend shall not send the student ID as the source of truth.

---

# 10. Enrollment Rules

The system shall enforce the following rules:

### Rule 1

A student cannot enroll in a course more than once.

### Rule 2

Only students can enroll.

### Rule 3

An unauthenticated user cannot enroll.

### Rule 4

A student cannot enroll in a deleted or inactive course.

### Rule 5

Enrollment must be associated with the selected course.

### Rule 6

The same student may enroll in multiple courses.

---

# 11. My Courses

## FR-10: Student Enrolled Courses

Students shall be able to retrieve all courses in which they are enrolled.

Example:

```http
GET /api/students/me/courses
```

The system shall determine the student from the authenticated JWT.

---

# 12. Session Management

## FR-11: Create Session

An instructor shall be able to create sessions for their own courses.

Example:

```http
POST /api/instructors/courses/{courseId}/sessions
```

A session shall contain:

* Title.
* Description.
* Video.
* Order.

Example:

```json
{
  "title": "Introduction to Spring Boot",
  "description": "Understanding Spring Boot and its architecture.",
  "order": 1
}
```

---

# 13. Session Ordering

Sessions shall have an explicit order.

Example:

```text
Course: Spring Boot Fundamentals

1. Introduction
2. Spring Core
3. Dependency Injection
4. Spring MVC
5. Spring Data JPA
6. Spring Security
```

The system shall return sessions ordered by their `order` value.

---

# 14. Session Video

## FR-12: Upload Session Video

Instructors shall be able to upload a video associated with a session.

The backend shall validate:

* File type.
* File size.
* User authorization.

A production implementation should use external/object storage.

The actual video file should not be stored directly inside the relational database.

The database should store the video's URL/path and related metadata.

Example:

```text
Session
 ├── id
 ├── title
 ├── description
 ├── videoUrl
 └── displayOrder
```

---

# 15. Student Session Access

## FR-13: Access Course Sessions

A student shall only be able to access sessions belonging to courses in which the student is enrolled.

The relationship is:

```text
Student
   ↓
Enrollment
   ↓
Course
   ↓
Session
   ↓
Video
```

If the student is not enrolled, the backend shall reject access to protected course content.

Recommended response:

```http
403 Forbidden
```

---

# 16. Instructor Authorization

The backend shall verify ownership before allowing instructors to modify resources.

For example:

```text
Instructor A
    ↓
Course 10
```

Instructor A can modify Course 10.

If Instructor B attempts:

```http
PUT /api/instructors/courses/10
```

the backend must reject the operation.

Authorization must be implemented on the backend and must not rely solely on frontend restrictions.

---

# 17. Security Requirements

The application shall use **Spring Security**.

Security requirements include:

* JWT authentication.
* Password hashing using BCrypt.
* Role-based authorization.
* Stateless authentication.
* Protected instructor endpoints.
* Protected student endpoints.
* Ownership validation.
* Enrollment validation.

Example:

```text
ROLE_INSTRUCTOR
      ↓
Create / Update / Delete Course
```

```text
ROLE_STUDENT
      ↓
Enroll / Access Enrolled Courses
```

---

# 18. Non-Functional Requirements

## NFR-01: Performance

The backend should provide normal API responses within an acceptable response time under normal system load.

Large video files must not be unnecessarily loaded into memory.

## NFR-02: Security

The system shall:

* Hash passwords.
* Protect authenticated endpoints.
* Validate JWT tokens.
* Enforce user roles.
* Validate resource ownership.
* Validate uploaded files.
* Prevent unauthorized course access.

---

# 19. Business Rules

The following business rules shall apply.

### BR-01

An instructor can own multiple courses.

### BR-02

A course belongs to exactly one instructor.

### BR-03

A course can contain zero or more sessions.

### BR-04

A session belongs to exactly one course.

### BR-05

A student can enroll in multiple courses.

### BR-06

A course can have multiple enrolled students.

### BR-07

A student cannot enroll in the same course more than once.

### BR-08

Only the course owner can modify or delete the course.

### BR-09

Only the course owner can modify its sessions.

### BR-10

Only enrolled students can access protected course sessions.

### BR-11

An instructor cannot enroll as a student unless the system explicitly supports both roles.

### BR-12

Deleting a course must not leave orphaned sessions or enrollments.

---

# 20. Future Enhancements

The architecture should allow future features without requiring major restructuring.

Possible future versions may include:

## Course Reviews

Students can rate courses.

## Progress Tracking

The system can track completed sessions.

## Certificates

Students can receive certificates after completing a course.

## Course Categories

Possible categories include:

* Programming
* Database
* DevOps
* Mobile Development
* Software Engineering

## Search & Filtering

Students can search by:

* Course title.
* Instructor.
* Category.
* Level.

## Notifications

Students can receive notifications when an instructor adds a new session.

## Favorites

Students can save courses for later.

## Payments

Paid courses can be introduced in a future version.

## Admin Role

A future administrative role can manage:

* Users.
* Courses.
* Reports.
* Categories.
* Platform settings.

---

# 21. Acceptance Criteria

The first release shall be considered complete when:

1. A user can register as an instructor.
2. An instructor can log in.
3. An instructor can create a course.
4. An instructor can update their course.
5. An instructor can delete their course.
6. An instructor can create multiple sessions.
7. An instructor can upload session videos.
8. A user can register as a student.
9. A student can log in.
10. A student can browse courses.
11. A student can view course details.
12. A student can enroll in a course.
13. A student can view their enrolled courses.
14. A student can access sessions of enrolled courses.
15. A student cannot access protected content from courses they haven't enrolled in.
16. An instructor cannot modify another instructor's course.
17. Students cannot create or delete courses.
18. JWT authentication works correctly.
19. Role-based authorization works correctly.
20. API errors are returned using a consistent response structure.
