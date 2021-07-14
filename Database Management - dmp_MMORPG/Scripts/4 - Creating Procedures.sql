/* THIS IS THE HEADER SECTION
	Author: Donovan van Heerden
	Date:
	Purpose: This script creates the stored procedures for the database.
*/

USE dmp_MMORPG
GO

--This next section of code checks to see if the "spRegister" stored procedure exists, if so, it drops the procedure.
IF EXISTS(SELECT name FROM dmp_MMORPG.sys.procedures
WHERE name='spRegister')
DROP PROCEDURE spRegister
GO

--This next section of code creates the procedure "spRegister".
CREATE PROCEDURE spRegister
@uName VARCHAR(20),
@uPass VARCHAR(30),
@mFee VARCHAR(15),
@gTime VARCHAR(10)
AS
IF EXISTS (SELECT * FROM Account WHERE account.userName = @uName)
	PRINT 'Username already exists'	
 ELSE
  BEGIN 
	INSERT INTO Account
	VALUES (@uName,@uPass,@mFee,@gTime)
	PRINT 'New Account Registered'
  END	
GO	
PRINT 'Procedure spRegister created...'
GO

--This next section of code checks to see if the "spAddTime" stored procedure exists, if so, it drops the procedure.
IF EXISTS(SELECT name FROM dmp_MMORPG.sys.procedures
WHERE name='spAddTime')
DROP PROCEDURE spAddTime
GO

--This next section of code creates the procedure "spAddTime".
CREATE PROCEDURE spAddTime
@uName VARCHAR(20),
@gTime INT
AS
IF @uName IN(SELECT userName FROM Account)
BEGIN
 UPDATE Account SET gameTime = CAST(CAST(SUBSTRING(gametime,1,LEN(gametime)-1) AS INT) + @gTime AS VARCHAR) + 'd'
 WHERE userName = @uName
END
ELSE
 RAISERROR('Account does not exist.',16,10)	
GO
PRINT 'Procedure spAddTime created...'

--This next section of code checks to see if the "spAddItem" stored procedure exists, if so, it drops the procedure.
IF EXISTS(SELECT name FROM dmp_MMORPG.sys.procedures
WHERE name='spAddItem')
DROP PROCEDURE spAddItem
GO

--This next section of code creates the procedure "spAddItem".
CREATE PROCEDURE spAddItem
@cName VARCHAR(20),
@itemID VARCHAR(5),
@quantity INT
AS
IF @cName IN(SELECT cName FROM PlayerChar)
BEGIN
 IF @itemID IN(SELECT itemID FROM item)
 BEGIN
 IF EXISTS (SELECT * FROM ItemDetails WHERE cName = @cName AND itemID = @itemID)
  BEGIN
	UPDATE ItemDetails SET quantity = @quantity+quantity WHERE cName = @cName AND itemID = @itemID
	PRINT 'Successfully updated quantity'
  END	
 ELSE
  BEGIN
	IF (SELECT COUNT(*) FROM ItemDetails WHERE cName = @cName) < 8
	 BEGIN
	  INSERT INTO ItemDetails VALUES(@cName, @itemID, @quantity)
	  PRINT 'Item added successfully to ' + @cName + '''s inventory.'
	 END
	ELSE
	 BEGIN
	  RAISERROR('The inventory is full.',16,10)	
	 END 
  END
 END
 ELSE
 RAISERROR('The itemid entered is invalid.',16,10)
END 
ELSE
 BEGIN
  RAISERROR('The character name entered is invalid.',16,10)
 END  	
GO
PRINT 'Procedure spAddItem created...'
GO

--This next section of code checks to see if the "spAddChar" stored procedure exists, if so, it drops the procedure.
IF EXISTS(SELECT name FROM dmp_MMORPG.sys.procedures
WHERE name='spAddChar')
DROP PROCEDURE spAddChar
GO

--This next section of code creates the procedure "spAddChar".
CREATE PROCEDURE spAddChar
@cName VARCHAR(20),
@team VARCHAR(20),
@uName VARCHAR(20)
AS
IF @uName IN(SELECT userName FROM PlayerChar)
BEGIN
 IF EXISTS (SELECT * FROM PlayerChar WHERE PlayerChar.cName = @cName)
	RAISERROR('Character already exists',16,10)	
 ELSE
  BEGIN 
	INSERT INTO PlayerChar (cName,team,userName)
	 VALUES (@cName,@team,@uName)
	PRINT 'New Character created'
  END	
END
ELSE
RAISERROR('The account in which you are trying to add a character, does not exist.',16,10)	  
GO
PRINT 'Procedure spAddChar created...'
GO

--This next section of code checks to see if the "spSendLetter" stored procedure exists, if so, it drops the procedure.
IF EXISTS(SELECT name FROM dmp_MMORPG.sys.procedures
WHERE name='spSendLetter')
DROP PROCEDURE spSendLetter
GO

--This next section of code creates the procedure "spSendLetter".
CREATE PROCEDURE spSendLetter
@uName VARCHAR(20),
@optional VARCHAR(3)
AS
DECLARE @gametime VARCHAR(10)
SELECT @gametime = Account.gameTime FROM Account WHERE Account.userName = @uName
IF @uName IN(SELECT userName FROM Account)
BEGIN
 IF EXISTS (SELECT * FROM Account WHERE Account.userName = @uName)
  BEGIN
   IF @optional = 'Yes'
    BEGIN
      PRINT '---------------------------------------------------------------------------------'
	  PRINT '>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<'
	  PRINT '---------------------------------------------------------------------------------'
	  PRINT '|Greetings ' + @uName +',															|'
      PRINT '|Your account has ' + @gametime + ' of gametime left.				  	    					|'
      PRINT '---------------------------------------------------------------------------------'
	  PRINT '>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<'
	  PRINT '---------------------------------------------------------------------------------'
	  PRINT '|BREAKING NEWS!												 '+CAST(GETDATE()AS VARCHAR)+'|'
	  PRINT '|The following bugs have been fixed:											|'
	  PRINT '|	* Quest -		Breaking the line - no longer								|	'
	  PRINT '|					caused the player to die randomly							|	'
	  PRINT '|					after 80% completion.										|	'
      PRINT '|	* Talent Tree - Talent trees were adjusted									|	'
	  PRINT '|					accordingly to balance the									|	'
	  PRINT '|					classes: Thief, Mage and									|	'
	  PRINT '|					Warrior.													|	'
	  PRINT '---------------------------------------------------------------------------------'
	  PRINT '|GAMEPLAY NEWS! 																|	'
	  PRINT '|	* We have increased the level cap from 90 to 95.							|'
	  PRINT '|	* Added 5 new leveling zones: 2 level 90 - 92 Zones, 2 level 92 - 94 Zones	|	'
	  PRINT '|								 and 1 level 94 - 95 Zone.						|	'
	  PRINT '|	* Changed the way gear worked to incorperate PVP during leveling, so that	|	'
	  PRINT '|	  people who may be '''+'ganked'''+' will now have more of an advantage and			|	'
	  PRINT '|	  gear will change and have base PVP resilience.							|	'
	  PRINT '---------------------------------------------------------------------------------'
	  PRINT '|OTHER NEWS!																	|	'
	  PRINT '|If you had created your account and bought into the alpha version of the game	|	'
	  PRINT '|you will receive bonus vanity items and companions, like the Molten Song Bird	|	'
	  PRINT '|or the Treant of Harmony. 														|'
	  PRINT '---------------------------------------------------------------------------------'
	  PRINT '>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<'
	  PRINT '---------------------------------------------------------------------------------'
    END
   ELSE
    BEGIN
      PRINT '---------------------------------------------------------------------------------'
	  PRINT '>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<'
	  PRINT '---------------------------------------------------------------------------------'
	  PRINT '|Greetings ' + @uName +',															|'
      PRINT '|Your account has ' + @gametime + ' of gametime left.				  	    					|'
      PRINT '---------------------------------------------------------------------------------'
	  PRINT '>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<'
	  PRINT '---------------------------------------------------------------------------------'
    END
 END	
ELSE
RAISERROR('Account does not exist',16,10)
END
GO
PRINT 'Procedure spSendLetter created...'
GO




USE master
GO