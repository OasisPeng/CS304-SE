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


