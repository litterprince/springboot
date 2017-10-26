-- 用户表
create table t_user(
	id varchar(50) primary key,
	user_name varchar(100) NOT NULL unique,
	password varchar(100),
	real_name varchar(100),
	sex varchar(2),
	create_time date
);
-- 角色表
create table t_role(
	id varchar(50) primary key,
	role_name varchar(100),
	remark varchar(100)
);
-- 用户角色表
create table t_user_role(
	id varchar(50) primary key,
	user_id varchar(50),
	role_id varchar(50),
	create_time date
);
-- 权限表
create table t_power(
	id varchar(50) primary key,
	power_name varchar(100),
	remark varchar(100)
);
-- 权限角色表
create table t_power_role(
	id varchar(50) primary key,
	power_id varchar(50),
	role_id varchar(50),
	create_time date
);
insert  into t_user values ('1','ceshi1','C4CA4238A0B923820DCC509A6F75849B','王','男','2017-10-10 00:00:00');
insert  into t_user values ('2','ceshi2','C4CA4238A0B923820DCC509A6F75849B','李','女','2017-10-10 00:00:00');
insert  into t_user values ('3','ceshi3','C4CA4238A0B923820DCC509A6F75849B','张','女','2017-10-10 00:00:00');
insert  into t_role values ('1','admin','超级管理员');
insert  into t_role values ('2','manager','管理员');
insert  into t_role values ('3','normal','普通用户');
insert  into t_power values ('1','add','增加');
insert  into t_power values ('2','update','修改');
insert  into t_power values ('3','select','查询');
insert  into t_power values ('4','del','删除');
insert  into t_user_role values ('1','1','1','2017-10-10 01:00:00');
insert  into t_user_role values ('2','2','2','2017-10-10 02:00:00');
insert  into t_user_role values ('3','3','3','2017-10-10 03:00:00');
insert  into t_power_role values ('1','1','1','2017-10-10 01:00:00');
insert  into t_power_role values ('2','1','1','2017-10-10 02:00:00');
insert  into t_power_role values ('3','1','1','2017-10-10 03:00:00');
insert  into t_power_role values ('4','2','2','2017-10-10 04:00:00');
insert  into t_power_role values ('5','3','2','2017-10-10 05:00:00');
insert  into t_power_role values ('6','3','3','2017-10-10 06:00:00');