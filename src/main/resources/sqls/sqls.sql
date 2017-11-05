CREATE TABLE
    wms_material
    (
        id bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
        material_name VARCHAR(50) NOT NULL COMMENT '原料名称',
        material_code VARCHAR(35) NOT NULL COMMENT '原料编码',
        can_split VARCHAR(1) NOT NULL COMMENT '是否可拆分 Y:是 N:否',
        stock_unit VARCHAR(10) COMMENT '单位，如克，条，箱等',
        status INT COMMENT '状态 0:待处理 1:有效 2:无效',
        search_key VARCHAR(512) COMMENT '搜索key,以逗号隔开',
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE
    wms_material_split_cfg
    (
        id bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
        material_name VARCHAR(50) NOT NULL COMMENT '原料名称',
        material_code VARCHAR(35) NOT NULL COMMENT '原料编码',
        split_name VARCHAR(1) NOT NULL COMMENT '拆分子原料名称',
        split_code VARCHAR(10) COMMENT '拆分子原料编码',
        split_amt DOUBLE(20,2) COMMENT '拆分数量',
        status INT COMMENT '状态 0:待处理 1:有效 2:无效',
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜品拆分配置表';  
    
CREATE TABLE
    wms_material_stock
    (
        id bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
        material_name VARCHAR(50) COMMENT '材料名称',
        material_code VARCHAR(35) COMMENT '原料编码',
        curr_stock DECIMAL(20,2) COMMENT '当前库存',
        used_stock DECIMAL(20,2) COMMENT '消耗库存',
        stock_type VARCHAR(1) COMMENT '库存类型 1:总库, 2:分库',
        stock_unit VARCHAR(10) COMMENT '单位，如克，条，箱等',
        modifier VARCHAR(64),
        warehouse_code VARCHAR(35) COMMENT '仓库代码',
        warehouse_name VARCHAR(128) COMMENT '仓库名称',
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
CREATE TABLE
    wms_material_stock_history
    (
        id bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
        material_name VARCHAR(50) COMMENT '材料名称',
        material_code VARCHAR(35) COMMENT '原料编码',
        op_type VARCHAR(30) COMMENT '流水类型,如入库、出库、盘点',
        stock_chg DECIMAL(20,2) COMMENT '库存变化，正数表示增加，负数表示减少',
        curr_stock DECIMAL(20,2) COMMENT '库存剩余',
        operator VARCHAR(30) COMMENT '操作人',
        warehouse_code VARCHAR(35) COMMENT '仓库地址',
        store_code VARCHAR(35) COMMENT '门店',
        remark VARCHAR(1024) COMMENT '备注',
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
        menu_index VARCHAR(50) COMMENT '菜单导航',
        menu_level INT COMMENT '菜单层级 0:根菜单 1:一级菜单',
        parent_code VARCHAR(20) COMMENT '父级菜单',
        status INT COMMENT '状态 0:待处理 1:有效 2:无效',
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
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='门店信息';    
    
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
        PRIMARY KEY (id),
        CONSTRAINT uni_wh_code UNIQUE (warehouse_code)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='仓库';    