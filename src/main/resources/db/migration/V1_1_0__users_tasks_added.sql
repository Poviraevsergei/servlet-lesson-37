create sequence IF NOT EXISTS public.tasks_id_seq;

create sequence IF NOT EXISTS public.users_id_seq;

create table IF NOT EXISTS public.users
(
    id       bigint default nextval('users_id_seq'::regclass) not null
        primary key,
    username text                                             not null,
    password varchar(10)                                      not null
);

create table IF NOT EXISTS public.tasks
(
    id      bigserial
        constraint tasks_pk
        primary key,
    value   varchar(200) not null,
    user_id bigint       not null
        constraint users_id___fk
            references public.users
            on update cascade on delete cascade
);