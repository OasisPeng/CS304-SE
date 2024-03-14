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


