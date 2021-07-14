/* THIS IS THE HEADER SECTION
	Author: Donovan van Heerden
	Date:
	Purpose: This script creates the database and all the tables contained in the database.
*/
USE master
GO

--Creating the Database.

--This next section of code checks to see if the database "AuctionDb" exists, if so, it drops the database.
IF EXISTS(SELECT name FROM master.dbo.sysdatabases
WHERE name='AuctionDb')
DROP DATABASE AuctionDb
GO

--This next section of code creates the database "AuctionDb".
CREATE DATABASE AuctionDb
ON PRIMARY
(
NAME = 'AuctionDb_data',
FILENAME = 'C:\Users\User\Desktop\Stuff\Projects\Java Unit 6 Database\Database\AuctionDb_data.mdf',
SIZE = 5MB,
FILEGROWTH = 10%
)
LOG ON
(
NAME = 'AuctionDb_log',
FILENAME = 'C:\Users\User\Desktop\Stuff\Projects\Java Unit 6 Database\Database\AuctionDb_log.ldf',
SIZE = 5MB,
FILEGROWTH = 10%
)
GO
PRINT 'Database Created...'
GO





--Creating Tables
USE AuctionDb
GO

--This next section of code creates the "CustomerInformation" table in the AuctionDb database.
CREATE TABLE CustomerInformation
(
	CustomerID INT NOT NULL IDENTITY,
	FirstName VARCHAR(20) NOT NULL,
	LastName VARCHAR(50) NOT NULL,
	EmailAddress VARCHAR(255) NOT NULL,
	[Password] VARCHAR(30) NOT NULL,
	ContactNumber VARCHAR(11) NOT NULL,
	PostalAddress VARCHAR(255) NOT NULL,
	SecQuestAnswer VARCHAR(50) NOT NULL,
	PRIMARY KEY(CustomerID)
)
GO
PRINT 'CustomerInformation Table Created...'
GO

--This next section of code creates the "ProductInformation" table in the AuctionDb database.
CREATE TABLE ProductInformation
(
	ProductID INT NOT NULL IDENTITY,
	ProdName VARCHAR(50) NOT NULL,
	ProdDesc VARCHAR(255) NOT NULL,
	StartBid DECIMAL(18,2) NOT NULL,
	CurrentBid DECIMAL(18,2) NOT NULL,
	EndDate DATE NOT NULL,
	OwnerID INT NOT NULL,
	PRIMARY KEY(ProductID)
)
GO
PRINT 'ProductInformation Table Created...'
GO

--This next section of code creates the "BidInformation" table in the AuctionDb database.
CREATE TABLE BidInformation
(
	BidID INT NOT NULL IDENTITY,
	CustomerID INT NOT NULL REFERENCES CustomerInformation(CustomerID),
	ProductID INT NOT NULL REFERENCES ProductInformation(ProductID),
	BidValue DECIMAL(18,2) NOT NULL,
	PRIMARY KEY(BidID)
)
GO
PRINT 'BidInformation Table Created...'
GO


USE master
GO
