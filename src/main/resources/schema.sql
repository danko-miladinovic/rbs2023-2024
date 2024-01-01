drop table if exists users;
drop table if exists person;
drop table if exists cars;
drop table if exists comments;

create table users
(
    id       int          NOT NULL AUTO_INCREMENT,
    username varchar(255) not null,
    password varchar(255) not null,
    PRIMARY KEY (ID)
);

create table hashedUsers
(
    id       int          NOT NULL AUTO_INCREMENT,
    username varchar(255) not null,
    passwordHash varchar(64) not null,
    salt varchar(64) not null,
    totpKey varchar(255) null,
    PRIMARY KEY (ID)
);

create table persons
(
    id             int          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    firstName      varchar(255) not null,
    lastName       varchar(255) not null,
    email          varchar(255) not null,
    PRIMARY KEY (ID)
);

create table gift
(
    id             int          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name           varchar(255) not null,
    description    varchar(255) not null,
    price          double       not null,
    PRIMARY KEY (ID)
);

create table tags
(
    id             int          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name           varchar(255) not null,
    PRIMARY KEY (ID)
);

create table gift_to_tag
(
    giftId             int     NOT NULL,
    tagId              int     NOT NULL
);

create table ratings
(
    giftId              int     NOT NULL,
    userId              int     NOT NULL,
    rating              int     NOT NULL
);

create table comments
(
    id             int          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    giftId         int          NOT NULL,
    userId         int          NOT NULL,
    comment        varchar(500) NOT NULL,
    PRIMARY KEY (ID)
);

create table user_to_roles(
    userId         int          NOT NULL,
    roleId         int          NOT NULL
);

create table roles(
    id             int          NOT NULL,
    name           varchar(255) NOT NULL,
    PRIMARY KEY (ID)
);

create table permissions(
    id             int          NOT NULL,
    name           varchar(255) NOT NULL,
    PRIMARY KEY (ID)
);

create table role_to_permissions(
    roleId         int          NOT NULL,
    permissionId   int          NOT NULL
);