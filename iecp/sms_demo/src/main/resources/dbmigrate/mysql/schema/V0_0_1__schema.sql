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

-- SMS Platform

create table advice(
    id bigint primary key auto_increment,
    title varchar(50),
    content varchar(140),
    read_times bigint,
    user_id bigint,
    deleted tinyint default 0
);

create table channel(
    id bigint primary key auto_increment,
    name varchar(50),
    operator varchar(50),
    deleted tinyint default 0
);

create table channel_group(
    id bigint primary key auto_increment,
    name varchar(50),
    ctcc_id bigint,
    cmcc_id bigint,
    cucc_id bigint,
    deleted tinyint default 0
);

create table financial(
    id bigint primary key auto_increment,
    actCount bigint,
    actTime varchar(50),
    actType varchar(50),
    user_id bigint,
    deleted tinyint default 0
);

create table setting(
    id bigint primary key auto_increment,
    audit_condition int,
    cut_condition int,
    cut_percent int,
    whitelist bigint,
    blacklist bigint,
    user_id bigint,
    deleted tinyint default 0
);

create table sms(
    id bigint primary key auto_increment,
    sequence_id varchar(64),
    phone_number varchar(11),
    content varchar(500),
    push_time varchar(50),
    push_status varchar(50),
    receipt_time varchar(50),
    receipt_status varchar(50),
    deleted tinyint default 0
);

create table sms_receive(
    id bigint primary key auto_increment,
    from_phone_number varchar(11),
    content varchar(500),
    receive_time varchar(50),
    user_id bigint,
    deleted tinyint default 0
);

create table sms_task(
    id bigint primary key auto_increment,
    content varchar(500),
    plan_time varchar(50),
    send_time varchar(50),
    send_status varchar(50),
    phone_attachment bigint,
    owner bigint,
    channel_group_id bigint,
    deleted tinyint default 0
);