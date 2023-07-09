create table users
(
    id             bigserial primary key not null,
    login          varchar               not null,
    password       varchar               not null,
    contact_info   varchar,
    courier_status varchar,
    account_balance bigint default 0
);