create table auth_user(
    id bigint primary key auto_increment,
    name varchar(64),
    username varchar(255) not null unique,
    password varchar(255) not null,
    email varchar(50),
    salt varchar(64),
    status varchar(32)
);

create table auth_role(
    id bigint primary key auto_increment,
    name varchar(255) not null unique
);

create table auth_group(
    id bigint primary key auto_increment,
    name varchar(50),
    parent_id bigint
);

create table auth_perm(
    id bigint primary key auto_increment,
    type varchar(50)
);

create table auth_menu(
    id bigint primary key auto_increment,
    name varchar(50),
    url varchar(100),
    parent_id bigint
);

create table auth_file(
    id bigint primary key auto_increment,
    name varchar(100),
    code varchar(100),
    url varchar(100)
);

create table auth_oper(
    id bigint primary key auto_increment,
    name varchar(50),
    code varchar(100),
    url_prefix varchar(100),
    parent_id bigint
);

create table auth_user_role(
    user_id bigint not null,
    role_id bigint not null,
    primary key(user_id,role_id)
);

create table auth_user_group(
    user_id bigint,
    group_id bigint,
    primary key(user_id,group_id)
);

create table auth_role_group(
    role_id bigint,
    group_id bigint,
    primary key(role_id,group_id)
);

create table auth_role_perm(
   role_id bigint,
    perm_id bigint,
    primary key(role_id,perm_id)
);

create table auth_perm_menu(
    perm_id bigint,
    menu_id bigint,
    primary key(perm_id,menu_id)
);

create table auth_perm_file(
    perm_id bigint,
    file_id bigint,
    primary key(perm_id,file_id)
);

create table auth_perm_oper(
    perm_id bigint,
    oper_id bigint,
    primary key(perm_id,oper_id)
);