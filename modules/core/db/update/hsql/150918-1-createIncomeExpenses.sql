create table SAMPLER_INCOME_EXPENSES (
    ID varchar(36) not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    VERSION integer,
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    YEAR_ integer not null,
    INCOME double precision not null,
    EXPENSES double precision not null,
    ALPHA double precision,
    DASH_LENGTH_LINE integer,
    DASH_LENGTH_COLUMN integer,
    --
    primary key (ID)
);
