/* THIS IS THE HEADER SECTION
	Author: Donovan van Heerden
	Date:
	Purpose: This script creates the indicies for the dmp_MMORPG database.
*/

USE dmp_MMORPG
GO

--This next section of code checks to see if the "idx_Item" index exists, if so, it drops the index.
IF EXISTS(SELECT name FROM dmp_MMORPG.sys.indexes
WHERE name='idx_Item')
DROP INDEX idx_Item ON Item
GO

--This next section of code checks creates the index "idx_Item".
CREATE INDEX idx_Item
ON Item(itemID)
GO
PRINT 'Index idx_Item created...'
GO

--This next section of code checks to see if the "idx_Account" index exists, if so, it drops the index.
IF EXISTS(SELECT name FROM dmp_MMORPG.sys.indexes
WHERE name='idx_Account')
DROP INDEX idx_Account ON Account
GO

--This next section of code checks creates the index "idx_Account".
CREATE INDEX idx_Account
ON Account(userName)
GO
PRINT 'Index idx_Account created...'
GO

USE master
GO
