drop table if exists wms_menu;
create table wms_menu(
   id bigint primary key auto_increment  comment  '主键',
   menu_name varchar(50) comment '菜单名称',
   menu_code varchar(20) comment '菜单CODE',
   menu_index varchar(50) comment '菜单导航',
   menu_level int comment '菜单层级 0:根菜单 1:一级菜单',
   parent_code varchar(20) comment '父级菜单',
   status int comment '状态 0:待处理 1:有效 2:无效'
);

drop table if exists wms_user;
create table wms_user(
   id bigint primary key auto_increment comment '主键',
   user_code varchar(35) comment '用户编码',
   user_name varchar(128) comment '用户名称',
   user_mobile varchar(15) comment '用户手机号',
   store_code varchar(35) comment '用户归属门店',
   user_role varchar(50) comment '用户角色',
   password varchar(35) comment '用户密码'
) comment '用户信息';

drop table if exists wms_session;
create table wms_session(
   id bigint primary key auto_increment comment '主键',
   session_id varchar(50),
   user_code varchar(35) comment '用户CODE',
   expired_time datetime comment '过期时间'
) comment 'session信息';

drop table if exists wms_store;
create table wms_store(
   id bigint primary key auto_increment comment '主键',
   store_name varchar(100) comment '门店名称',
   store_code varchar(35) comment '门店编码',
   store_addr varchar(1024) comment '门店地址'
) comment '门店信息';

drop table if exists wms_material;
create table wms_material (
   id bigint primary key auto_increment  comment  '主键',
   material_name varchar(50) not null comment '原料名称',
   material_code varchar(35) not null comment '原料编码',
   can_split varchar(1) not null comment '是否可拆分 Y:是 N:否',
   stock_unit varchar(10) comment '单位，如克，条，箱等',
   status int comment '状态 0:待处理 1:有效 2:无效'
);

drop table if exists wms_material_split_cfg;
create table wms_material_split_cfg (
   id bigint primary key auto_increment  comment  '主键',
   material_name varchar(50) not null comment '原料名称',
   material_code varchar(35) not null comment '原料编码',
   split_name varchar(1) not null comment '拆分子原料名称',
   split_code varchar(10) comment '拆分子原料编码',
   split_amt double(20,2) comment '拆分数量',
   status int comment '状态 0:待处理 1:有效 2:无效'
) comment '菜品拆分配置表';

drop table if exists wms_material_stock;
create table wms_material_stock(
   id bigint primary key auto_increment comment '主键',
   material_name varchar(50) comment '材料名称',
   material_code varchar(35) comment '原料编码',
   curr_stock decimal(20,2) comment '当前库存',
   used_stock decimal(20,2) comment '消耗库存',
   stock_type varchar(1) comment '库存类型, 1:总库,2:分库'
);

drop table if exists wms_material_stock_history;
create table wms_material_stock_history (
   id bigint primary key auto_increment comment '主键',
   material_name varchar(50) comment '材料名称',
   material_code varchar(35) comment '原料编码',
   op_type varchar(30) comment '流水类型,如入库、出库、盘点',
   stock_chg decimal(20,2) comment '库存变化，正数表示增加，负数表示减少',
   curr_stock decimal(20,2) comment '库存剩余',
   operator varchar(30) comment '操作人',
   store_code varchar(35) comment '门店',
   remark varchar(1024) comment '备注'
);

drop table if exists wms_recipes;
create table wms_recipes(
   id bigint primary key auto_increment comment '主键',
   recipes_name varchar(50) comment '食谱名称',
   recipes_code varchar(35) comment '食谱编码',
   store_code varchar(35) comment '门店，默认:DEFAULT'
) comment '食谱';

drop table if exists wms_recipes_formula;
create table wms_recipes_formula(
   id bigint primary key auto_increment comment '主键',
   recipes_name varchar(50) comment '食谱名称',
   recipes_code varchar(35) comment '食谱编码',
   material_name varchar(50) comment '食材名称',
   material_code varchar(35) comment '食材编码',
   material_amt double comment '食材数量',
   material_unit varchar(10) comment '食材单位'
) comment '食谱配方';

drop table if exists wms_material_supplier;
CREATE TABLE wms_material_supplier
    (
        id bigint primary key auto_increment  comment  '主键',
        material_name VARCHAR(50) NOT NULL COMMENT '原料名称',
        material_code VARCHAR(35) NOT NULL COMMENT '原料编码',
        supplier_code VARCHAR(32) NOT NULL COMMENT '供货商编码',
        supplier_name VARCHAR(128) NOT NULL COMMENT '供货商名称'
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供货商';

