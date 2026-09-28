# **Reviewer Feedback, implemented changes:**

To optimize database performance, prevent table-locking issues, and improve query response times, targeted indexes have been added to the database schema.

---
# NOTE1: Database Performance & Indexing Strategy

## 1. Summary of Indexes Created

| Table Name | Index Name | Indexed Column(s) | Primary Purpose |
| :--- | :--- | :--- | :--- |
| `SYS_COURSE` | `IDX_COURSE_USER_STATUS` | `(USER_ID, STATUS)` | Foreign key lock prevention & instructor status queries |
| `SYS_COURSE` | `IDX_COURSE_NAME` | `(COURSE_NAME)` | Fast catalog searches & alphabetical sorting |
| `SYS_SESSION` | `COURSE_SESSION_TITLE` *(Unique)* | `(COURSE_ID, SESSION_TITLE)` | Foreign key lock prevention & session uniqueness |
| `SYS_Enrollment` | `ENROLLMENT_PK` *(PK)* | `(USER_ID, COURSE_ID)` | Student lookup & uniqueness |
| `SYS_Enrollment` | `IDX_ENROLLMENT_COURSE_FK` | `(COURSE_ID)` | Foreign key lock prevention & course enrollment lookups |
| `SYS_Student` | `STUDENT_PK` *(PK)* | `(USER_ID)` | Entity integrity & 1:1 join lookups |
| `SYS_Instructor` | `INSTRUCTOR_PK` *(PK)* | `(USER_ID)` | Entity integrity & 1:1 join lookups |

---

## 2. Advantages & Technical Benefits

### A. Prevention of Full Table Locks on Foreign Keys (Crucial in Oracle)
* **Problem:** In Oracle, if a child table's Foreign Key column is **unindexed**, updates or deletes on the parent table (e.g., deleting a user from `SYS_USER`) trigger an exclusive **Full Table Lock** on the child table (`SYS_COURSE` or `SYS_Enrollment`). This freezes all read/write operations for other users.
* **Solution:** Creating composite and standalone indexes containing foreign key columns (`USER_ID`, `COURSE_ID`) prevents table locks and allows concurrent transactions to run smoothly.

### B. Query Performance Optimization ($O(N)$ to $O(\log N)$)
* **Without Indexes:** The database engine performs a **Full Table Scan (FTS)**—reading every block of data off the disk from start to finish to locate matching rows.
* **With B-Tree Indexes:** Oracle performs an **Index Range Scan** or **Index Unique Scan**, using a B-Tree hierarchy to jump directly to the target record in a fraction of a millisecond.

### C. Index Elimination & Storage Efficiency
* **Composite Leading Column Advantage:** Instead of creating redundant single-column indexes, composite indexes were chosen strategically:
    * `IDX_COURSE_USER_STATUS (USER_ID, STATUS)` handles both instructor queries (`WHERE USER_ID = :1 AND STATUS = :2`) **and** serves as the Foreign Key index for `USER_ID`.
    * `COURSE_SESSION_TITLE UNIQUE (COURSE_ID, SESSION_TITLE)` enforces uniqueness **and** satisfies the Foreign Key requirement for `SYS_SESSION(COURSE_ID)`.
* **Benefit:** Saves disk space, reduces memory consumption, and speeds up `INSERT`/`UPDATE` operations by eliminating useless duplicate indexes.

### D. Efficient Alphabetical Sorting (`ORDER BY`)
* **Benefit:** B-Tree indexes store values in pre-sorted order. Queries executing `SELECT * FROM SYS_COURSE ORDER BY COURSE_NAME` utilize `IDX_COURSE_NAME` directly, completely avoiding expensive CPU-intensive `SORT ORDER BY` operations in memory.

---

## 3. Recommended SQL DDL Execution Script

```sql
-- Composite index on SYS_COURSE (Covers FK lookups + Status filtering)
CREATE INDEX IDX_COURSE_USER_STATUS ON SYS_COURSE(USER_ID, STATUS);

-- B-Tree Index for course title searches and sorting
CREATE INDEX IDX_COURSE_NAME ON SYS_COURSE(COURSE_NAME);

-- Foreign Key lookup index for course enrollments
CREATE INDEX IDX_ENROLLMENT_COURSE_FK ON SYS_Enrollment(COURSE_ID);