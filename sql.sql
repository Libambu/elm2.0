CREATE DATABASE IF NOT EXISTS myelb
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE myelb;

create table authority
(
    name varchar(50) not null
        primary key
);

create table users
(
    id          bigint auto_increment
        primary key,
    create_time datetime(6)  null,
    creator     bigint       null,
    is_deleted  bit          null,
    update_time datetime(6)  null,
    updater     bigint       null,
    activated   bit          not null,
    password    varchar(100) not null,
    username    varchar(100) not null,
    constraint UKr43af9ap4edm43mmtq01oddj6
        unique (username)
);

create table business
(
    id               bigint auto_increment
        primary key,
    create_time      datetime(6)    null,
    creator          bigint         null,
    is_deleted       bit            null,
    update_time      datetime(6)    null,
    updater          bigint         null,
    business_address varchar(255)   null,
    business_explain varchar(255)   null,
    business_img     text           null,
    business_name    varchar(255)   not null,
    delivery_price   decimal(10, 2) null,
    order_type_id    int            null,
    remarks          varchar(255)   null,
    start_price      decimal(10, 2) null,
    user_id          bigint         not null,
    constraint FKtm7f6x8bo3o8pk2kraawyq18u
        foreign key (user_id) references users (id)
);

create table delivery_address
(
    id           bigint auto_increment
        primary key,
    create_time  datetime(6)  null,
    creator      bigint       null,
    is_deleted   bit          null,
    update_time  datetime(6)  null,
    updater      bigint       null,
    address      varchar(255) null,
    contact_name varchar(255) null,
    contact_sex  int          null,
    contact_tel  varchar(255) null,
    user_id      bigint       not null,
    constraint FKlunoaq6lsl5d75tkbg3alrn6u
        foreign key (user_id) references users (id)
);

create table food
(
    id           bigint auto_increment
        primary key,
    create_time  datetime(6)    null,
    creator      bigint         null,
    is_deleted   bit            null,
    update_time  datetime(6)    null,
    updater      bigint         null,
    food_explain varchar(255)   null,
    food_img     text           null,
    food_name    varchar(255)   not null,
    food_price   decimal(10, 2) not null,
    remarks      varchar(255)   null,
    business_id  bigint         not null,
    constraint FK7aacvfjdybtgiw2tb7vm7nu72
        foreign key (business_id) references business (id)
);

create table cart
(
    id          bigint auto_increment
        primary key,
    create_time datetime(6) null,
    creator     bigint      null,
    is_deleted  bit         null,
    update_time datetime(6) null,
    updater     bigint      null,
    quantity    int         null,
    business_id bigint      not null,
    customer_id bigint      not null,
    food_id     bigint      not null,
    constraint FK9idro6c1m306j5glp69u15o9j
        foreign key (food_id) references food (id),
    constraint FKgvn8s674rpifksi4a6uaq9ymh
        foreign key (business_id) references business (id),
    constraint FKrynrwuqbpdheocivcmp9itsxi
        foreign key (customer_id) references users (id)
);

create table orders
(
    id          bigint auto_increment
        primary key,
    create_time datetime(6)    null,
    creator     bigint         null,
    is_deleted  bit            null,
    update_time datetime(6)    null,
    updater     bigint         null,
    order_date  datetime(6)    null,
    order_state int            null,
    order_total decimal(10, 2) not null,
    business_id bigint         not null,
    customer_id bigint         not null,
    address_id  bigint         not null,
    constraint FK6yv9sbeyxjkhkrdb2opee19tg
        foreign key (business_id) references business (id),
    constraint FKnxiucb4707442svu8ppyng5md
        foreign key (address_id) references delivery_address (id),
    constraint FKsjfs85qf6vmcurlx43cnc16gy
        foreign key (customer_id) references users (id)
);

create table orderdetailet
(
    id          bigint auto_increment
        primary key,
    create_time datetime(6) null,
    creator     bigint      null,
    is_deleted  bit         null,
    update_time datetime(6) null,
    updater     bigint      null,
    quantity    int         null,
    food_id     bigint      not null,
    order_id    bigint      not null,
    constraint FKafcbyjtsrerl3nb0mafb089ys
        foreign key (food_id) references food (id),
    constraint FKj78b4w018ti2fm1th2y7rip7p
        foreign key (order_id) references orders (id)
);

create table person
(
    email      varchar(255) null,
    first_name varchar(255) null,
    gender     varchar(255) null,
    last_name  varchar(255) null,
    phone      varchar(255) null,
    photo      text         null,
    id         bigint       not null
        primary key,
    constraint FKt9eifwfaty2datnx2ra7ll9dc
        foreign key (id) references users (id)
);

create table user_authority
(
    user_id        bigint      not null,
    authority_name varchar(50) not null,
    primary key (user_id, authority_name),
    constraint FK6ktglpl5mjosa283rvken2py5
        foreign key (authority_name) references authority (name),
    constraint FKhi46vu7680y1hwvmnnuh4cybx
        foreign key (user_id) references users (id)
);

INSERT INTO elb.authority (name) VALUES ('ADMIN');

INSERT INTO elb.users (id, create_time, creator, is_deleted, update_time, updater, activated, password, username) VALUES (1, '2025-09-17 16:08:52.000000', 0, false, '2025-09-20 10:52:57.724621', 1, true,
        '$2a$10$ZNSjLD6NK7dVZonkPK11QOOmSNrykc80LZbmKgOm6ImHbiu2QQx4K', 'admin');

INSERT INTO elb.user_authority (user_id, authority_name)
VALUES (1, 'ADMIN');

