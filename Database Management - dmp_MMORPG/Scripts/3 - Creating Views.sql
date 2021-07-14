/* THIS IS THE HEADER SECTION
	Author: Donovan van Heerden
	Date:
	Purpose: This script creates the views.
*/

USE dmp_MMORPG
GO

--This next section of code checks to see if the "vwBlockedAccounts" view exists, if so, it drops the view.
IF EXISTS(SELECT name FROM dmp_MMORPG.sys.all_views
WHERE name='vwBlockedAccounts')
DROP VIEW vwBlockedAccounts
GO

--This next section of code creates the view "vwBlockedAccounts".
CREATE VIEW vwBlockedAccounts
AS
	SELECT *
	FROM Account
	WHERE monthlyFee = 'Blocked'
GO
PRINT 'View vwBlockedAccounts created...'
GO

--This next section of code checks to see if the "vwTopSkill" view exists, if so, it drops the view.
IF EXISTS(SELECT name FROM dmp_MMORPG.sys.all_views
WHERE name='vwTopSkill')
DROP VIEW vwTopSkill
GO

--This next section of code creates the view "vwTopSkill".
CREATE VIEW vwTopSkill
AS
	SELECT TOP 20 skillLevel,cName, account.userName, uPassword, monthlyFee, gameTime
	FROM PlayerChar
	JOIN Account ON Account.userName = PlayerChar.userName
	ORDER BY skillLevel DESC
GO
PRINT 'View vwTopSkill created...'
GO

--This next section of code checks to see if the "vwTopStackedItems" view exists, if so, it drops the view.
IF EXISTS(SELECT name FROM dmp_MMORPG.sys.all_views
WHERE name='vwTopStackedItems')
DROP VIEW vwTopStackedItems
GO

--This next section of code creates the view "vwTopStackedItems".	
CREATE VIEW vwTopStackedItems	
AS
	SELECT TOP 20 itemdetails.itemID, item.itemName, itemDesc, ItemDetails.cName, quantity
	FROM ItemDetails
	JOIN Item ON Item.itemID = ItemDetails.itemID
	GROUP BY item.itemName,itemdetails.itemID, Item.itemDesc, itemdetails.cName, quantity
	ORDER BY quantity DESC
GO
PRINT 'View vwTopStackedItems created...'
GO

--This next section of code checks to see if the "vwPopItems" view exists, if so, it drops the view.
IF EXISTS(SELECT name FROM dmp_MMORPG.sys.all_views
WHERE name='vwPopItems')
DROP VIEW vwPopItems
GO	

--This next section of code creates the view "vwPopItems".
CREATE VIEW vwPopItems
AS
	SELECT TOP 5 itemdetails.Itemid, item.itemName, COUNT(itemdetails.itemid) AS qtyOfItem 
	FROm itemdetails
	JOIN Item ON Item.itemID =  ItemDetails.itemID
	GROUP BY itemdetails.itemID,item.itemName 
	oRDER by qtyOfItem DESC
GO
PRINT 'View vwPopItems created...'
GO	
	
USE master
GO
	