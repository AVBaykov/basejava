create table resume
(
  uuid      char(36) not null
    constraint resume_pkey
    primary key,
  full_name text     NOT NULL
);

alter table resume
  owner to postgres;

create table contact
(
  id          serial   not null
    constraint contact_pkey
    primary key,
  resume_uuid char(36) not null
    constraint contact_resume_uuid_fk
    references resume
    on delete cascade,
  type        text     not null,
  value       text     not null
);
CREATE UNIQUE INDEX contact_uuid_type_index
  ON public.contact (resume_uuid, type);

alter table contact
  owner to postgres;

create table text_section
(
  id serial not null
    constraint section_pkey
    primary key,
  type text not null,
  value text not null,
  resume_uuid char(36) not null
    constraint section_resume_uuid_fk
    references resume
    on delete cascade
)
;

alter table string_section owner to postgres
;

create index text_section_uuid_index
  on string_section (resume_uuid)
;
