-- 使用数据库
use big_event;

-- 用户表
create table user (
                      id int unsigned primary key auto_increment comment 'ID',
                      username varchar(20) not null unique comment '用户名',
                      password varchar(32) comment '密码',
                      nickname varchar(10) default '' comment '昵称',
                      email varchar(128) default '' comment '邮箱',
                      user_pic varchar(128) default '' comment '头像',
                      create_time datetime not null comment '创建时间',
                      update_time datetime not null comment '修改时间'
) comment '用户表';

-- 分类表
create table category(
                         id int unsigned primary key auto_increment comment 'ID',
                         category_name varchar(32) not null comment '分类名称',
                         category_alias varchar(32) not null comment '分类别名',
                         create_user int unsigned not null comment '创建人ID',
                         create_time datetime not null comment '创建时间',
                         update_time datetime not null comment '修改时间',
                         constraint fk_category_user foreign key (create_user) references user(id) -- 外键约束
);

-- 文章表
create table article(
                        id int unsigned primary key auto_increment comment 'ID',
                        title varchar(30) not null comment '文章标题',
                        content varchar(10000) not null comment '文章内容',
                        cover_img varchar(128) not null comment '文章封面',
                        state varchar(3) default '草稿' comment '文章状态: 只能是[已发布] 或者 [草稿]',
                        category_id int unsigned comment '文章分类ID',
                        create_user int unsigned not null comment '创建人ID',
                        create_time datetime not null comment '创建时间',
                        update_time datetime not null comment '修改时间',
                        constraint fk_article_category foreign key (category_id) references category(id), -- 外键约束
                        constraint fk_article_user foreign key (create_user) references user(id) -- 外键约束
);

-- ==========================================
-- 插入测试数据
-- ==========================================

-- 1. 向用户表 (user) 插入数据
-- 注意：因为 id 是自动递增的，我们不需要手动指定 id，数据库会自动生成 1, 2, 3...
INSERT INTO user (username, password, nickname, email, user_pic, create_time, update_time)
VALUES
    ('zhangsan', '123456', '张三', 'zhangsan@example.com', 'default_avatar.png', NOW(), NOW()),
    ('lisi', '123456', '李四', 'lisi@example.com', 'default_avatar.png', NOW(), NOW());


-- 2. 向分类表 (category) 插入数据
-- 注意：create_user 必须是上面 user 表中已经存在的 id (比如 1 和 2)
INSERT INTO category (category_name, category_alias, create_user, create_time, update_time)
VALUES
    ('前端开发', 'Frontend', 1, NOW(), NOW()),
    ('后端开发', 'Backend', 1, NOW(), NOW()),
    ('数据库', 'Database', 2, NOW(), NOW());


-- 3. 向文章表 (article) 插入数据
-- 注意：category_id 必须是 category 表中存在的 id，create_user 必须是 user 表中存在的 id
INSERT INTO article (title, content, cover_img, state, category_id, create_user, create_time, update_time)
VALUES
    ('Vue3 快速入门指南', '这是关于 Vue3 基础语法的文章内容...', 'vue3_cover.jpg', '已发布', 1, 1, NOW(), NOW()),
    ('MySQL 索引优化实战', '本文详细介绍了如何优化慢查询和建立合适的索引...', 'mysql_index.jpg', '已发布', 3, 2, NOW(), NOW()),
    ('Java SpringBoot 学习笔记', 'SpringBoot 自动配置原理剖析...', 'springboot.jpg', '草稿', 2, 1, NOW(), NOW());