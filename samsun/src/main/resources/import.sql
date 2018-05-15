INSERT INTO role(status, name) VALUES ('ACTIVE', 'ROLE_ADMIN'), ('ACTIVE', 'ROLE_STAFF'), ('ACTIVE', 'ROLE_CLINIC');

INSERT INTO account (status, username, password, role_id) VALUES ('ACTIVE', 'admin', '$2a$06$iJOvJ3.DwOqBFQbXVsNK5uWqg9qBGMP3qKn8D69ZKf4NY8N.UPdlq', 1),('ACTIVE', 'ducrc3', '$2a$10$FLH6Bztbi.WrygvilgK8wejxK2AIIl8/LozIKk17CmQ/sxD2fHi02', 2);


INSERT INTO xray (status, id,name) VALUES ('ACTIVE', 1,'CT Toàn Hàm'), ('ACTIVE', 2,'Panorama'), ('ACTIVE', 3,'Cephalo Nghiêng'), ('ACTIVE', 4,'Cephalo Thẳng'), ('ACTIVE', 5,'Bàn Tay'), ('ACTIVE', 6,'In Ra Giấy Tỉ Lệ (1:1)'), ('ACTIVE', 7,'Gửi mail'), ('ACTIVE', 8,'Vẽ Ống Dây Thần Kinh'), ('ACTIVE', 9,'Vẽ Cross-section'), ('ACTIVE', 10,'Kết Hợp Làm Máng Phẫu Thuật(Implant guide)') ;

INSERT INTO city (status, id, name) VALUES ('ACTIVE', 1, 'Hà Nội'),('ACTIVE', 2, 'Đà Nẵng'),('ACTIVE', 3, 'Hồ Chí Minh')

INSERT INTO township (status, id,name,acronym,city_id) VALUES ('ACTIVE', 1,'Hải Châu','HC',2),('ACTIVE', 3,'Thanh Khê','TK',2),('ACTIVE', 4,'Sơn Trà','ST',2),('ACTIVE', 5,'Ngũ Hành Sơn','NHS',2),('ACTIVE', 6,'Cẩm Lệ','CL',2),('ACTIVE', 7,'Quận 1','Q1',3),('ACTIVE', 8,'Quận 2','Q2',3),('ACTIVE', 9,'Quận 3','Q3',3),('ACTIVE', 10,'Quận 4','Q4',3),('ACTIVE', 11,'Quận 5','Q5',3),('ACTIVE', 12,'Quận 6','Q6',3),('ACTIVE', 13,'Quận 7','Q7',3),('ACTIVE', 14,'Quận 8','Q8',3),('ACTIVE', 15,'Quận 9','Q9',3),('ACTIVE', 16,'Quận 10','Q10',3),('ACTIVE', 17,'Quận 11','Q11',3),('ACTIVE', 18,'Quận Thủ Đức','TD',3),('ACTIVE', 19,'Quận Gò Vấp','GV',3),('ACTIVE', 20,'Quận Bình Thạch','BT',3),('ACTIVE', 21,'Quận Tân Bình','TB',3),('ACTIVE', 22,'Quận Tân Phú','TP',3),('ACTIVE', 23,'Quận Phú Nhuận','PN',3),('ACTIVE', 24,'Quận Bình Tân','BTAN',3),('ACTIVE', 25,'Quận Ba Đình','BD',1),('ACTIVE', 26,'Quận Hoàn Kiếm','HK',1),('ACTIVE', 27,'Quận Hai Bà Trưng','HBT',1),('ACTIVE', 28,'Quận Đống Đa','DD',1),('ACTIVE', 29,'Quận Tây Hồ','TH',1),('ACTIVE', 30,'Quận Cầu Giấy','CG',1),('ACTIVE', 31,'Quận Thanh Xuân','TX',1),('ACTIVE', 32,'Quận Hoàng Mai','HM',1),('ACTIVE', 33,'Quận Long Biên','LB',1),('ACTIVE', 34,'Liên Chiểu','LC',2),('ACTIVE', 35,'Huyện Hòa Vang','HV',2),('ACTIVE', 36,'Huyện Hoàng Sa','TK',2);

INSERT INTO rc (status, id, name, phone, address, township_id) VALUES ('ACTIVE',1,'SAMSUN RC1','(028) 3848 3691','112 Trần Quang Khải',7),('ACTIVE',2,'SAMSUN RC2','(028) 7306 7879','Số 1 Hồ Bá Kiện',16),('ACTIVE',3,'SAMSUN RC3','(0236) 389 6660','137 Ông Ích Khiêm',1),('ACTIVE',4,'SAMSUN RC4','(024) 3224 7114','Số 39 Ngõ 9 Hoàng Cầu',28);

INSERT INTO user (clinic_name,address,email,full_name,phone_number,rc_id,township_id,account_id,gender) VALUES ('SAMSUN RC3','137 Ông Ích Khiêm','ducrc3@samsunvina.com','Nguyễn Như Đức','093548687',3,3,2,'Nam');
