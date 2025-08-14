create sequence book_id_seq start with 1 increment by 50;

create table books (
    id bigint default nextval(book_id_seq) not null,
    title text not null,
    author text not null,
    genre text not null,
    description text not null,
    created_at timestamp not null,
    updated_at timestamp,
    username text not null,
    primary key (id)
);