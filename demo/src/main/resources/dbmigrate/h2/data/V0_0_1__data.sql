insert into auth_user (id,username, name,email, password, salt,status) values(1,'admin','管理员','aa@aa.com','691b14d79bf0fa2215f155235df5e670b64394cc','7efbd59d9741d34f','enabled');
insert into auth_user (id,username, name,email, password, salt,status) values(2,'user','普通用户','bb@bb.com','2488aa0c31c624687bd9928e0a5d29e7d1ed520b','6d65d24122c30500','enabled');
insert into auth_user (id,username, name,email, password, salt,status) values(3,'guest','来宾用户','cc@cc.com','7308c9fc069595af791e55bbc29db809cb627f79','fcfc26e15a99d7ad','enabled');

insert into auth_role (id, name) values(1,'Admin');
insert into auth_role (id, name) values(2,'User');

insert into auth_group (id,name) values(1,'sys');
insert into auth_group (id,name) values(2,'normal');

-- readonly , generate by system initial
insert into auth_perm (id,type) values(1,'menu');
insert into auth_perm (id,type) values(2,'file');
insert into auth_perm (id,type) values(3,'oper');

insert into auth_menu (id,name,url) values(1,'parent1','parent1');
insert into auth_menu (id,name,url) values(2,'parent2','parent2');
insert into auth_menu (id,name,url,parent_id) values(3,'child1','child1',1);
insert into auth_menu (id,name,url,parent_id) values(4,'child2','child2',2);

-- code used to be linuxer
insert into auth_file (id,name,code,url) values(1,'test.sql','777','static/test.sql');

-- shiro's permission expression
insert into auth_oper (id,name,code) values(1,'查看','user:view');
insert into auth_oper (id,name,code) values(2,'修改','user:edit');

insert into auth_user_role (user_id, role_id) values(1,1);
insert into auth_user_role (user_id, role_id) values(1,2);
insert into auth_user_role (user_id, role_id) values(2,2);
insert into auth_user_role (user_id, role_id) values(3,2);

insert into auth_user_group (user_id,group_id) values(1,1);

insert into auth_role_group (role_id,group_id) values(1,1);

insert into auth_role_perm (role_id,perm_id) values(1,1);
insert into auth_role_perm (role_id,perm_id) values(1,2);
insert into auth_role_perm (role_id,perm_id) values(1,3);

insert into auth_perm_menu (perm_id,menu_id) values(1,1);
insert into auth_perm_menu (perm_id,menu_id) values(1,2);
insert into auth_perm_menu (perm_id,menu_id) values(1,3);
insert into auth_perm_menu (perm_id,menu_id) values(1,4);

insert into auth_perm_file (perm_id,file_id) values(2,1);

insert into auth_perm_oper (perm_id,oper_id) values(3,1);
insert into auth_perm_oper (perm_id,oper_id) values(3,2);
