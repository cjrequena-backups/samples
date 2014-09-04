use SECURITY;

insert into SECURITY.ROLE (id_role,role_name) values (1,'ROLE_ADMIN');
insert into SECURITY.ROLE (id_role,role_name) values (2,'ROLE_USER');

insert into SECURITY.RIGHT (id_right,right_name) values (1,'RIGHT_view');
insert into SECURITY.RIGHT (id_right,right_name) values (2,'RIGHT_create');
insert into SECURITY.RIGHT (id_right,right_name) values (3,'RIGHT_delete');
insert into SECURITY.RIGHT (id_right,right_name) values (4,'RIGHT_update');

insert into SECURITY.ROLE_RIGHT (id_role,id_right) values (1,1);
insert into SECURITY.ROLE_RIGHT (id_role,id_right) values (1,2);
insert into SECURITY.ROLE_RIGHT (id_role,id_right) values (1,3);
insert into SECURITY.ROLE_RIGHT (id_role,id_right) values (1,4);
insert into SECURITY.ROLE_RIGHT (id_role,id_right) values (2,1);

insert into SECURITY.USER (id_user,email,password,alias,first_name,last_name,mobile_phone) values (1,'admin@admin.com','admin','admin','pepe','trueno','555 555 555');
insert into SECURITY.USER (id_user,email,password,alias,first_name,last_name,mobile_phone) values (2,'user@user.com','user','user','paco','chocolatero','555 555 555');

insert into SECURITY.USER_ROLE (id_user,id_role) values (1,1);
insert into SECURITY.USER_ROLE (id_user,id_role) values (2,2);



