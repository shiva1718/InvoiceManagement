create table address
(
    id      bigint       not null
        primary key,
    city    varchar(255) null,
    pincode varchar(255) null,
    state   varchar(255) null,
    street  varchar(255) null
);

create table customer
(
    id         bigint auto_increment
        primary key,
    balance    double       not null,
    email      varchar(255) null,
    name       varchar(255) null,
    phone      varchar(255) null,
    address_id bigint       null,
    constraint UKr8whbd0mf9er6vwfr0sclsxkd
        unique (address_id),
    constraint FKglkhkmh2vyn790ijs6hiqqpi
        foreign key (address_id) references address (id)
);

create table invoice
(
    id           bigint auto_increment
        primary key,
    date         date   null,
    total_amount double not null,
    customer_id  bigint null,
    constraint FK5e32ukwo9uknwhylogvta4po6
        foreign key (customer_id) references customer (id)
);

create table invoice_item
(
    id           bigint auto_increment
        primary key,
    price        double       not null,
    quantity     int          not null,
    total_amount double       not null,
    invoice_id   bigint       null,
    name         varchar(255) null,
    constraint FKbu6tmpd0mtgu9wrw5bj5uv09v
        foreign key (invoice_id) references invoice (id)
);

create table payment
(
    id           bigint auto_increment
        primary key,
    amount_paid  double  not null,
    date         date    null,
    payment_type tinyint null,
    invoice_id   bigint  null,
    constraint FKsb24p8f52refbb80qwp4gem9n
        foreign key (invoice_id) references invoice (id),
    check (`payment_type` between 0 and 3)
);

create table users
(
    id       bigint auto_increment
        primary key,
    password varchar(255)               null,
    role     enum ('ADMIN', 'CUSTOMER') null,
    username varchar(255)               null,
    email    varchar(255)               not null,
    constraint UKr43af9ap4edm43mmtq01oddj6
        unique (username)
);

