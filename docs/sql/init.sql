set names utf8mb4;
create database if not exists `code_manage_system` default character set utf8mb4;
use `code_manage_system`;

drop table if exists `semester`;
create table `semester` (
    `id` int(10) unsigned not null auto_increment comment '自增id',
    `semester_id` int(6) not null unique comment '学期id',
    `name` varchar(32) not null comment '学期名称',
    `start_time` datetime not null  comment '开始时间',
    `end_time` datetime not null  comment '结束时间',
    `status` varchar(16) not null default 'disable' comment '状态',
    primary key (`id`)
) default charset = utf8mb4 comment = '学期表';

lock tables `semester` write ;

insert into `semester` (id, semester_id, name, start_time, end_time)
values
    (1, 232401, '2324学年第一学期', '2023-09-01 00:00:00', '2024-02-04 00:00:00'),
    (2, 232402, '2324学年第二学期', '2024-02-04 00:00:00', '2024-09-01 00:00:00'),
    (3, 242501, '2425学年第一学期', '2024-09-01 00:00:00', '2025-02-04 00:00:00'),
    (4, 242502, '2425学年第二学期', '2025-02-04 00:00:00', '2025-09-01 00:00:00');

unlock tables ;

drop table if exists `course`;
create table `course` (
    `id` int(10) unsigned not null auto_increment comment '自增id',
    `course_id` int(8) not null unique comment '课程id',
    `name` varchar(32) not null comment '课程名称',
    `start_time` datetime not null default current_timestamp comment '开始时间',
    `end_time` datetime not null default current_timestamp on update current_timestamp comment '结束时间',
    `status` varchar(16) not null default 'disable' comment '状态',
    primary key (`id`)
) default charset = utf8mb4 comment = '课程表';

lock tables `course` write ;
insert into `course` (id, course_id, name, start_time, end_time)
    values
        (1, 1001, '高等数学上', '2024-09-01 00:00:00', '2025-01-01 00:00:00'),
        (2, 1002, '高等数学下', '2024-09-01 00:00:00', '2025-01-01 00:00:00'),
        (3, 1003, '大学物理', '2024-09-01 00:00:00', '2025-01-01 00:00:00'),
        (4, 1004, '模拟电路', '2024-09-01 00:00:00', '2025-01-01 00:00:00');
unlock tables ;

drop table if exists `teacher`;
create table `teacher` (
    `id` int(10) not null auto_increment comment '自增id',
    `teacher_id` int(6) not null unique comment '教师id',
    `name` varchar(32) not null comment '教师名称',
    primary key (`id`)
) default charset = utf8mb4 comment = '教师表';

lock tables `teacher` write ;
insert into `teacher` (id, teacher_id, name)
values
    (1, 200001, '张三'),
    (2, 200002, '李四'),
    (3, 200003, '王五');
unlock tables ;

drop table if exists `class`;
create table `class` (
    `id` int(10) not null auto_increment comment '自增id',
    `class_id` int(10) not null unique comment '教学班级id',
    `name` varchar(32) not null comment '教学班级名称',
    `people_count` int(6) not null comment '班级人数',
    `semester_id` int(6) not null comment '学期id',
    `course_id` int(8) not null comment '课程id',
    `teacher_id` int(6) not null comment '教师id',
    primary key (`id`),
    foreign key (`semester_id`) references semester(`semester_id`),
    foreign key (`course_id`) references course(`course_id`),
    foreign key (`teacher_id`) references teacher(`teacher_id`)
) default charset = utf8mb4 comment '教学班级表';

lock tables `class` write ;
insert into `class` (id, class_id, name, people_count, semester_id, course_id, teacher_id)
values
    (1, 400001, '高数上一班', 50, 242501, 1001,  200001),
    (2, 400002, '高数下一班', 50, 242501, 1002,  200002),
    (3, 400003, '大物一班', 50, 242501, 1003, 200003),
    (4, 400004, '模电一班', 50, 242501, 1004, 200003);
unlock tables ;

drop table if exists `student`;
create table `student` (
    `id` int(10) not null auto_increment comment '自增id',
    `student_id` int(10) not null unique comment '学号',
    `name` varchar(16) not null comment '学生名称',
    `sex` varchar(4) not null comment '性别',
    `phone_number` varchar(16) not null comment '电话号码',
    `mail` varchar(32) not null comment '邮箱地址',
    primary key (`id`)
) default charset = utf8mb4 comment '学生表';

lock tables `student` write ;
insert into `student` (id, student_id, name, sex, phone_number, mail)
values
    (1, 300001, '刘七', '男', 111, 'test@csu.edu.cn'),
    (2, 300002, '孙八', '男', 111, 'test@csu.edu.cn'),
    (3, 300003, '赵六', '男', 111, 'test@csu.edu.cn');
unlock tables ;

drop table if exists `student_course`;
create table `student_course` (
    `id` int(10) not null auto_increment comment '自增id',
    `student_id` int(6) not null comment '学生id',
    `course_id` int(8) not null comment '课程id',
    primary key (`id`),
    foreign key (`student_id`) references student(`student_id`),
    foreign key (`course_id`) references course(`course_id`)
) default charset = utf8mb4 comment '学生选课表';

lock tables `student_course` write ;
insert into `student_course` (id, student_id, course_id)
values
    (1, 300001, 1001),
    (2, 300002, 1001);
unlock tables ;

drop table if exists `job`;
create table `job` (
    `id` int(10) not null auto_increment comment '自增id',
    `job_id` int(10) not null unique  comment '作业id',
    `title` varchar(64) not null comment '标题',
    `content` varchar(256) comment '内容',
    `course_id` int(8) not null comment '课程id',
    `class_id` int(10) not null comment '教学班级id',
    `status` varchar(16) not null comment '状态 enable | disable',
    `start_time` datetime not null comment '开始时间',
    `end_time` datetime not null comment '结束时间',
    primary key (`id`),
    foreign key (`course_id`) references course(`course_id`),
    foreign key (`class_id`) references class(`class_id`)
) default charset = utf8mb4 comment '作业表';

drop table if exists `student_job`;
create table `student_job` (
    `id` int(10) not null auto_increment comment '自增id',
    `job_id` int(10) not null comment '作业id',
    `student_id` int(6) not null comment '学生id',
    `status` varchar(16) not null comment '状态 created | submitted | stop',
    primary key (`id`),
    foreign key (`job_id`) references job(`job_id`),
    foreign key (`student_id`) references student(`student_id`)
)default charset = utf8mb4 comment '学生作业表';

drop table if exists `users`;
create table `users` (
    `id` int(10) not null auto_increment comment '自增id',
    `user_id` int(6) not null comment '用户id',
    `password` varchar(16) not null comment '密码',
    `type` varchar(8) not null comment '类型 student | admin | teacher',
    primary key (`id`)
) default charset = utf8mb4 comment '用户登录表';
lock tables `users` write ;
insert into `users` (id, user_id, password, type)
values
    (1, 100001, '123456', 'admin');
unlock tables ;
