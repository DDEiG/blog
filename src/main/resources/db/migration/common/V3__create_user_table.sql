create table user
(
    id              BIGINT  NOT NULL auto_increment,
    password        VARCHAR(255) NOT NULL,
    first_name      VARCHAR(255) NOT NULL,
    last_name       VARCHAR(255) NOT NULL,
    gender          VARCHAR(255) NOT NULL,
    birthday        DATE    NOT NULL,
    email           VARCHAR(255) NOT NULL,
    phone_number    VARCHAR(255) NOT NULL,
    created_at       timestamp   not null,
    last_modified_at timestamp   not null,
    PRIMARY KEY (id)
) engine=InnoDB;


ALTER TABLE user
    ADD CONSTRAINT UK_user_email UNIQUE (email);

CREATE TABLE user_roles
(
    user_id BIGINT    NOT NULL,
    role    VARCHAR(255) NOT NULL
);

ALTER TABLE user_roles
    ADD CONSTRAINT FK_user_roles_to_user FOREIGN KEY (user_id)
        REFERENCES user(id);