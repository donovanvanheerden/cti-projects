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
WHERE name='AuctionDb')
DROP DATABASE AuctionDb
GO

--This next section of code creates the database "dmp_MMORPG".
CREATE DATABASE AuctionDb
ON PRIMARY
(
NAME = 'AuctionDb_data',
FILENAME = 'c:\dmp_MMORPG\database\AuctionDb_data.mdf',
SIZE = 5MB,
FILEGROWTH = 10%
)
LOG ON
(
NAME = 'AuctionDb_log',
FILENAME = 'c:\dmp_MMORPG\database\AuctionDb_log.ldf',
SIZE = 5MB,
FILEGROWTH = 10%
)
GO
PRINT 'Database Created...'
GO





--Creating Tables
USE dmp_MMORPG
GO

--This next section of code creates the "Error" table in the dmp_MMORPG database.
CREATE TABLE Error
(
	errorID INT NOT NULL IDENTITY,
	errorType VARCHAR(20) NOT NULL,
	cause VARCHAR(50) NOT NULL,
	PRIMARY KEY(errorID)
)
GO
PRINT 'Error Table Created...'
GO

--This next section of code creates the "Account" table in the dmp_MMORPG database.
CREATE TABLE Account
(
	userName VARCHAR(20) NOT NULL,
	uPassword VARCHAR(30) NOT NULL,
	monthlyFee VARCHAR(15) NOT NULL DEFAULT('Not Paid'),
	gameTime VARCHAR(10) NOT NULL DEFAULT('0d'),
	PRIMARY KEY(userName)
)
GO
PRINT 'Account Table Created...'
GO

--This next section of code creates the "PlayerChar" table in the dmp_MMORPG database.
CREATE TABLE PlayerChar
(
	cName VARCHAR(20) NOT NULL,
	skillLevel INT NOT NULL DEFAULT(1),
	team VARCHAR(20) NOT NULL,
	userName VARCHAR(20) NOT NULL REFERENCES Account(userName),
	PRIMARY KEY(cName)
)
GO
PRINT 'PlayerChar Table Created...'
GO

--This next section of code creates the "Item" table in the dmp_MMORPG database.
CREATE TABLE Item
(
	itemID VARCHAR(5) NOT NULL,
	itemName VARCHAR(25) NOT NULL,
	itemDesc VARCHAR(40) NOT NULL,
	PRIMARY KEY(itemID)
)
GO
PRINT 'Item Table Created...'
GO

--This next section of code creates the "ItemDetails" table in the dmp_MMORPG database.
CREATE TABLE ItemDetails
(
	cName VARCHAR(20) NOT NULL REFERENCES PlayerChar(cName),
	itemID VARCHAR(5) NOT NULL REFERENCES Item(itemID),
	quantity INT NOT NULL,
	PRIMARY KEY(cName,itemID)
)
GO
PRINT 'ItemDetails Table Created...'
GO





USE master
GO
