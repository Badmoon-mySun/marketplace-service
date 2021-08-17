INSERT INTO public.product (id, currency, deleted, name, price, vendor_code) VALUES (1, 'Рубль', false, 'Творог', 100, 32143124);
INSERT INTO public.product (id, currency, deleted, name, price, vendor_code) VALUES (2, 'Рубль', false, 'Чай', 140, 32132131);
INSERT INTO public.product (id, currency, deleted, name, price, vendor_code) VALUES (3, 'Рубль', true, 'Кофе', 400, 25432423);

INSERT INTO public.marketplace_order (id, buyer_email, created_at, order_number) VALUES (22, 'hahaha@mail.ru', '2021-08-17 23:19:21.998000', 1172320814);
INSERT INTO public.marketplace_order (id, buyer_email, created_at, order_number) VALUES (20, 'anvar00755@mail.ru', '2021-08-17 23:18:32.536000', 1172796664);
INSERT INTO public.marketplace_order (id, buyer_email, created_at, order_number) VALUES (21, 'helloyou@mail.ru', '2021-08-17 23:19:14.697000', 1172320169);

INSERT INTO public.orders_has_products (order_id, product_id) VALUES (20, 1);
INSERT INTO public.orders_has_products (order_id, product_id) VALUES (21, 1);
INSERT INTO public.orders_has_products (order_id, product_id) VALUES (21, 2);
INSERT INTO public.orders_has_products (order_id, product_id) VALUES (22, 1);
INSERT INTO public.orders_has_products (order_id, product_id) VALUES (22, 2);
INSERT INTO public.orders_has_products (order_id, product_id) VALUES (21, 3);
