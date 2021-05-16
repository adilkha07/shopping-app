insert into User (contact_no,name) values ('91-9742527600','Tom');
insert into User (contact_no,name) values ('91-9742527000','Harry');

insert into Category (id,name) values ('32f7f1c2-ea60-4a70-ba7a-f17463b79900','Fruit');
insert into Category (id,name) values ('771ccf1c-b292-49d6-b924-8fe811c1d643','Vegetable');

insert into offer (id,min_purchase_quantity,offer_value,discount_type) values ('9412d776-14c5-4fe8-ba38-d9407011d4a0','2','10','FLAT');
insert into offer (id,min_purchase_quantity,offer_value,discount_type) values ('ee5a1038-aca3-45b8-8ff9-3b664b636ac7','2','15','PERCENTAGE');
insert into offer (id,min_purchase_quantity,offer_value,discount_type) values ('ea452e5a-9dda-4501-bac0-2ae32035ec10','2','1','FREEBIE');

insert into Product (id,name,price,stock,category_id_fk,offer_id_fk) values ('a7b8972b-71d7-45ff-bca5-78ef40297461','apple','10','10','32f7f1c2-ea60-4a70-ba7a-f17463b79900',null);
insert into Product (id,name,price,stock,category_id_fk,offer_id_fk) values ('a434a1c2-b7d2-495c-9736-b2265ef5d6bd','mango','15','5','32f7f1c2-ea60-4a70-ba7a-f17463b79900','9412d776-14c5-4fe8-ba38-d9407011d4a0');
insert into Product (id,name,price,stock,category_id_fk,offer_id_fk) values ('8df86d1c-129c-4afb-ad45-646e4e1439b3','orange','20','25','32f7f1c2-ea60-4a70-ba7a-f17463b79900','ee5a1038-aca3-45b8-8ff9-3b664b636ac7');
insert into Product (id,name,price,stock,category_id_fk,offer_id_fk) values ('6e1623aa-2051-4277-82d3-05abd93be1cf','papaya','5','15','32f7f1c2-ea60-4a70-ba7a-f17463b79900','ea452e5a-9dda-4501-bac0-2ae32035ec10');
