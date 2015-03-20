-- begin SAMPLER_CUSTOMER
create table SAMPLER_CUSTOMER (
    ID varchar(36) not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    VERSION integer,
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(50) not null,
    LAST_NAME varchar(100) not null,
    AGE integer,
    ACTIVE boolean not null,
    --
    primary key (ID)
)^
-- end SAMPLER_CUSTOMER
-- begin SAMPLER_ORDER
create table SAMPLER_ORDER (
    ID varchar(36) not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    VERSION integer,
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CUSTOMER_ID varchar(36),
    DATE_ date not null,
    AMOUNT decimal(19, 2),
    DESCRIPTION varchar(255),
    --
    primary key (ID)
)^
-- end SAMPLER_ORDER
-- begin SAMPLER_TASK
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
)^
-- end SAMPLER_TASK
