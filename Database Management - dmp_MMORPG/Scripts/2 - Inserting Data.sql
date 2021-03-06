/* THIS IS THE HEADER SECTION
	Author: Donovan van Heerden
	Date:
	Purpose: This script inserts all the data into the various tables contained in the database
*/

USE dmp_MMORPG
GO

--This next section of code inserts data into the "Account" table, in the dmp_MMORPG database.
INSERT INTO Account
VALUES ('coolman55','123coolman','Paid R300','60d'),
('Jessinator','J3sD4nH3a','Paid R150','30d'),
('Fudgebush','b99nm56v99gh9876','Paid R1200','365d'),
('MilkyPaws','KittyMeowMeow','Blocked','0d'),
('AntiBio','092EgloIE','Paid R500','90d'),
('Spyker','R3SPKY449','Blocked','0d'),
('sexybeast666','beast666sexy','Paid R2500','1095d'),
('RainbowUnicorn99','Flow3rs43va','Paid R300','60d'),
('FrankStrom','f23ar34nsw23121255','Paid R300','60d'),
('SanguineMist','t4frm1s7','Credited','60d'),
('Nomad454','d9922fjsR394kJD','Paid R1200','365d')
GO
PRINT 'Inserted data into Account Table...'
GO

--This next section of code inserts data into the "Error" table, in the dmp_MMORPG database.
INSERT INTO Error
VALUES ('Account','Account not found or does not exist'),
('Account', 'Password or Username incorrect'),
('Account','Account not active'),
('Game','Invalid Action'),
('Game','You clicked too many times'),
('Game', 'An instance of this game is already running'),
('Character', 'A Character with that name does not exist'),
('Character', 'A Character with that name already exists')
GO
PRINT 'Inserted data into Error Table...'
GO

--This next section of code inserts data into the "PlayerChar" table, in the dmp_MMORPG database.
INSERT INTO PlayerChar
VALUES ('Mancool',20,'Northern Alliance','coolman55'),
('Jessinator',12,'Western Horde','Jessinator'),
('Fudgebush',45,'Southern Tribe','Fudgebush'),
('Higgleton',90,'Southern Tribe','Fudgebush'),
('Corbatz',90,'Southern Tribe','Nomad454'),
('Lexoria',87,'Western Horde','Nomad454'),
('Demidon',63,'Southern Tribe','Nomad454'),
('Urdon',55,'Southern Tribe','Nomad454'),
('PawOfMilk',30,'Eastern Trolls','MilkyPaws'),
('Gobliness',25,'Western Horde','AntiBio'),
('AntiBio',72,'Southern Tribe','AntiBio'),
('Poep',44,'Western Horde','AntiBio'),
('Koifish',90,'Eastern Trolls','AntiBio'),
('Wimpie',88,'Northen Alliance','AntiBio'),
('Spyker',27,'Eastern Trolls','Spyker'),
('Killer77',70,'Northern Alliance','sexybeast666'),
('RainbowDash',10,'Southern Tribe','RainbowUnicorn99'),
('Homichi',35,'Eastern Trolls','FrankStrom'),
('Thraxx',90,'Western Horde','FrankStrom'),
('Zuldrak',73,'Eastern Trolls','FrankStrom'),
('VultureRegime',90,'Southern Tribe','FrankStrom'),
('Strom',65,'Eastern Trolls','FrankStrom'),
('Evatrix',90,'Western Horde','SanguineMist'),
('Evaletts',73,'Eastern Trolls','SanguineMist'),
('SanguineMist',90,'Southern Tribe','SanguineMist'),
('Illumine',65,'Eastern Trolls','SanguineMist')
GO
PRINT 'Inserted data into PlayerChar Table...'
GO

--This next section of code inserts data into the "Item" table, in the dmp_MMORPG database.
INSERT INTO Item
VALUES (075,'Sword of Shock','Does +20 Shock Damage'),
(076,'Dagger of Shock', 'Does +15 Shock Damage'),
(077,'Fire Staff','Allows the user to breathe fire'),
(078,'Shield of Justice','Blocks 20% of all Physical Damage'),
(079,'FrostFire Sword','Deals +30 Fire and Shock Damage'),
(080,'SkullBasher','Has a 20% chance to stun'),
(081,'Bow of Freezing','Slows the target''' + 's movement speed'),
(082,'Mace of Water','Heals the target for 20HP'),
(083,'Staff of Lightening','Deals 20 lightening damage'),
(084,'Ring of Power','Adds 10 power to the user'),
(010,'Health Potion','Restores 100HP'),
(011,'Greater Health Potion','Restores 500 HP'),
(012,'Ultra Health Potion','Restores 2000HP'),
(013,'Mana Potion','Restores 100 Mana'),
(014,'Greater Mana Potion','Restores 500 Mana'),
(015,'Ultra Mana Potion','Restores 2000 Mana'),
(020,'Potion of Invisibility','The user becomes invisible for 1 hour'),
(045,'Steel Helm','+5 Defence'),
(046,'Steel Gloves','+5 Defence'),
(047,'Steel Leggings','+10 Defence'),
(048,'Steel Breastplate','+15 Defence'),
(049,'Steel Boots','+5 Defence'),
(050,'Leather Helm','+3 Defence'),
(051,'Leather Gloves','+3 Defence'),
(052,'Leather Leggings','+8 Defence'),
(053,'Leather Jacket','+12 Defence'),
(054,'Leather Boots','+3 Defence'),
(055,'Cloth Helm','+2 Defence'),
(056,'Cloth Gloves','+2 Defence'),
(057,'Cloth Leggings','+6 Defence'),
(058,'Cloth Tunic','+10 Defence'),
(059,'Cloth Slippers','+2 Defence')
GO
PRINT 'Insert data into the Item Table...'
GO

--This next section of code inserts data into the "itemDetails" table, in the dmp_MMORPG database.
INSERT INTO itemDetails
VALUES ('Mancool',045,1),
('Mancool',046,2),
('Mancool',047,1),
('Mancool',048,1),
('Mancool',049,1),
('Mancool',075,2),
('Mancool',078,1),
('Jessinator',050,1),
('Jessinator',051,2),
('Jessinator',052,1),
('Jessinator',053,1),
('Jessinator',054,2),
('Jessinator',076,1),
('Fudgebush',050,1),
('Fudgebush',051,1),
('Fudgebush',052,1),
('Fudgebush',053,2),
('Fudgebush',054,1),
('Fudgebush',081,1),
('Higgleton',045,1),
('Higgleton',046,2),
('Higgleton',047,1),
('Higgleton',048,3),
('Higgleton',049,1),
('Higgleton',080,4),
('Corbatz',050,1),
('Corbatz',051,1),
('Corbatz',052,2),
('Corbatz',053,1),
('Corbatz',054,3),
('Corbatz',083,2),
('Lexoria',055,1),
('Lexoria',056,1),
('Lexoria',057,3),
('Lexoria',058,1),
('Lexoria',059,5),
('Lexoria',077,1),
('Demidon',045,3),
('Demidon',046,1),
('Demidon',047,1),
('Demidon',048,2),
('Demidon',049,1),
('Demidon',075,1),
('Urdon',050,4),
('Urdon',051,1),
('Urdon',052,1),
('Urdon',053,2),
('Urdon',054,1),
('Urdon',076,7),
('PawOfMilk',055,1),
('PawOfMilk',056,2),
('PawOfMilk',057,1),
('PawOfMilk',058,1),
('PawOfMilk',059,3),
('PawOfMilk',083,1),
('Gobliness',045,1),
('Gobliness',046,4),
('Gobliness',047,1),
('Gobliness',048,2),
('Gobliness',049,1),
('Gobliness',084,1),
('AntiBio',011,9),
('AntiBio',015,12),
('AntiBio',057,1),
('AntiBio',058,2),
('AntiBio',082,1),
('AntiBio',084,4),
('Poep',047,1),
('Poep',048,3),
('Poep',049,4),
('Poep',010,6),
('Poep',020,2),
('Poep',013,6),
('Koifish',055,1),
('Koifish',056,1),
('Koifish',057,3),
('Koifish',058,1),
('Koifish',011,8),
('Koifish',015,4),
('Wimpie',081,1),
('Wimpie',084,4),
('Wimpie',079,1),
('Wimpie',012,1),
('Wimpie',015,2),
('Wimpie',020,1),
('Spyker',045,3),
('Spyker',047,1),
('Spyker',048,1),
('Spyker',079,3),
('Spyker',078,1),
('Spyker',049,2),
('Killer77',020,6),
('Killer77',053,1),
('Killer77',054,1),
('Killer77',052,2),
('Killer77',012,8),
('Killer77',014,7),
('RainbowDash',055,1),
('RainbowDash',056,3),
('RainbowDash',057,4),
('RainbowDash',058,1),
('RainbowDash',012,2),
('RainbowDash',015,4),
('Homichi',083,1),
('Homichi',076,3),
('Homichi',012,7),
('Homichi',020,5),
('Thraxx',011,4),
('Thraxx',014,6),
('Zuldrak',020,2),
('VultureRegime',049,1),
('VultureRegime',048,2),
('VultureRegime',077,1),
('VultureRegime',059,1),
('VultureRegime',051,1),
('Strom',020,5),
('Strom',015,4),
('Evatrix',012,4),
('Evatrix',052,1),
('Evatrix',053,2),
('Evatrix',050,1),
('Evaletts',046,2),
('Evaletts',047,1),
('Evaletts',048,3),
('Evaletts',049,1),
('Evaletts',012,9),
('Evaletts',020,2),
('SanguineMist',012,12),
('SanguineMist',013,13),
('SanguineMist',055,1),
('SanguineMist',056,1),
('Illumine',056,1),
('Illumine',057,1),
('Illumine',011,6)
GO
PRINT 'Inserted data into ItemDetails Table...'
GO


USE master
GO