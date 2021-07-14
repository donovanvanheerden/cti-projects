/* THIS IS THE HEADER SECTION
	Author: Donovan van Heerden
	Date:
	Purpose: This script inserts the triggers into the database.
*/

USE dmp_MMORPG
GO

--This next section of code checks to see if the "trInsertDisabled" trigger exists, if so, it drops the trigger.
IF EXISTS(SELECT name FROM dmp_MMORPG.sys.triggers
WHERE name='trInsertDisabled')
DROP TRIGGER trInsertDisabled
GO

--This next section of code creates the trigger "trInsertDisabled".
CREATE TRIGGER trInsertDisabled
ON Item
INSTEAD OF INSERT
AS
PRINT 'NO, Inserting has been disabled on table ITEM'
GO
PRINT 'Trigger trInsertDisabled created...'

--This next section of code checks to see if the "trInsertNotify" trigger exists, if so, it drops the trigger.
IF EXISTS(SELECT name FROM dmp_MMORPG.sys.triggers
WHERE name='trInsertNotify')
DROP TRIGGER trInsertNotify
GO

--This next section of code creates the trigger "trInsertNotify".
CREATE TRIGGER trInsertNotify
ON ItemDetails
AFTER INSERT, UPDATE
AS
PRINT 'Changes made were successful'
GO
PRINT 'Trigger trInsertNotify created...'


USE master
GO
