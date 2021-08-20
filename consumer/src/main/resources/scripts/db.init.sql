use `ufo-plus`;

create table if not exists `users`(
    `id`            integer primary key auto_increment,
    `username`      varchar(100) not null,
    `email`         varchar(100) not null,
    `tenant`        varchar(20) not null,
    `createdAt`     datetime not null default current_timestamp,
    `modifiedAt`    datetime not null default current_timestamp
);

insert into `users`
(
    id, email, username, tenant, createdAt, modifiedAt
) values
(1, 'truong@mail.com', 'truong', 'vn', sysdate(), sysdate())
,(2, 'peter@mail.com', 'peter', 'tr', sysdate(), sysdate())
;

drop table if exists `pings`;
create table if not exists `pings`(
    `id` bigint primary key auto_increment,
    `tenant` varchar(20),
    `val` varchar(20) not null,
    `pin_id` bigint not null,
    `created_at` datetime not null default current_timestamp,
    `modified_at` datetime not null default current_timestamp
);