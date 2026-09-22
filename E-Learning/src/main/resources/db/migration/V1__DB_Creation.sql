    --Option 8A for enhanced ERD
    CREATE TABLE SYS_ROLE
    (
        Role_Id   RAW(16) NOT NULL, --UUID Type in oracle sql: RAW(16)
        Role_Name VARCHAR2(50)   NOT NULL UNIQUE,
        CONSTRAINT Role_Id_PK PRIMARY KEY (Role_Id)
    );


    CREATE TABLE SYS_Status
    (
        Status_ID RAW(16) NOT NULL,
        Status_NAME VARCHAR2(50) NOT NULL UNIQUE,
        CONSTRAINT Status_ID_PK PRIMARY KEY (Status_ID)
    );

    CREATE TABLE SYS_USER
        (
        USER_ID RAW(16) NOT NULL, --UUID Type in oracle sql: RAW(16)
        Role_Id   RAW(16) NOT NULL, --UUID Type in oracle sql: RAW(16)
        Full_Name VARCHAR2(50),
        Email VARCHAR2(50) UNIQUE ,
        Password VARCHAR2(255), --encoded by Bcrypt
        CREATED_AT DATE DEFAULT SYSDATE,
        UPDATE_AT DATE DEFAULT SYSDATE,

        CONSTRAINT USER_ID_PK PRIMARY KEY (USER_ID),
        CONSTRAINT Role_ID_FK FOREIGN KEY (Role_Id)   REFERENCES SYS_ROLE(Role_Id)
    );

    -- DROP TABLE SYS_USER CASCADE CONSTRAINTS ;
    --User/Role(Instructor) -----   course(pk + instructorId FK)
    --thumbnail : image
    --Course will have one status: one to many
    CREATE TABLE SYS_COURSE
    (
        COURSE_ID      RAW(16) NOT NULL,
        USER_ID        RAW(16) NOT NULL,
        Status_ID    RAW(16) NOT NULL,

        COURSE_NAME    VARCHAR2(100) NOT NULL,
        DESCRIPTION    VARCHAR2(1000),
        THUMBNAIL_URL  VARCHAR2(500), -- image
        CREATED_AT    DATE DEFAULT SYSDATE  NOT NULL,
        UPDATED_AT   DATE  DEFAULT SYSDATE  NOT NULL,

        CONSTRAINT COURSE_ID_PK PRIMARY KEY (COURSE_ID),

        CONSTRAINT COURSE_USER_FK FOREIGN KEY (USER_ID) REFERENCES SYS_USER(USER_ID),
         CONSTRAINT Status_FK1 FOREIGN KEY (Status_ID) REFERENCES SYS_Status(Status_ID)
    );


    --One to many Course === Session
    CREATE TABLE SYS_SESSION
    (
        SESSION_ID     RAW(16) NOT NULL,
        COURSE_ID      RAW(16) NOT NULL,
        Status_ID    RAW(16) NOT NULL,

        SESSION_TITLE  VARCHAR2(100) NOT NULL,
        DESCRIPTION    VARCHAR2(1000),
        VIDEO_URL      VARCHAR2(500),
        CREATED_AT     DATE DEFAULT SYSDATE NOT NULL,

        CONSTRAINT SESSION_ID_PK PRIMARY KEY (SESSION_ID),

        CONSTRAINT COURSE_FK   FOREIGN KEY (COURSE_ID)  REFERENCES SYS_COURSE(COURSE_ID),
        CONSTRAINT Status_FK2 FOREIGN KEY (Status_ID) REFERENCES SYS_Status(Status_ID),
        CONSTRAINT COURSE_SESSION_TITLE UNIQUE (COURSE_ID, SESSION_TITLE)  --must not have same session of the same course twice

    );

    --User/Role(Student)   ----< ENROLLMENT(ASSOCIATIVE) 2FKs >------ course pk
    CREATE TABLE SYS_Enrollment
        (
            Enrollment_Id RAW(16) NOT NULL,

            USER_ID RAW(16) NOT NULL, --for Student --Student_ID --FK
            COURSE_ID   RAW(16) NOT NULL, --FK
            CONSTRAINT Enrollment_Id_PK PRIMARY KEY (Enrollment_Id),
            CONSTRAINT USER_ID_FK FOREIGN KEY (USER_ID) REFERENCES SYS_USER(USER_ID),
            CONSTRAINT COURSE_ID_FK1 FOREIGN KEY (COURSE_ID) REFERENCES SYS_COURSE(COURSE_ID),
            --user must be enrolled in the course only once
            CONSTRAINT User_Course_Constraint UNIQUE (USER_ID,COURSE_ID)

    );
