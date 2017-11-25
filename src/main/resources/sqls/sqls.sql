CREATE TABLE
    wms_dict
    (
        id bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
        dict_group VARCHAR(128) NOT NULL COMMENT '字典分组',
        dict_code VARCHAR(128) NOT NULL COMMENT '字典代码',
        dict_name VARCHAR(128) COMMENT '字典名称',
        dict_val VARCHAR(128) COMMENT '字典值',
        PRIMARY KEY (id),
        CONSTRAINT uni_group_code UNIQUE (dict_code, dict_group)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE
    wms_inventory_apply
    (
        id bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
        apply_num VARCHAR(64) NOT NULL COMMENT '申请单号码',
        cabin_code VARCHAR(35) NOT NULL COMMENT '货站代码',
        cabin_name VARCHAR(128) NOT NULL COMMENT '货站名称',
        apply_type VARCHAR(32) NOT NULL COMMENT '申请单类型',
        serial_no VARCHAR(64) NOT NULL COMMENT '流水号',
        proposer VARCHAR(128) NOT NULL COMMENT '申请人',
        status VARCHAR(2) NOT NULL COMMENT
        '采购单状态, 0:草稿, 1:提交待审核 2:审核通过 3:采购中 4:配送中 5:已入库, 6:已撤销,7:驳回',
        remark VARCHAR(1024) COMMENT '备注',
        gmt_created DATETIME NOT NULL COMMENT '创建时间',
        gmt_modified DATETIME NOT NULL COMMENT '最近修改时间',
        creator VARCHAR(35) NOT NULL COMMENT '创建人',
        modifier VARCHAR(35) NOT NULL COMMENT '最近修改人',
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE
    wms_inventory_apply_detail
    (
        id bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
        apply_num VARCHAR(64) NOT NULL COMMENT '采购单号码',
        apply_type VARCHAR(32) NOT NULL COMMENT '申请单类型',
        cabin_code VARCHAR(35) NOT NULL COMMENT '货站代码',
        cabin_name VARCHAR(128) NOT NULL COMMENT '货站名称',
        material_code VARCHAR(35) NOT NULL COMMENT '原料代码',
        material_name VARCHAR(128) NOT NULL COMMENT '原料名称',
        supplier_code VARCHAR(35) COMMENT '供应商',
        supplier_name VARCHAR(128) COMMENT '供应商名称',
        spec_amt DECIMAL(14,2) COMMENT '采购量(规则)',
        spec_unit VARCHAR(32) COMMENT '规格单位',
        spec_price DECIMAL(14,2) COMMENT '单价，按规则',
        stock_amt DECIMAL(14,2) COMMENT '库存量,根据规格计算',
        real_stock_amt DECIMAL(14,2) COMMENT '实际入库量，由接收人确认',
        stock_unit VARCHAR(32) COMMENT '库存单位',
        total_price DECIMAL(14,2) COMMENT '总价',
        remark VARCHAR(1024) COMMENT '备注',
        prod_date DATETIME COMMENT '生产日期',
        exp_date DATETIME COMMENT '过期日期',
        keep_time VARCHAR(10) COMMENT '保质期，如10天，1月等',
        gmt_created DATETIME NOT NULL COMMENT '创建时间',
        status VARCHAR(2) NOT NULL COMMENT '状态',
        gmt_modified DATETIME NOT NULL COMMENT '最近修改时间',
        creator VARCHAR(35) NOT NULL COMMENT '创建人',
        modifier VARCHAR(35) NOT NULL COMMENT '最近修改人',
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE
    wms_material
    (
        id bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
        material_name VARCHAR(50) NOT NULL COMMENT '原料名称',
        material_code VARCHAR(35) NOT NULL COMMENT '原料编码',
        spec_unit VARCHAR(32) COMMENT '规格单位，如包、箱等',
        spec_qty DECIMAL(14,2) COMMENT '规格数量',
        stock_unit VARCHAR(10) COMMENT '单位，如克，条，箱等',
        status INT COMMENT '状态 0:待处理 1:有效 2:无效',
        search_key VARCHAR(512) COMMENT '搜索key,以逗号隔开',
        utilization_ratio INT DEFAULT '100' NOT NULL COMMENT '利用率(%)',
        storage_life VARCHAR(10) COMMENT '保质时间',
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE
    wms_material_split_cfg
    (
        id bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
        material_name VARCHAR(50) NOT NULL COMMENT '原料名称',
        material_code VARCHAR(35) NOT NULL COMMENT '原料编码',
        split_name VARCHAR(128) NOT NULL COMMENT '拆分子原料名称',
        split_code VARCHAR(35) COMMENT '拆分子原料编码',
        split_amt DOUBLE(20,2) COMMENT '拆分数量',
        status INT COMMENT '状态 0:待处理 1:有效 2:无效',
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜品拆分配置表';
CREATE TABLE
    wms_material_stock
    (
        id bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
        cabin_code VARCHAR(35) NOT NULL COMMENT '货栈代码',
        cabin_name VARCHAR(128) NOT NULL COMMENT '货栈名称',
        cabin_type VARCHAR(1) NOT NULL COMMENT '货栈类型 1:仓库 2:门店',
        material_code VARCHAR(35) NOT NULL COMMENT '原料编码',
        material_name VARCHAR(50) NOT NULL COMMENT '材料名称',
        curr_stock DECIMAL(20,2) NOT NULL COMMENT '当前库存',
        stock_unit VARCHAR(10) NOT NULL COMMENT '单位，如克，条，箱等',
        modifier VARCHAR(64),
        gmt_modified DATETIME NOT NULL COMMENT '修改日期',
        check_stock_time DATETIME COMMENT '上次盘点库存时间',
        check_operator VARCHAR(32) COMMENT '盘点人员',
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE
    wms_material_stock_history
    (
        id bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
        material_name VARCHAR(50) COMMENT '材料名称',
        material_code VARCHAR(35) COMMENT '原料编码',
        cabin_code VARCHAR(35) COMMENT '货栈代码',
        cabin_name VARCHAR(128) COMMENT '货栈名称',
        keep_days VARCHAR(32),
        cabin_type VARCHAR(1) COMMENT '货栈类型 1:仓库 2:门店',
        total_price DECIMAL(12,2) COMMENT '货物总价',
        unit_price DECIMAL(12,2) COMMENT '货物单价',
        amt DECIMAL(20,2) COMMENT '增加数量，入库时为正，出库时为负',
        pre_stock DECIMAL(20,2) COMMENT '变化前的库存',
        post_stock DECIMAL(20,2) COMMENT '变化后的库存',
        product_date DATETIME COMMENT '生产日期',
        stock_unit VARCHAR(10) COMMENT '库存单位',
        op_type VARCHAR(30) COMMENT '流水类型,如入库、出库、盘点',
        relate_code VARCHAR(64) COMMENT '关联单',
        operator VARCHAR(30) COMMENT '操作人',
        remark VARCHAR(1024) COMMENT '备注',
        gmt_created DATETIME NOT NULL COMMENT '创建时间',
        status INT COMMENT '记录状态 0:初始值 1:已处理 2:废弃 9:处理中',
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE
    wms_material_supplier
    (
        id bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
        material_name VARCHAR(50) NOT NULL COMMENT '原料名称',
        material_code VARCHAR(35) NOT NULL COMMENT '原料编码',
        supplier_code VARCHAR(32) NOT NULL COMMENT '供货商编码',
        supplier_name VARCHAR(128) NOT NULL COMMENT '供货商名称',
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供货商';
CREATE TABLE
    wms_menu
    (
        id bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
        menu_name VARCHAR(50) COMMENT '菜单名称',
        menu_code VARCHAR(20) COMMENT '菜单CODE',
        menu_icon VARCHAR(128) COMMENT '菜名图标',
        menu_index VARCHAR(50) COMMENT '菜单导航',
        parent_code VARCHAR(20) COMMENT '父级菜单',
        status INT COMMENT '状态 0:待处理 1:有效 2:无效',
        roles VARCHAR(32) COMMENT '授权角色',
        order_by INT,
        type VARCHAR(10),
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE
    wms_notice
    (
        id bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
        title VARCHAR(256) COMMENT '标题',
        content VARCHAR(1024) COMMENT '显示内容',
        msg_type VARCHAR(32) COMMENT '信息类型',
        status VARCHAR(1) COMMENT '状态 0:草稿 1:有效 2:撤销 3:已处理 4:到期',
        gmt_created DATETIME COMMENT '创建时间',
        gmt_expired DATETIME COMMENT '到期时间',
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE
    wms_recipes
    (
        id bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
        recipes_name VARCHAR(50) COMMENT '食谱名称',
        recipes_code VARCHAR(35) COMMENT '食谱编码',
        store_code VARCHAR(35) COMMENT '门店，默认:DEFAULT',
        out_code VARCHAR(35) COMMENT '外部代码',
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='食谱';
CREATE TABLE
    wms_recipes_formula
    (
        id bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
        recipes_name VARCHAR(50) COMMENT '食谱名称',
        recipes_code VARCHAR(35) COMMENT '食谱编码',
        material_name VARCHAR(50) COMMENT '食材名称',
        material_code VARCHAR(35) COMMENT '食材编码',
        material_amt DOUBLE COMMENT '食材数量',
        material_unit VARCHAR(10) COMMENT '食材单位',
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='食谱配方';
CREATE TABLE
    wms_sequence
    (
        id bigint NOT NULL AUTO_INCREMENT,
        sequence_code VARCHAR(64) NOT NULL,
        next_value bigint DEFAULT '0',
        modify_version bigint DEFAULT '0',
        PRIMARY KEY (id),
        CONSTRAINT uni_seq_code UNIQUE (sequence_code)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE
    wms_session
    (
        id bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
        session_id VARCHAR(50),
        user_code VARCHAR(35) COMMENT '用户CODE',
        expired_time DATETIME COMMENT '过期时间',
        user_info VARCHAR(1024) COMMENT '用户信息',
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='session信息';
CREATE TABLE
    wms_store
    (
        id bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
        store_code VARCHAR(35) COMMENT '门店编码',
        store_name VARCHAR(100) COMMENT '门店名称',
        store_addr VARCHAR(1024) COMMENT '门店地址',
        store_manager VARCHAR(128) COMMENT '负责人',
        manager_phone VARCHAR(64) COMMENT '负责人手机',
        manager_email VARCHAR(64) COMMENT '邮箱',
        out_code VARCHAR(35) COMMENT '外部代码',
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='门店信息';
CREATE TABLE
    wms_supplier
    (
        id bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
        supplier_code VARCHAR(64) NOT NULL COMMENT '供应商编码',
        supplier_name VARCHAR(128) NOT NULL COMMENT '供应商名称',
        short_name VARCHAR(128) NOT NULL COMMENT '简称',
        supplier_addr VARCHAR(256) COMMENT '供应商地址',
        pay_mode VARCHAR(32) COMMENT '结账模式',
        pay_way VARCHAR(32) COMMENT '支付方式',
        pay_account VARCHAR(32) COMMENT '结算账户',
        bank_name VARCHAR(256) COMMENT '银行名称',
        supplier_tel VARCHAR(32) COMMENT '供应商电话',
        supplier_phone VARCHAR(32) COMMENT '供应商手机',
        supplier_email VARCHAR(64) COMMENT '供应商邮箱',
        status VARCHAR(1) NOT NULL COMMENT '有效性 0:待审核 1:有效 2:无效',
        remark VARCHAR(1024) COMMENT '备注',
        modifer VARCHAR(35) COMMENT '最近修改人员',
        gmt_modified DATETIME COMMENT '修改时间',
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE
    wms_user
    (
        id bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
        user_code VARCHAR(35) COMMENT '用户编码',
        user_name VARCHAR(128) COMMENT '用户名称',
        user_mobile VARCHAR(15) COMMENT '用户手机号',
        store_code VARCHAR(35) COMMENT '用户归属门店',
        user_role VARCHAR(50) COMMENT '用户角色',
        password VARCHAR(35) COMMENT '用户密码',
        status VARCHAR(1) DEFAULT '1' COMMENT '用户状态 1:有效 2:无效',
        auth_stores VARCHAR(256) COMMENT '授权门店',
        auth_warehouse VARCHAR(256) COMMENT '授权门店',
        PRIMARY KEY (id),
        CONSTRAINT uni_user_code UNIQUE (user_code)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息';
CREATE TABLE
    wms_warehouse
    (
        id bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
        warehouse_code VARCHAR(35) NOT NULL COMMENT '仓库代码',
        warehouse_name VARCHAR(128) NOT NULL COMMENT '仓库名称',
        warehouse_addr VARCHAR(512) COMMENT '仓库地址',
        warehouse_manager VARCHAR(128) COMMENT '仓库负责人',
        manager_phone VARCHAR(16) COMMENT '负责人手机号',
        manager_email VARCHAR(64) COMMENT '负责人邮箱',
        related_store VARCHAR(512) COMMENT '关联门店,用逗号隔开',
        PRIMARY KEY (id),
        CONSTRAINT uni_wh_code UNIQUE (warehouse_code)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='仓库';

create table wms_upload_files(
   id bigint primary key auto_increment comment '主键',
   busi_no varchar(64) not null comment '业务代码',
   content_type varchar(64) not null comment '文件类型(http)',
   file_location varchar(64) not null comment '文件存储位置,local表示本地',
   file_path varchar(128) not null comment '文件存储路径,file_location=local时保存为本地目录',
   file_name varchar(128) not null comment '文件名称',
   file_ori_name varchar(128) comment '文件原始名称',
   remark varchar(256) comment '备注',
   creator varchar(35) comment '创建人员',
   gmt_created datetime comment '创建时间'
);

alter table wms_inventory_apply_detail add img_busi_no varchar(64) after keep_time;


INSERT INTO wms_menu ( menu_name, menu_code, menu_icon, menu_index, parent_code, status, roles, order_by, type) VALUES ('首页', 'index', 'el-icon-location', 'home', 'root', 1, 'all', 1, 'link');
INSERT INTO wms_menu ( menu_name, menu_code, menu_icon, menu_index, parent_code, status, roles, order_by, type) VALUES ('系统管理', 'sys_nav', 'el-icon-setting', '2', 'root', 1, 'all', 2, 'nav');
INSERT INTO wms_menu ( menu_name, menu_code, menu_icon, menu_index, parent_code, status, roles, order_by, type) VALUES ('基础信息', 'base_nav', 'el-icon-menu', '3', 'root', 1, 'all', 3, 'nav');
INSERT INTO wms_menu ( menu_name, menu_code, menu_icon, menu_index, parent_code, status, roles, order_by, type) VALUES ('进销存', 'inventory_nav', 'el-icon-menu', '4', 'root', 1, 'all', 4, 'nav');
INSERT INTO wms_menu ( menu_name, menu_code, menu_icon, menu_index, parent_code, status, roles, order_by, type) VALUES ('报表模块', 'task_nav', 'el-icon-menu', '5', 'root', 1, 'all', 5, 'nav');
INSERT INTO wms_menu ( menu_name, menu_code, menu_icon, menu_index, parent_code, status, roles, order_by, type) VALUES ('我的任务', 'my_nav', 'el-icon-menu', '6', 'root', 1, 'all', 6, 'nav');
INSERT INTO wms_menu ( menu_name, menu_code, menu_icon, menu_index, parent_code, status, roles, order_by, type) VALUES ('用户管理', 'userManage', null, 'userManage', 'sys_nav', 1, 'all', 2, 'nav');
INSERT INTO wms_menu ( menu_name, menu_code, menu_icon, menu_index, parent_code, status, roles, order_by, type) VALUES ('系统设置', 'sysConfig', null, 'sysConfig', 'sys_nav', 1, 'all', 2, 'nav');
INSERT INTO wms_menu ( menu_name, menu_code, menu_icon, menu_index, parent_code, status, roles, order_by, type) VALUES ('原料管理', 'materialManage', null, 'materialManage', 'base_nav', 1, 'all', null, 'link');
INSERT INTO wms_menu ( menu_name, menu_code, menu_icon, menu_index, parent_code, status, roles, order_by, type) VALUES ( '菜品管理', 'recipesManage', null, 'recipesManage', 'base_nav', 1, 'all', null, 'link');
INSERT INTO wms_menu ( menu_name, menu_code, menu_icon, menu_index, parent_code, status, roles, order_by, type) VALUES ( '仓库管理', 'warehouseManage', null, 'warehouseManage', 'base_nav', 1, 'all', null, 'link');
INSERT INTO wms_menu ( menu_name, menu_code, menu_icon, menu_index, parent_code, status, roles, order_by, type) VALUES ( '门店管理', 'storeManage', null, 'storeManage', 'base_nav', 1, 'all', null, 'link');
INSERT INTO wms_menu ( menu_name, menu_code, menu_icon, menu_index, parent_code, status, roles, order_by, type) VALUES ( '供应商管理', 'supplierManage', null, 'supplierManage', 'base_nav', 1, 'all', null, 'link');
INSERT INTO wms_menu ( menu_name, menu_code, menu_icon, menu_index, parent_code, status, roles, order_by, type) VALUES ( '入库管理', 'inventoryIn', null, 'inventoryIn', 'inventory_nav', 1, 'all', null, 'link');
INSERT INTO wms_menu ( menu_name, menu_code, menu_icon, menu_index, parent_code, status, roles, order_by, type) VALUES ( '采购单录入', 'purchaseOrder', null, 'purchaseOrder', 'inventory_nav', 1, 'all', null, 'link');
INSERT INTO wms_menu ( menu_name, menu_code, menu_icon, menu_index, parent_code, status, roles, order_by, type) VALUES ( '调拨单录入', 'diaobo', null, 'diaobo', 'inventory_nav', 1, 'all', null, 'link');
INSERT INTO wms_menu ( menu_name, menu_code, menu_icon, menu_index, parent_code, status, roles, order_by, type) VALUES ( '物料报损', 'baosun', null, 'baosun', 'inventory_nav', 1, 'all', null, 'link');
INSERT INTO wms_menu ( menu_name, menu_code, menu_icon, menu_index, parent_code, status, roles, order_by, type) VALUES ( '盘点库存', 'pandian', null, 'pandian', 'inventory_nav', 1, 'all', null, 'link');
INSERT INTO wms_menu ( menu_name, menu_code, menu_icon, menu_index, parent_code, status, roles, order_by, type) VALUES ( '出库管理', 'outStock', null, 'inventoryOut', 'inventory_nav', 0, 'all', null, 'link');

INSERT INTO wms_recipes ( recipes_name, recipes_code, store_code, out_code) VALUES ( '生鱼片', 'CD000001', null, '111');
INSERT INTO wms_recipes ( recipes_name, recipes_code, store_code, out_code) VALUES ( '涮牛肉火锅', 'CD000002', null, null);
INSERT INTO wms_recipes ( recipes_name, recipes_code, store_code, out_code) VALUES ( '什锦生鱼片', 'CD000003', null, null);
INSERT INTO wms_recipes ( recipes_name, recipes_code, store_code, out_code) VALUES ( '什锦生鱼片船', 'CD000004', null, null);
INSERT INTO wms_recipes ( recipes_name, recipes_code, store_code, out_code) VALUES ( '汁烤鸡排', 'CD000005', null, null);
INSERT INTO wms_recipes ( recipes_name, recipes_code, store_code, out_code) VALUES ( '盐烤三文鱼', 'CD000006', null, null);
INSERT INTO wms_recipes ( recipes_name, recipes_code, store_code, out_code) VALUES ( '煮加吉鱼头', 'CD000007', null, null);
INSERT INTO wms_recipes ( recipes_name, recipes_code, store_code, out_code) VALUES ( '什锦天妇罗', 'CD000008', null, null);

    
INSERT INTO wms_material ( material_name, material_code, spec_unit, spec_qty, stock_unit, status, search_key, utilization_ratio, storage_life) VALUES ( '香鱼', 'M00003', '无', 0.00, '个', 1, 'xy,xiangyu', 100, '11D');
INSERT INTO wms_material ( material_name, material_code, spec_unit, spec_qty, stock_unit, status, search_key, utilization_ratio, storage_life) VALUES ( '河鳗', 'M00004', '无', 0.00, '个', 1, 'heman,hm', 100, '11D');
INSERT INTO wms_material ( material_name, material_code, spec_unit, spec_qty, stock_unit, status, search_key, utilization_ratio, storage_life) VALUES ( '鲍鱼', 'M00005', '包', 12.00, '千克', 1, 'by,baoyu', 100, '11D');
INSERT INTO wms_material ( material_name, material_code, spec_unit, spec_qty, stock_unit, status, search_key, utilization_ratio, storage_life) VALUES ( '带子', 'M00006', '箱', 12.00, '千克', 1, 'daizi,dz', 100, '12D');
INSERT INTO wms_material ( material_name, material_code, spec_unit, spec_qty, stock_unit, status, search_key, utilization_ratio, storage_life) VALUES ( '鲽鱼', 'M00007', '包', 12.00, '千克', 1, 'dy,dieyu', 100, '12D');
INSERT INTO wms_material ( material_name, material_code, spec_unit, spec_qty, stock_unit, status, search_key, utilization_ratio, storage_life) VALUES ( '自海胆', 'M00008', '包', 14.00, '千克', 1, 'zhd,zihaidan', 100, '3D');
INSERT INTO wms_material ( material_name, material_code, spec_unit, spec_qty, stock_unit, status, search_key, utilization_ratio, storage_life) VALUES ( '秋刀鱼', 'M00009', '无', 0.00, '千克', 1, 'qdy,qiudaoyu', 100, '11D');
INSERT INTO wms_material ( material_name, material_code, spec_unit, spec_qty, stock_unit, status, search_key, utilization_ratio, storage_life) VALUES ( '明石鲷', 'M00010', '无', 0.00, '个', 1, 'mingshidiao,msd', 100, '12D');
INSERT INTO wms_material ( material_name, material_code, spec_unit, spec_qty, stock_unit, status, search_key, utilization_ratio, storage_life) VALUES ( '萝卜', 'M00011', '无', 0.00, '千克', 1, 'lb,luobo', 100, '11D');
INSERT INTO wms_material ( material_name, material_code, spec_unit, spec_qty, stock_unit, status, search_key, utilization_ratio, storage_life) VALUES ( '日本芜菁', 'M00012', '无', 0.00, '千克', 1, 'rbwj,ribenwujing', 100, '11D');
INSERT INTO wms_material ( material_name, material_code, spec_unit, spec_qty, stock_unit, status, search_key, utilization_ratio, storage_life) VALUES ( '秋葵', 'M00013', '包', 13.00, '千克', 1, 'qiukui,qk', 100, '11D');
INSERT INTO wms_material ( material_name, material_code, spec_unit, spec_qty, stock_unit, status, search_key, utilization_ratio, storage_life) VALUES ( '蕨菜', 'M00014', '无', 0.00, '千克', 1, 'juecai,jc', 100, '12D');
INSERT INTO wms_material ( material_name, material_code, spec_unit, spec_qty, stock_unit, status, search_key, utilization_ratio, storage_life) VALUES ( '莼菜', 'M00015', '包', 123.00, '千克', 1, 'cc,chuncai', 100, '12D');

INSERT INTO wms_sequence ( sequence_code, next_value, modify_version) VALUES ( 'wms_material', 16, 16);
INSERT INTO wms_sequence ( sequence_code, next_value, modify_version) VALUES ( 'wms_recipes', 9, 9);
INSERT INTO wms_sequence ( sequence_code, next_value, modify_version) VALUES ( 'wms_warehouse', 3, 3);
INSERT INTO wms_sequence ( sequence_code, next_value, modify_version) VALUES ( 'wms_store', 6, 6);
INSERT INTO wms_sequence ( sequence_code, next_value, modify_version) VALUES ( 'wms_supplier', 4, 4);


INSERT INTO wms_store ( store_code, store_name, store_addr, store_manager, manager_phone, manager_email, out_code) VALUES ( 'MD0001', '常州店', '门店地址', '负责人', '负责人手机', '负责人邮箱', '123');
INSERT INTO wms_store ( store_code, store_name, store_addr, store_manager, manager_phone, manager_email, out_code) VALUES ( 'MD0002', '上海店', null, null, null, null, null);
INSERT INTO wms_store ( store_code, store_name, store_addr, store_manager, manager_phone, manager_email, out_code) VALUES ('MD0003', '南通店', null, null, null, null, null);
INSERT INTO wms_store ( store_code, store_name, store_addr, store_manager, manager_phone, manager_email, out_code) VALUES ('MD0004', '无锡店', null, null, null, null, null);
INSERT INTO wms_store ( store_code, store_name, store_addr, store_manager, manager_phone, manager_email, out_code) VALUES ( 'MD0005', '常州万达广场店', null, null, null, null, null);


INSERT INTO wms_supplier ( supplier_code, supplier_name, short_name, supplier_addr, pay_mode, pay_way, pay_account, bank_name, supplier_tel, supplier_phone, supplier_email, status, remark, modifer, gmt_modified) VALUES ('S0001', '海鲜供应商', '海鲜供应商', null, 'ByNow', 'bank', '234225354435355', '工商银行', null, null, null, '1', '发鼎折覆餗发多少', 'admin', '2017-11-21 23:03:50');
INSERT INTO wms_supplier ( supplier_code, supplier_name, short_name, supplier_addr, pay_mode, pay_way, pay_account, bank_name, supplier_tel, supplier_phone, supplier_email, status, remark, modifer, gmt_modified) VALUES ('S0002', '蔬菜供应商', '蔬菜供应商', null, 'ByWeek', 'alipay', null, 'alipay', null, null, null, '1', null, 'admin', '2017-11-20 23:00:48');
INSERT INTO wms_supplier ( supplier_code, supplier_name, short_name, supplier_addr, pay_mode, pay_way, pay_account, bank_name, supplier_tel, supplier_phone, supplier_email, status, remark, modifer, gmt_modified) VALUES ('S0003', '崇明新鲜蔬菜供应', '崇明新鲜蔬菜供应', null, null, 'alipay', null, 'alipay', null, null, null, '1', null, 'admin', '2017-11-20 23:01:21');

INSERT INTO wms_user ( user_code, user_name, user_mobile, store_code, user_role, password, status, auth_stores, auth_warehouse) VALUES ('admin', '管理员', null, null, '1', 'E10ADC3949BA59ABBE56E057F20F883E', '1', null, null);
INSERT INTO wms_user ( user_code, user_name, user_mobile, store_code, user_role, password, status, auth_stores, auth_warehouse) VALUES ('yinguoliang', '尹国良', null, null, '1', 'E10ADC3949BA59ABBE56E057F20F883E', '1', null, null);

INSERT INTO wms_warehouse (warehouse_code, warehouse_name, warehouse_addr, warehouse_manager, manager_phone, manager_email, related_store) VALUES ('WH0001', '崇明仓库', null, null, null, null, null);
INSERT INTO wms_warehouse ( warehouse_code, warehouse_name, warehouse_addr, warehouse_manager, manager_phone, manager_email, related_store) VALUES ( 'WH0002', '昆山仓库', null, null, null, null, null);

INSERT INTO wms_dict (dict_group, dict_code, dict_name, dict_val) VALUES ( 'DEFAULT', 'image_path', '保存路劲', '/root/pictures/');
