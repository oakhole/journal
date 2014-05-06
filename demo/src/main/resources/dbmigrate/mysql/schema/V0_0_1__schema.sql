
create table user(
    id bigint primary key auto_increment,
    name varchar(255),
    username varchar(255) not null unique,
    password varchar(255) not null,
    email varchar(50),
    salt varchar(64),
    status varchar(32)
)engine=innoDB;

create table role(
    id bigint primary key auto_increment,
    name varchar(255),
    permissions varchar(255)
)engine=innoDB;

create table group(
    id bigint primary key auto_increment,
    name varchar(50),
    parent_id bigint
)engine=innoDB;

create table user_role(
    user_id bigint not null,
    role_id bigint not null,
    primary key(user_id,role_id)
)engine=innoDB;
