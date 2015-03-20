update SAMPLER_CUSTOMER set ACTIVE = false ;
alter table SAMPLER_CUSTOMER alter column ACTIVE set not null ;
