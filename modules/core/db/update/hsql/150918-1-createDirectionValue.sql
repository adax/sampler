create table SAMPLER_DIRECTION_VALUE (
    ID varchar(36) not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    VERSION integer,
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    DIRECTION varchar(50) not null,
    VALUE_ double precision not null,
    --
    primary key (ID)
);
