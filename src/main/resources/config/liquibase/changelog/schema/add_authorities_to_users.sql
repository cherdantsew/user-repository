update roles
set authorities = concat(authorities, ',CREATE_DELIVERY_ORDER,VIEW_DELIVERY_ORDER,CHANGE_DELIVERY_DESTINATION,CANCEL_DELIVERY_ORDER')
where role_name = 'ROLE_USER';
update roles
set authorities = concat(authorities, ',VIEW_ALL_DELIVERY_ORDERS,ASSIGN_PARCEL_DELIVERY_ORDER,CHANGE_DELIVERY_STATUS,VIEW_ALL_COURIERS')
where role_name = 'ROLE_ADMIN';
update roles
set authorities = concat(authorities, ',CHANGE_DELIVERY_STATUS')
where role_name = 'ROLE_COURIER';
