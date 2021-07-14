/* THIS IS THE HEADER SECTION
	Author: Donovan van Heerden
	Date:
	Purpose: This script creates the database and all the tables contained in the database.
*/
USE master
GO

--Creating the Database.

--This next section of code checks to see if the database "dmp_MMORPG" exists, if so, it drops the database.
IF EXISTS(SELECT name FROM master.dbo.sysdatabases
WHERE name='Default')
DROP DATABASE [Default]
GO

--This next section of code creates the database "VideoStoreDb".
CREATE DATABASE [Default]
ON PRIMARY
(
NAME = 'Default_data',
FILENAME = 'C:\Users\User\Documents\NetBeansProjects\Bakery\Database\Default_data.mdf',
SIZE = 5MB,
FILEGROWTH = 10%
)
LOG ON
(
NAME = 'Default_log',
FILENAME = 'C:\Users\User\Documents\NetBeansProjects\Bakery\Database\Default_log.ldf',
SIZE = 5MB,
FILEGROWTH = 10%
)
GO
PRINT 'Database Created...'
GO



USE [Default]
GO
--Creating Tables


CREATE TABLE products
(
	id INT NOT NULL IDENTITY,
	[description] VARCHAR(50) NOT NULL,
	price FLOAT NOT NULL,
	quantity INT NOT NULL,
	PRIMARY KEY(id)
)
GO
PRINT 'products Table Created...'
GO


USE master
GO

