create sequence user_id_seq start with 1 increment by 50;

create table users (
    id          bigint default nextval('user_id_seq') not null,
    username    text not null unique,
    email       text not null unique,
    password    text not null,
    full_name   text not null,
    role        text default 'USER',
    created_at  timestamp,
    updated_at  timestamp,
    primary key (id)
);