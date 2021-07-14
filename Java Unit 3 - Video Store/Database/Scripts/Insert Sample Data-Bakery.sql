USE [Default]
GO

--This next section of code inserts data into the "Genre" table, in the VideoStoreDb database.
INSERT INTO products
VALUES ('Bread',4.40,10),('Carrot Cake',18.90,4),('Chicken Pie',13.90,2),('Swiss Roll',12.49,1),('Rainbow Cake',9.49,21)
GO
PRINT 'Inserted data into products Table...'
GO

USE master
GO