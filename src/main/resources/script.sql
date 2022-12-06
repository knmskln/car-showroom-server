insert ignore into roles(role_id, role_name)
VALUES (1, 'admin');
insert ignore into roles(role_id, role_name)
VALUES (2, 'user');
insert ignore into roles(role_id, role_name)
VALUES (3, 'dealer');
insert ignore into roles(role_id, role_name)
VALUES (4, 'seller');

insert ignore into users(user_id, banned, email, first_name, last_name, password, username, status_id)
VALUES (1, 1, false, 'admin@mail.ru', 'Admin', 'admin', '���eFZ���2l�@)', 'admin', 1);
-- 1111(password)
insert ignore into statuses(status_id, status_name)
VALUES (1, 'orderFromSeller');
insert ignore into statuses(status_id, status_name)
VALUES (2, 'orderFromDealer');
insert ignore into statuses(status_id, status_name)
VALUES (3, 'approved');
insert ignore into statuses(status_id, status_name)
VALUES (4, 'rejected');