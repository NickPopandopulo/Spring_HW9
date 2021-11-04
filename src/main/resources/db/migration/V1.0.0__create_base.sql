create table product (
    id bigserial not null primary key,
    title text not null,
    description text,
    image_link text,
    price int not null
);

create table category(
    id bigserial not null primary key,
    name text not null,
    alias text not null,
    parent_id bigint,

    foreign key (parent_id) references category(id)
);

create table product_category (
    category_id bigint not null,
    product_id bigint not null,

    primary key (category_id, product_id),
    foreign key (category_id) references category(id),
    foreign key (product_id) references product(id)
);
