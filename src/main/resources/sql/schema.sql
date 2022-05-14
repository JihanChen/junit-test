DROP TABLE IF EXISTS `user`;
create table  `user`(
    `user_id` varchar(255) comment '用户id',
    `user_name` varchar(255)  comment '用户名',
    `password` varchar(255) comment '密码',
    PRIMARY KEY (`user_id`)
);