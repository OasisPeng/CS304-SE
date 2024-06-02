create table ai
(
    content text null,
    id      int auto_increment
        primary key,
    user    int  null
);

create table course
(
    id             int auto_increment comment '主键ID'
        primary key,
    trainingType   varchar(255) null comment '培训类型',
    courseCode     varchar(255) null comment '课程代码',
    chineseName    varchar(255) null comment '中文课程名称',
    englishName    varchar(255) null comment '英文课程名称',
    courseNature   varchar(255) null comment '课程性质',
    courseCategory varchar(255) null comment '课程类别',
    language       varchar(255) null comment '授课语言',
    credits        double       null comment '学分',
    hours          double       null comment '学时',
    department     varchar(255) null comment '开课院系',
    teacher        varchar(255) null comment '授课老师'
)
    comment '课程表';

create table dafen
(
    course_id int           null,
    score     int default 0 null,
    user_id   int           null
);

create table dianzan
(
    course_id int null,
    user_id   int null
);

create table event
(
    id       int auto_increment comment '主键ID'
        primary key,
    title    varchar(255)   not null comment '标题',
    owner    varchar(255)   not null comment '创建者学号/id',
    week     int            null comment '第几周',
    xq       int            null comment '星期',
    level    varchar(255)   null comment '待办事件紧急程度',
    finish   int            null comment '待办是否完成',
    category varchar(255)   null comment '待办类别',
    emotion  varchar(255)   null comment '心情',
    text     varchar(10000) null comment '内容'
)
    comment '待办事件';

create table favourite
(
    user_id int null,
    good_id int null
);

create table follow
(
    following int null,
    follower  int null
);

create table goods
(
    id           bigint auto_increment
        primary key,
    name         varchar(255)                       not null,
    price        decimal(10, 2)                     not null,
    image        varchar(255)                       null,
    seller_id    varchar(20)                        not null,
    buyer_id     varchar(20)                        null,
    description  text                               null,
    category     varchar(255)                       null,
    publish_date datetime default CURRENT_TIMESTAMP not null
);

create index idx_buyer_id
    on goods (buyer_id);

create index idx_category
    on goods (category);

create index idx_seller_id
    on goods (seller_id);

create table have_buy
(
    user_id int null,
    good_id int null
);

create table message
(
    id     int auto_increment
        primary key,
    `from` int           null,
    `to`   int           null,
    text   text          null,
    time   timestamp     null,
    old    int default 1 null
);

create table pinglun
(
    course_id int          null,
    user_id   int          not null,
    content   varchar(999) null,
    id        int auto_increment
        primary key
);


