create table post
(
    id               bigint      not null auto_increment,
    title            varchar(50) not null,
    body             text        not null,
    writer           varchar(30) not null,
    created_at       timestamp   not null,
    last_modified_at timestamp   not null,
    primary key (id)
) engine=InnoDB;