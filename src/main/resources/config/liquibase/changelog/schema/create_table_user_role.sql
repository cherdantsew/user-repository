create table user_role(
    user_id bigint,
    role_id smallint,
    constraint user_role_pk primary key (user_id, role_id),
    constraint user_role_fk foreign key (user_id)
                      references users(id)
                      on delete cascade
);