create table address
(
  id bigint auto_increment
    primary key,
  user_id bigint not null,
  receiver varchar(64) not null,
  area varchar(256) not null,
  detail varchar(256) not null,
  type varchar(8) not null,
  phone varchar(16) not null,
  create_time datetime not null comment '创建时间',
  update_time datetime not null comment '更新时间'
)
;

create index INDEX_USER_ID
  on address (user_id)
;

create table admin_function
(
  id bigint auto_increment
    primary key,
  name varchar(32) not null comment '名称',
  state varchar(10) default 'open' not null comment '状态，open/closed',
  parent_id int(10) not null comment '父节点ID',
  url varchar(64) not null comment '链接',
  create_time datetime not null comment '创建时间',
  update_time datetime null comment '修改时间',
  constraint admin_func_url_UNIQUE
  unique (url)
)
  comment 'ERP菜单表'
;

create table admin_role
(
  id bigint auto_increment
    primary key,
  name varchar(50) not null comment '角色名',
  create_time datetime not null comment '创建时间',
  update_time datetime null comment '修改时间',
  constraint UNIQUE_ROLE_NAME
  unique (name)
)
;

create table admin_role_function
(
  id bigint auto_increment
    primary key,
  admin_role_id int(10) not null comment '角色ID',
  admin_func_id int(10) not null comment '功能ID',
  create_time datetime not null comment '创建时间',
  update_time datetime null comment '修改时间',
  constraint admin_role_func_UNIQUE
  unique (admin_role_id, admin_func_id)
)
  comment 'admin角色功能树对应关系表'
;

create table admin_user
(
  id bigint auto_increment
    primary key,
  name varchar(50) not null,
  password varchar(50) not null,
  create_time datetime not null comment '创建时间',
  update_time datetime null comment '修改时间',
  constraint admin_user_name_UNIQUE
  unique (name)
)
;

create table admin_user_role
(
  id bigint auto_increment
    primary key,
  admin_user_id int(10) not null comment '用户ID',
  admin_role_id int(10) not null comment '角色ID',
  create_time datetime not null comment '创建时间',
  update_time datetime null comment '修改时间'
)
  comment 'admin用户角色对应关系表'
;

create index admin_user_role_id
  on admin_user_role (admin_user_id, admin_role_id)
;

create table area
(
  id bigint auto_increment
    primary key,
  name varchar(32) not null comment '名称',
  parent_id bigint not null,
  common int(4) not null,
  type varchar(16) not null comment '类型:省,市',
  create_time datetime not null comment '创建时间',
  update_time datetime not null comment '更新时间',
  constraint area_name_UNIQUE
  unique (name)
)
;

create table cart
(
  id bigint auto_increment
    primary key,
  user_id bigint not null,
  deal_id bigint not null,
  deal_sku_id bigint not null,
  count int(4) not null,
  create_time datetime not null comment '创建时间',
  update_time datetime not null comment '更新时间'
)
;

create index cart_user_id
  on cart (user_id)
;

create table deal
(
  id bigint auto_increment
    primary key,
  area_id bigint not null comment '归属地',
  area_name varchar(64) not null comment '归属地名称',
  sku_id bigint not null comment '商品ID',
  deal_class int(2) not null comment '商品类型',
  merchant_id bigint not null comment '厂商ID',
  merchant_sku bigint not null comment '厂商sku',
  deal_title varchar(200) not null comment '商品标题',
  deal_price decimal not null comment '商品价格',
  merchant_price decimal not null comment '进货价',
  market_price decimal not null comment '市场价',
  settlement_price decimal not null,
  settlement_price_max decimal null comment '最大可接受结算价格',
  discount int(3) null comment '折扣',
  bonus_points int(5) null comment '积分',
  deal_type int(3) not null comment '商品类型',
  image_id bigint default '0' null comment '对应商品图片',
  deal_level int(4) not null comment '商品优先级',
  max_purchase_count int(4) null comment '最大可购买数量',
  publish_status int(2) not null comment '发布状态',
  inventory_amount int(4) not null comment '商品库存数量',
  vendibility_amount int(4) not null comment '商品可售数量',
  oos_status int(2) not null comment '售光标识',
  start_time datetime not null comment '销售开始时间',
  end_time datetime null comment '销售结束时间',
  publish_time datetime null comment '发布时间',
  merchant_code varchar(15) null comment '商家编码',
  create_time datetime not null comment '创建时间',
  update_time datetime not null comment '更新时间',
  category_id bigint not null comment '商品类别ID',
  constraint deal_sku_UNIQUE
  unique (sku_id)
)
;

create table deal_category
(
  id bigint auto_increment comment 'ID'
    primary key,
  parent_id bigint not null comment '父ID',
  name varchar(100) not null comment '名称',
  url_name varchar(32) not null comment '分类URL名称',
  publish_status int(2) not null comment '发布状态',
  create_time datetime not null comment '创建时间',
  order_num int(10) unsigned not null comment '排序号码',
  deep int(10) unsigned not null comment '层次深度',
  constraint deal_category_name_UNIQUE
  unique (name),
  constraint deal_category_url_name_UNIQUE
  unique (url_name)
)
;

create table deal_detail
(
  id bigint not null
    primary key,
  deal_id bigint not null,
  deal_detail varchar(8000) null,
  constraint detail_deal_id_UNIQUE
  unique (deal_id)
)
;

create table favorite
(
  id bigint auto_increment
    primary key,
  user_id bigint not null,
  deal_id bigint not null,
  deal_sku_id bigint not null,
  create_time datetime not null comment '创建时间',
  update_time datetime not null comment '更新时间'
)
;

create index favorite_user_deal_id
  on favorite (user_id, deal_id)
;

create table image_info
(
  id bigint auto_increment comment 'Id'
    primary key,
  width int(4) null comment '图片的宽',
  height int(4) null comment '图片的高',
  source_path varchar(100) null comment '图片的源路径'
)
;

create table merchant
(
  id bigint auto_increment comment '商家ID'
    primary key,
  name varchar(32) default '' not null comment '商家名称',
  description varchar(200) default '' not null comment '商家描述',
  image_id bigint not null comment '关联图片',
  level int(4) not null comment '商家级别',
  hot_level int(4) not null comment '热度等级',
  status int(2) not null comment '发布状态',
  create_time datetime not null comment '创建时间',
  update_time datetime null comment '修改时间',
  url varchar(100) not null comment '商家URL',
  constraint merchant_name_UNIQUE
  unique (name)
)
;

create table message
(
  id bigint auto_increment
    primary key,
  user_id bigint not null,
  title varchar(64) not null,
  content varchar(256) not null,
  readed varchar(4) not null,
  create_time datetime not null comment '创建时间',
  update_time datetime not null comment '更新时间'
)
;

create index message_user_INDEX
  on message (user_id)
;

create table order_basic
(
  id bigint auto_increment
    primary key,
  user_id bigint not null comment '用户ID',
  order_status int not null comment '订单状态',
  create_time datetime not null comment '创建时间',
  update_time datetime not null comment '更新时间',
  total_price int not null comment '订单总价',
  total_settlement_price int not null,
  address mediumtext null comment '收货地址',
  receiver varchar(128) null comment '收件人',
  phone varchar(20) null comment '电话',
  pay_type int(2) default '0' null comment '支付方式，1：微信，2：支付宝，3：货到付款'
)
;

create index order_user_INDEX
  on order_basic (user_id)
;

create table order_detail
(
  id bigint auto_increment
    primary key,
  order_id bigint not null comment '订单ID',
  user_id bigint not null comment '用户ID',
  merchant_sku int(20) null comment '商家商品SKU',
  merchant_id bigint null comment '商家编码',
  merchant_code varchar(32) null comment '商家编码',
  deal_id bigint not null comment 'deal ID',
  deal_sku_id bigint not null,
  deal_img_id bigint not null,
  deal_title varchar(200) not null comment 'deal名称',
  deal_count int not null comment 'Deal数量',
  deal_price int not null comment 'Deal单价',
  total_price int not null comment 'Deal总价',
  settlement_price int not null,
  total_settlement_price int not null,
  detail_status int not null comment '详情状态',
  create_time datetime not null comment '创建时间',
  update_time datetime not null comment '更新时间'
)
;

create index detail_order_id_UNIQUE
  on order_detail (order_id)
;

create index detail_user_id_INDEX
  on order_detail (user_id)
;

create table start_remind
(
  id bigint auto_increment
    primary key,
  user_id bigint not null,
  deal_id varchar(64) not null,
  deal_sku_id varchar(64) not null,
  deal_title varchar(200) not null,
  start_time datetime not null comment '开团时间',
  create_time datetime not null comment '创建时间',
  update_time datetime not null comment '更新时间',
  constraint remind_user_deal_UNIQUE
  unique (user_id, deal_id)
)
;

create index remind_user_INDEX
  on start_remind (user_id)
;

create table user
(
  id int(10) auto_increment
    primary key,
  password varchar(45) not null,
  name varchar(45) not null,
  login_time datetime not null,
  create_time datetime not null,
  update_time datetime not null,
  constraint user_name_UNIQUE
  unique (name)
)
;

create table user_basic_info
(
  id int(10) not null
    primary key,
  nickname varchar(32) not null,
  real_name varchar(32) not null,
  mail varchar(32) not null,
  phone varchar(16) not null,
  create_time datetime not null,
  update_time datetime not null
)
;

