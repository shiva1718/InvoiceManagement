create table invoice_db.customer
(
    id      bigint auto_increment
        primary key,
    address varchar(255) null,
    email   varchar(255) null,
    name    varchar(255) null,
    phone   varchar(255) null,
    balance double       not null
);

create table invoice_db.invoice
(
    id           bigint auto_increment
        primary key,
    date         date   null,
    total_amount double not null,
    customer_id  bigint null,
    constraint FK5e32ukwo9uknwhylogvta4po6
        foreign key (customer_id) references invoice_db.customer (id)
);

create table invoice_db.invoice_item
(
    id           bigint auto_increment
        primary key,
    item_name    varchar(255) null,
    price        double       not null,
    quantity     int          not null,
    invoice_id   bigint       null,
    total_amount double       not null,
    constraint FKbu6tmpd0mtgu9wrw5bj5uv09v
        foreign key (invoice_id) references invoice_db.invoice (id)
);

create table invoice_db.payment
(
    id           bigint auto_increment
        primary key,
    amount_paid  double  not null,
    date         date    null,
    payment_type tinyint null,
    invoice_id   bigint  null,
    constraint FKsb24p8f52refbb80qwp4gem9n
        foreign key (invoice_id) references invoice_db.invoice (id),
    check (`payment_type` between 0 and 3)
);

create table invoice_db.users
(
    id       bigint auto_increment
        primary key,
    password varchar(255)               null,
    role     enum ('ADMIN', 'CUSTOMER') null,
    username varchar(255)               null,
    constraint UKr43af9ap4edm43mmtq01oddj6
        unique (username)
);

