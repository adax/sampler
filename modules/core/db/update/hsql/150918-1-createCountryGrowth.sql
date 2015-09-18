create table SAMPLER_COUNTRY_GROWTH (
    ID varchar(36) not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    VERSION integer,
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    COUNTRY varchar(255) not null,
    YEAR2014 double precision not null,
    YEAR2015 double precision not null,
    --
    primary key (ID)
);
