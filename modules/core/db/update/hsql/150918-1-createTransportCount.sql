create table SAMPLER_TRANSPORT_COUNT (
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
    CARS integer not null,
    MOTORCYCLES integer not null,
    BICYCLES integer not null,
    --
    primary key (ID)
);
