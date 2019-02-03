

drop table users if exists cascade;
drop table authorities if exists cascade;


create table users(
    username varchar_ignorecase(50) not null primary key,
    password varchar_ignorecase(50) not null,
    enabled boolean not null
);

create table authorities (
    username varchar_ignorecase(50) not null,
    authority varchar_ignorecase(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
);

insert into users values('han','123',true);
insert into authorities values('han','ROLE_USER');