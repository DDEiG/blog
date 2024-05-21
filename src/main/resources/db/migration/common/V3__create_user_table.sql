create table user
(
    id              BIGINT  NOT NULL auto_increment,
    username        VARCHAR(255) NOT NULL,
    password        VARCHAR(255) NOT NULL,
    nickname        VARCHAR(255) NOT NULL,
    gender          VARCHAR(255) NOT NULL,
    birthday        DATE    NOT NULL,
    created_at       timestamp   not null,
    last_modified_at timestamp   not null,
    PRIMARY KEY (id)
) engine=InnoDB;


ALTER TABLE user
    ADD CONSTRAINT UK_user_username UNIQUE (username);

ALTER TABLE user
    ADD CONSTRAINT UK_user_nickname UNIQUE (nickname);

CREATE TABLE user_roles
(
    user_id BIGINT    NOT NULL,
    role    VARCHAR(255) NOT NULL
);

ALTER TABLE user_roles
    ADD CONSTRAINT FK_user_roles_to_user FOREIGN KEY (user_id)
        REFERENCES user(id);