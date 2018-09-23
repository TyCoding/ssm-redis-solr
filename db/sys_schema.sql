-- CREATE DATABASE ssm_redis DEFAULT CHARACTER SET utf8;

DROP TABLE IF EXISTS tb_item;
DROP TABLE IF EXISTS tb_user;

-- 商品表
CREATE TABLE tb_item(
  id bigint auto_increment comment '编号',
  title varchar(1000) comment '商品名称',
  price varchar(100) comment '商品价格',
  image varchar(1000) comment '商品图片',
  category varchar(1000) comment '商品标题',
  brand varchar(1000) comment '商品品牌',
  seller varchar(1000) comment '商品卖家',
  constraint pk_sys_users primary key(id)
) CHARSET=utf8 ENGINE=InnoDB;

-- 用户表
CREATE TABLE tb_user(
  id bigint auto_increment comment '编号',
  username VARCHAR(100) comment '用户名',
  password VARCHAR(100) comment '密码',
  salt VARCHAR(100) comment '盐值',
  constraint pk_sys_users primary key(id)
) CHARSET=utf8 ENGINE=InnoDB;

INSERT INTO tb_user VALUES(1, 'tycoding', '6b85f509338dc4381188a0a82cd5cf15', 'fd510006846219f8f9f3e053c9603be5');
INSERT INTO tb_user VALUES(2,'涂陌', 'eff31afdb73ac68608f7446e9f9767b6', 'b8099d0c46547e520acecd4ed2ef412b');

