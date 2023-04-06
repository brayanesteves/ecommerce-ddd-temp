INSERT INTO categories (id, name) VALUES ('f3559fb4-ea4a-4c86-b889-e0838a0719c5', 'Camisetas');
INSERT INTO categories (id, name) VALUES ('7e7937a6-e008-42f9-b619-d15a41108b8a', 'Juguetes');
INSERT INTO categories (id, name) VALUES ('7a064b02-480b-4040-a0af-99bb56a5223c', 'Vasos');
INSERT INTO categories (id, name) VALUES ('8c534d71-e4a3-490c-9d6d-8c15ed2569ad', 'Comics');
INSERT INTO categories (id, name) VALUES ('855e673e-26bb-40e7-a31d-638f47cb269a', 'Accesorios');
INSERT INTO categories (id, name) VALUES ('855e673e-26bb-40e7-a31d-638f47cb269b', 'Video');

INSERT INTO franchises (id, name) VALUES ('fe5d622d-895b-429e-b564-63ed7ebc7820', 'Alternativo');
INSERT INTO franchises (id, name) VALUES ('f878cdc6-d089-405f-9f4d-5d53dcc79726', 'Marvel');
INSERT INTO franchises (id, name) VALUES ('f3d0a258-ab7a-4a2f-864a-f3acff3450e3', 'DC');

-- INSERT INTO products (id, code, name, purchase_price, selling_price, category_id, franchise_id) VALUES ('490b15ff-c75c-486c-96c1-34de50383f54', '8B7CCS', 'Pelicula Avengers. Blu-Ray', 64000.50, 78000, '855e673e-26bb-40e7-a31d-638f47cb269b', 'f878cdc6-d089-405f-9f4d-5d53dcc79726');

--INSERT INTO movements(id, product_id, type, quantity, price, observation, created_at) VALUES ('117d81f9-a820-49a2-b304-95138135ffc3', '490b15ff-c75c-486c-96c1-34de50383f54', 'INCOMINGS', 5, 64000.50, 'None', STR_TO_DATE('04/01/2021 8:06:26 AM', '%c/%e/%Y %r'));
--INSERT INTO movements(id, product_id, type, quantity, price, observation, created_at) VALUES ('d81b909f-e064-41a3-bd86-931387ea85bd', '490b15ff-c75c-486c-96c1-34de50383f54', 'INCOMINGS', 9, 64500.00, 'None', STR_TO_DATE('05/01/2021 9:00:40 AM', '%c/%e/%Y %r'));
--INSERT INTO movements(id, product_id, type, quantity, price, observation, created_at) VALUES ('45cf4697-9d03-4b93-8b2f-7f612176933f', '490b15ff-c75c-486c-96c1-34de50383f54', 'OUTGOINGS', 4, 78000.59888686, 'None', STR_TO_DATE('06/01/2021 10:06:00 AM', '%c/%e/%Y %r'));
--INSERT INTO movements(id, product_id, type, quantity, price, observation, created_at) VALUES ('7916d06c-a6d2-4920-9836-54e9968aedf9', '490b15ff-c75c-486c-96c1-34de50383f54', 'OUTGOINGS', 1, 70000.00, 'None', STR_TO_DATE('06/01/2021 11:00:00 AM', '%c/%e/%Y %r'));

-- Password: demo1234
INSERT INTO users (id, username, password, first_name, last_name) VALUES ('66d5c69f-7516-4773-bbd5-c273a9f5c515', 'demo', '$2a$10$HBMJtOBn/PuSc/1BRa5KPeA3yv.ChZZoCbF/0QyDtDtuDc2VP2JGm', 'Demo', 'user');