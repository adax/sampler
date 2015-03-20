create table SAMPLER_TASK (
    ID varchar(36) not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    VERSION integer,
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    DUE_DATE timestamp,
    ASSIGNEE_ID varchar(36),
    PARENT_TASK_ID varchar(36),
    --
    primary key (ID)
);
