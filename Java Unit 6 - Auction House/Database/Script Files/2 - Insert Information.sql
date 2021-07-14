USE AuctionDb
GO

--This next section of code inserts data into the "Movie" table, in the VideoStoreDb database.
INSERT INTO CustomerInformation
VALUES ('Donovan','van Heerden','admin@AH.co.za','admin','27757873655','12 Long Str,Nahoon,East London,5201','admin'),
('Daniel','Lundstrom','DanLund@gmail.com','password','27842253314','20 Ten Rale Rd,Suburbia,Port Elizabeth,5241','roux')
GO
PRINT 'Inserted data into CustomerInformation Table...'
GO

INSERT INTO ProductInformation
VALUES('Oak Table', 'Nicely aged Oak Table from 1920''s',375.99 ,400.00 ,'2015-02-17','2'),
('Grandfather Clock', 'Shiny old clock, works like brand new.',275.99 ,275.99 ,'2015-06-17','2'),
('Dusty Old Chair', 'Cobwebs and stuff, nothing broken',175.99 ,175.99 ,'2015-05-10','2'),
('Manikin from 1800''s', 'Life sized manikin, for all those tailors out there.',329.49 ,329.49 ,'2015-04-12','2'),
('Book of Alchemy', 'Learn all the ancient techniques of alchemy.',79.95 ,79.95 ,'2015-06-05','2'),
('China Vase', 'Ancient vase from China, depicting epic dragons!',529.49 ,529.49 ,'2015-07-25','1'),
('Wagon Wheels', 'Spare wheels for those who still require Wagon Wheels. Pretty rare for today.',449.99 ,449.99 ,'2015-09-10','1')
GO
PRINT 'Inserted data into ProductionInformation Table...'
GO

INSERT INTO BidInformation
VALUES(1, 1, 400.00)
GO
PRINT 'Inserted data into BidInformation Table...'
GO

USE master
GO