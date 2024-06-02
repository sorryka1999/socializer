--liquibase formatted sql

create table portal_user
(
    id                  bigserial primary key,
    email               varchar(63) not null unique,
    password            varchar(60) not null,
    full_name           varchar(63) not null,
    portal_user_type    varchar(8)  not null,
    is_active           boolean     not null,
    created_at          timestamp   not null
);

create table question
(
    id                  bigserial primary key,
    name                varchar(63) not null unique
);

create table result
(
    id                  bigserial primary key,
    answer              varchar(63) not null,
    portal_user         bigint      not null,
    question            bigint      not null,
    constraint  portal_user_fkey
        foreign key (portal_user) references
            portal_user (id) match simple
            on update restrict
            on delete restrict,
    constraint  question_fkey
        foreign key (question) references
            question (id) match simple
            on update restrict
            on delete restrict,
    constraint  portal_user_question_key
        unique (portal_user, question)
);
