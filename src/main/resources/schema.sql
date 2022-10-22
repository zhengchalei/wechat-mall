create table goods
(
    id             serial primary key,
    title          text    not null,
    primary_image  text    not null,
    max_sale_price decimal not null,
    min_sale_price decimal not null,
    images         json    not null,
    description    json
);
comment on column goods.title is 'title';
comment on column goods.primary_image is '主页图片, 商品展示第一张图';
comment on column goods.max_sale_price is '原价';
comment on column goods.min_sale_price is '促销价';
comment on column goods.description is '用于展示商品详情中的轮播图';
comment on column goods.description is '商品详情, 一般以多张图片拼接';
