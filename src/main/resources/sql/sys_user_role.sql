CREATE TABLE sys_user_role (user_id int(10) NOT NULL, role_id int(10) NOT NULL, roleName varchar(50), roleDesc varchar(50)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO sys_user_role (user_id, role_id, roleName, roleDesc) VALUES (1, 11, '管理员', '普通管理员');
INSERT INTO sys_user_role (user_id, role_id, roleName, roleDesc) VALUES (2, 12, '经理', '技术经理');
INSERT INTO sys_user_role (user_id, role_id, roleName, roleDesc) VALUES (3, 13, '管理员', '后台管理员');
