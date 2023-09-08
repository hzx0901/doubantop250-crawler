-- auto-generated definition
create schema douban_crawler collate utf8mb4_0900_ai_ci;
use douban_crawler;
create table movies
(
    ranking      int          null comment '排名',
    movie_name   varchar(255) null comment '电影名称',
    director     varchar(255) null comment '导演',
    actor        varchar(255) null comment '主演',
    release_date varchar(255) null comment '上映时间',
    country      varchar(255) null comment '国家',
    movie_type   varchar(255) null comment '电影类型',
    rating       float        null comment '评分',
    comment      varchar(255) null comment '影评',
    detail_link  varchar(255) null comment '电影详情链接',
    cover_link   varchar(255) null comment '电影封面链接',
    id           int          not null
        primary key
);