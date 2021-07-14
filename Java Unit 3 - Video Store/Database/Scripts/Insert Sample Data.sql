USE VideoStoreDb
GO

--This next section of code inserts data into the "Genre" table, in the VideoStoreDb database.
INSERT INTO Genre
VALUES ('Action'),('Adventure'),('Fantasy'),('Thriller'),('History'),('Mystery'),('Drama'),('Sci-Fi')
GO
PRINT 'Inserted data into Genre Table...'
GO

--This next section of code inserts data into the "Movie" table, in the VideoStoreDb database.
INSERT INTO Movie
VALUES ('Anonymous','The theory that it was in fact Edward De Vere, Earl of Oxford, who penned Shakespeare''s plays. Set against the backdrop of the succession of Queen Elizabeth I and the Essex rebellion against her.',5),
('Contagion','Healthcare professionals, government officials and everyday people find themselves in the midst of a worldwide epidemic as the CDC works to find a cure.',7),
('47 Ronin','A band of samurai set out to avenge the death and dishonor of their master at the hands of a ruthless shogun.',1),
('Inception','A thief who steals corporate secrets through use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.',8),
('Oblivion','A veteran assigned to extract Earth''s remaining resources begins to question what he knows about his mission and himself.',1),
('The Prestige','Two stage magicians engage in competitive one-upmanship in an attempt to create the ultimate stage illusion.',4),
('The Raven','When a madman begins committing horrific murders inspired by Edgar Allan Poe''s works, a young Baltimore detective joins forces with Poe to stop him from making his stories a reality.',4),
('Stoker','After India''s father dies, her Uncle Charlie, who she never knew existed, comes to live with her and her unstable mother. She comes to suspect this mysterious, charming man has ulterior motives and becomes increasingly infatuated with him.',6),
('The Sunset Limited','Two men in an apartment with their opposing beliefs.',7),
('Transcendence','A scientist''s drive for artificial intelligence, takes on dangerous implications when his consciousness is uploaded into one such program.',8),
('Unknown','A man awakens from a coma, only to discover that someone has taken on his identity and that no one, (not even his wife), believes him. With the help of a young woman, he sets out to prove who he is.',4),
('V is for Vendetta','In a future British tyranny, a shadowy freedom fighter plots to overthrow it with the help of a young woman.',4),
('Shutter Island','In 1954, U.S. Marshal Teddy Daniels is investigating the disappearance of a murderess who escaped from a hospital for the criminally insane and is presumed to be hiding near-by.',6),
('The Grey','After their plane crashes in Alaska, six oil workers are led by a skilled huntsman to survival, but a pack of merciless wolves haunts their every step.',4)
GO
PRINT 'Inserted data into Movie Table...'
GO

--This next section of code inserts data into the "Genre" table, in the VideoStoreDb database.
INSERT INTO User_Accounts
VALUES ('Admin','Admin')
GO
PRINT 'Inserted data into User_Accounts Table...'
GO

USE master
GO