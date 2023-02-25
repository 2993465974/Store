create DATABASE phonestore;
use phonestore;
create table category(
    cid int primary key auto_increment,
    cname varchar(55) not null unique
);
create table goods(
    gid int primary key auto_increment,
    gname varchar(55) not null,
    images1 varchar(55) not null,
    images2 varchar(55) not null,
    price double not null,
    stock int not null,
    intro varchar(255) not null,
    categoryId int not null,
    foreign key(categoryId) references category(cid)
);

create table users(
    uid int primary key auto_increment,
    username varchar(55) not null unique,
    password varchar(55) not null,
    realname varchar(50),
    phone varchar(50),
    address varchar(300)
);

create table recommend(
rid int primary key auto_increment comment '推荐编号',
type tinyint(6) comment '推荐类型(1条幅/2热销/3性价比)',
goodsgid int comment '商品编号'
);

#管理员表

create table admins(
    id int primary key  auto_increment comment '管理员编号',
    username varchar(100) not null unique comment '管理员',
    password varchar(100) not null comment '管理员密码'

);


create table orders(
    oid int primary key auto_increment comment '订单编号',
    total double(16,2) not null comment '总价格',
    amount int not null comment '商品总数',
    status int not null comment '订单状态(1未付款、2已付款、3已发货、4已收货)',
    paytype int not null comment '支付方式(1微信、2支付宝、3货到付款)',
    realname varchar(50) not null comment '收货人',
    phone varchar(50) not null comment '收货电话',
    address varchar(255) not null comment '收货地址',
    systime timestamp null default CURRENT_TIMESTAMP comment '下单时间',
    userId int comment '下单用户ID'
);
create table orderItem(
 id int primary key auto_increment comment '订单编号',
 price double comment '订单项单价',
 amount int comment '订单项数量',
 orderId    int comment '订单编号',
 goodsId int comment '商品编号',
 total double comment '总价'
);