create table if not exists product
(
    id          bigserial not null primary key,
    title       text      not null,
    description text,
    image_link  text,
    price       int       not null
);

create table if not exists category
(
    id        bigserial not null primary key,
    name      text      not null,
    alias     text      not null,
    parent_id bigint,

    foreign key (parent_id) references category (id)
);

create table if not exists customer
(
    id   bigserial not null primary key,
    name text      not null
);

create table if not exists product_category
(
    category_id bigint not null,
    product_id  bigint not null,

    primary key (category_id, product_id),
    foreign key (category_id) references category (id),
    foreign key (product_id) references product (id)
);

create table if not exists purchase_details
(
    customer_id bigint not null,
    product_id  bigint not null,
    purchase_price text,

    primary key (customer_id, product_id),
    foreign key (customer_id) references customer (id),
    foreign key (product_id) references product (id)
);