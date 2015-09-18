create table SAMPLER_POINT_PAIR (
    ID varchar(36) not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    VERSION integer,
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    AX double precision not null,
    AY double precision not null,
    BX double precision not null,
    BY_ double precision not null,
    --
    primary key (ID)
);
