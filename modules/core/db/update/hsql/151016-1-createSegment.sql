create table SAMPLER_SEGMENT (
    ID varchar(36) not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    VERSION integer,
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    START_ integer,
    DURATION integer,
    COLOR varchar(255),
    TASK_ varchar(255),
    TASK_SPAN_ID varchar(36),
    --
    primary key (ID)
);
