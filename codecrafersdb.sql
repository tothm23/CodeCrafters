-- phpMyAdmin SQL Dump
-- version 5.1.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Feb 05, 2024 at 10:32 AM
-- Server version: 5.7.24
-- PHP Version: 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `codecraftersdb`
--
CREATE DATABASE IF NOT EXISTS `codecraftersdb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `codecraftersdb`;

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `basket` (IN `gameIdIN` INT(9), IN `userIdIN` INT(9))   BEGIN

    DECLARE gamePrice INT(9);
    DECLARE gameDiscount INT(9);

    SELECT game.price INTO gamePrice
    FROM game
    WHERE game.id = gameIdIN;

    SELECT game.discount INTO gameDiscount
    FROM game
    WHERE game.id = gameIdIN;

    INSERT INTO basket(
        basket.userId,
        basket.gameId,
        basket.amount
    )
    VALUES(
        userIdIN,
        gameIdIN,
        ROUND( gamePrice - (gamePrice * gameDiscount / 100), 0)
    );

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `bestsellers` ()   BEGIN

    SELECT 
        game.id,
        game.name,
        game.price,
        game.image,
        game.discount
    FROM game

    ORDER BY game.amountSold DESC
    LIMIT 3;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `carouselGames` ()   BEGIN

    SELECT 
        game.id,
        game.name,
        game.price,
        game.image,
        game.discount
    FROM game

    ORDER BY RAND()
    LIMIT 3;
    
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `checkGameId` (IN `gameIdIN` INT(9), OUT `countOUT` INT(1))   SELECT COUNT(game.id) 
INTO countOUT
FROM game
WHERE game.id = gameIdIN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `checkGameIdBasket` (IN `gameIdIN` INT(9), OUT `countOUT` INT(1))   SELECT COUNT(basket.gameId) 
INTO countOUT
FROM basket
WHERE basket.gameId = gameIdIN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `checkUserEmail` (IN `emailIN` VARCHAR(100), OUT `countOUT` INT(1))   SELECT COUNT(user.id) 
INTO countOUT
FROM user
WHERE user.email = emailIN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `checkUserId` (IN `userIdIN` INT(9), OUT `countOUT` INT(1))   SELECT COUNT(user.id) 
INTO countOUT
FROM user
WHERE user.id = userIdIN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `checkUserIdBasket` (IN `userIdIN` INT(9), OUT `countOUT` INT(1))   SELECT COUNT(basket.userId) 
INTO countOUT
FROM basket
WHERE basket.userId = userIdIN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `checkUserName` (IN `nameIN` VARCHAR(100), OUT `countOUT` INT(1))   SELECT COUNT(user.id) 
INTO countOUT
FROM user
WHERE user.userName = nameIN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteGameFromBasket` (IN `userIdIN` INT(9), IN `gameIdIN` INT(9))   DELETE 
FROM basket
WHERE basket.userId = userIdIN AND basket.gameId = gameIdIN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteUser` (IN `userIdIN` INT(9))   DELETE FROM user
WHERE user.id = userIdIN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `game` (IN `idIN` INT(9))   BEGIN

    SELECT 
        game.id,
        game.name AS "gameName",
        game.price,
        game.description,
        game.image,
        game.ageLimit,
        game.discount,
        game.inStock,
        device.name AS "deviceName",
        platform.name AS "platformName"
    FROM game

    INNER JOIN device
    ON game.deviceId = device.id

    INNER JOIN platform
    ON game.platformId = platform.id

    WHERE game.id = idIN;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `games` ()   BEGIN

    SELECT 
        game.id,
        game.name AS "gameName",
        game.price,
        game.image,
        game.ageLimit,
        game.discount,
        device.name AS "deviceName",
        platform.name AS "platformName"
    FROM game

    INNER JOIN device
    ON game.deviceId = device.id

    INNER JOIN platform
    ON game.platformId = platform.id;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `login` (IN `userNameIN` VARCHAR(100), IN `passwordIN` TEXT, OUT `userIdOUT` INT(9), OUT `userNameOUT` VARCHAR(100), OUT `lastNameOUT` VARCHAR(100), OUT `firstNameOUT` VARCHAR(100), OUT `emailOUT` VARCHAR(100))   SELECT 
	user.id,
    user.userName,
    user.lastName,
    user.firstName,
    user.email
INTO
	userIdOUT,
    userNameOUT,
    lastNameOUT,
    firstNameOUT,
    emailOUT
FROM user

WHERE user.userName = userNameIN AND user.password = SHA2(passwordIN, 256)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `newGames` ()   BEGIN

    SELECT 
        game.id,
        game.name,
        game.price,
        game.image,
        game.discount
    FROM game

    ORDER BY game.createdAt DESC
    LIMIT 3;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `order` (IN `userIdIN` INT(9))   BEGIN

    DECLARE total INT(9);

    SELECT SUM(basket.amount) INTO total
    FROM basket
    WHERE basket.userId = userIdIN;

    INSERT INTO order_(
        order_.userId,
        order_.totalAmount
    )
    VALUES(
        userIdIN,
        total
    );

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `productKeys` (IN `userIdIN` INT(9))   BEGIN

    SELECT 
        basket.gameId,
        user.email,
        user.userName,
        game.name,
        basket.amount,
        productkey.key
    FROM basket

    INNER JOIN user
    ON basket.userId = user.id

    INNER JOIN game
    ON basket.gameId = game.id

    INNER JOIN productkey
    ON basket.gameId = productkey.gameId

    WHERE basket.userId = userIdIN;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `registration` (IN `userNameIN` VARCHAR(100), IN `lastNameIN` VARCHAR(100), IN `firstNameIN` VARCHAR(100), IN `emailIN` VARCHAR(100), IN `passwordIN` TEXT)   INSERT INTO user(
	user.userName,
    user.lastName,
    user.firstName,
    user.email,
    user.password
)
VALUES(
	userNameIN,
    lastNameIN,
    firstNameIN,
    emailIN,
    SHA2(passwordIN, 256)
)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateUser` (IN `idIN` INT(9), IN `userNameIN` VARCHAR(100), IN `lastNameIN` VARCHAR(100), IN `firstNameIN` VARCHAR(100), IN `passwordIN` TEXT)   UPDATE user
SET user.userName = userNameIN,
	user.lastName = lastNameIN,
    user.firstName = firstNameIN,
  	user.password = SHA1(passwordIN),
    user.updatedAt = CURRENT_TIMESTAMP
WHERE user.id = idIN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `user` (IN `idIN` INT(9))   SELECT 
	user.id,
    user.userName,
    user.lastName,
    user.firstName,
    user.email
FROM user

WHERE user.id = idIN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `userBasket` (IN `userIdIN` INT(9))   BEGIN

    SELECT 
        basket.userId,
        game.id,
        game.name,
        basket.amount,
        game.image
    FROM basket

    INNER JOIN game
    ON basket.gameId = game.id

    WHERE basket.userId = userIdIN;

END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `basket`
--

CREATE TABLE `basket` (
  `id` int(9) NOT NULL,
  `userId` int(9) NOT NULL,
  `gameId` int(9) NOT NULL,
  `amount` int(9) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `device`
--

CREATE TABLE `device` (
  `id` int(9) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `device`
--

INSERT INTO `device` (`id`, `name`) VALUES
(7, 'Nintendo Switch'),
(1, 'PC'),
(5, 'PS4'),
(6, 'PS5'),
(2, 'XBOX One'),
(3, 'XBOX Series S'),
(4, 'XBOX Series X');

-- --------------------------------------------------------

--
-- Table structure for table `game`
--

CREATE TABLE `game` (
  `id` int(9) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` int(9) NOT NULL,
  `description` text NOT NULL,
  `image` varchar(100) NOT NULL,
  `ageLimit` int(2) NOT NULL,
  `discount` int(3) NOT NULL DEFAULT '0',
  `inStock` int(5) NOT NULL,
  `deviceId` int(9) NOT NULL,
  `platformId` int(9) NOT NULL,
  `createdAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `amountSold` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `game`
--

INSERT INTO `game` (`id`, `name`, `price`, `description`, `image`, `ageLimit`, `discount`, `inStock`, `deviceId`, `platformId`, `createdAt`, `amountSold`) VALUES
(1, 'The Witcher 3: Wild Hunt', 5990, 'A The Witcher 3: Wild Hunt egy fantasy szerepjáték, melyben Geralt of Rivia karakterét irányítva különböző küldetéseket teljesíthetsz egy nyitott világban.', 'witcher3.jpg', 18, 15, 100, 1, 1, '2023-11-11 11:57:24', 0),
(2, 'Counter-Strike: Global Offensive', 1490, 'A Counter-Strike: Global Offensive egy taktikai lövöldözős játék, ahol két csapat, a terroristák és az antiterroristák egymás ellen küzdenek.', 'csgo.jpg', 16, 0, 300, 1, 1, '2023-11-11 12:02:56', 1),
(3, 'Minecraft', 2990, 'A Minecraft egy sandbox játék, ahol a játékosok kockákból építhetnek és felfedezhetnek egy végtelen világot.', 'minecraft.jpg', 7, 0, 200, 7, 3, '2023-11-11 12:04:14', 0),
(4, 'FC 24', 9990, 'A FC 24 egy futballszimulátor játék, ahol a játékosok vezethetik kedvenc csapatukat és részt vehetnek különböző versenyeken.', 'fc24.jpg', 7, 20, 150, 2, 2, '2023-11-11 12:04:14', 0),
(5, 'Grand Theft Auto V', 6990, 'A Grand Theft Auto V egy akció-kaland játék, melyben a játékosok Los Santos városában szabadon közlekedhetnek és különböző küldetéseket teljesíthetnek.', 'gta5.jpg', 18, 0, 100, 1, 3, '2023-11-11 12:14:09', 0),
(6, 'Red Dead Redemption 2: Ultimate Edition', 28990, 'Amerika, 1899. A vadnyugat hőskora lassan véget ér, ahogy a törvény emberei az utolsó bűnbandákat is levadásszák. Aki nem hajlandó megadni magát, halál fia. Arthur Morgan és a Van der Linde banda egy félresikerült rablási kísérlet miatt menekülni kényszerül Blackwater városából. Miközben szövetségi ügynökök és az ország legjobb fejvadászai lihegnek a nyomukban, a bandának nincs más választása, mint hogy a túlélés érdekében rabolva és fosztogatva átverekedjék magukat Amerika könyörtelen belvidékein. Amikor az egyre élesedő belső ellentétek a banda szétszakításával fenyegetnek, Arthur kénytelen eldönteni, hogy saját elveihez marad hű, vagy a bandához, amely felnevelte.', 'RDR2.jpg', 18, 0, 235, 1, 3, '2023-11-13 21:57:50', 0),
(7, 'Alan Wake 2', 17900, 'Az Alan Wake 2 egy túlélőhorror játék, amit a Remedy Entertainment stúdió fejlesztett ki. Ez a 2010-ben megjelent Alan Wake folytatása.', 'AlanWake2.jpg', 18, 0, 50, 6, 1, '2023-11-13 22:06:04', 2),
(8, 'Star Wars Battlefront 2', 13990, 'A Star Wars Battlefront II-ban lehetőséged nyílik átvenni az irányítást Iden Versio-n, a Birodalom jól ismert Inferno Squad-jának parancsnoka felett, hogy a mintegy 30 évtizedet felölelő kampány során megismerhess egy izgalmas sztorit a bosszúról, az árulásról és a megváltásról, méghozzá a mozifilmekhez kapcsolódó áthallásokkal és szereplőkkel a középpontban.', 'BF2.jpg', 16, 15, 188, 5, 1, '2023-11-13 22:09:22', 0),
(9, 'Battlefield 2042', 19990, 'Az Amerikai Egyesült Államok és Oroszország a háború szélére sodródott. Miután a két nagyhatalom peremháborúi már évtizedek óta tartanak, számos tapasztalt veterán harcol a világban, akiknek elege van a fennálló rendszerből. Ezek a specialisták a saját, különleges harci képzettségüket arra használják, hogy alakítsák az emberiség jövőjét. Ebben a futurisztikus háborúban vehetsz részt te is a Battlefield 2042 harcosaként.', 'BF2042.png', 16, 0, 150, 2, 2, '2023-11-13 22:18:27', 0),
(10, 'Tom Clancy\'s Rainbow Six Siege', 6660, 'A taktikai FPS-ek egyik legjobbjaként számon tartott Rainbow Six-sorozat legújabb epizódjában ismét megtapasztalhatod, hogy milyen egy speciális katonai egység tagjaként csapatban, parancsra engedelmeskedni, és egy előre meghatározott stratégia mentén együtt végrehajtani a feladatokat.', 'R6.jpg', 18, 0, 125, 1, 4, '2023-11-13 22:23:30', 1),
(11, 'Assassin\'s Creed Odyssey', 6990, 'Írd meg saját odüsszeiádat, és válj legendás spártai hőssé az Assassin’s Creed sorozat legnagyobb és legszebb epizódjában! Több száz évvel az Assassin’s Creed Origins eseményei előtt, az ókori Görögországban háború dúl Spárta és a görögök között. Ez a te lehetőséged, hogy kitaszítottból igazi legendává válj! Ez az első eset, hogy a nyílt világú Assassin’s Creed sorozat valódi RPG-vé válik. Irányíthatod a beszélgetéseket, hatással lehetsz a küldetések alakulására és a történet végkimenetelére. Fejleszd saját férfi vagy női karakteredet, szerezz új fegyvereket, lopakodj, támadj, barangolj és hajózz a lenyűgöző világban. A legősibb kaland lesz a legnagyobb!', 'ACOdyssey.jpg', 18, 0, 150, 2, 4, '2023-12-01 18:12:39', 0),
(12, 'Assassin\'s Creed Mirage', 14900, 'Éld át Basim, az Assassin’s Creed Valhallában megismert bérgyilkos izgalmas történetét az Assassin’s Creed Mirage játékban! Basim egy furfangos utcai tolvaj volt rémálomszerű víziókkal, aki válaszokat és igazságot keresett a 9. századi Bagdad utcáin. Miután találkozott a rejtélyes szervezettel, a Hidden Ones-szal, halálos bérgyilkos vált belőle, és oly módon változott meg a sorsa, ahogyan sosem gondolta volna. Térj vissza a legendás sorozat gyökereihez, és ismerd meg az egyik leghalálosabb bérgyilkos izgalmas történetét!', 'ACMirage.jpg', 18, 0, 220, 6, 4, '2023-12-01 18:19:26', 0),
(13, 'The Sims 4', 2990, 'Átesett a generációváltáson a még mindig rendkívüli népszerűségnek örvendő The Sims-sorozat is, így a műfajában egyedinek tekinthető élet-szimulátor keretein belül ezúttal minden eddiginél nagyobb szabadság, felejthetetlen élmények, valamint a virtuális világ szépségei várnak rád. A The Sims 4-ben tehát lehetőséged lesz valóra váltani minden olyan álmodat, amire a való életben korántsem biztos, hogy képes lennél, mindezt pedig megerősíthetjük azzal, hogy a minden eddiginél komolyabb lehetőséget kínáló Create-A-Sim opcióban nem csak külsőleg, hanem belsőleg is megalkothatod virtuális másodat.', 'Sims4.jpg', 12, 0, 70, 5, 2, '2023-12-01 18:30:38', 0),
(14, 'Battlefield V', 6990, 'Vegyél részt az emberiség történelmének legnagyobb háborújában, ahogy a híres lövöldözős játéksorozat visszatér a gyökereihez, és úgy ábrázolja a II. világháborút, mint még soha! Állítsd össze a csapatodat teljes egészében testreszabható katonákból, fegyverekből és járművekből – aztán vidd el őket egy epikus kalandra a Tides of War játékmódban! Éld át az eddigi legélethűbb Battlefield élményt, az egyjátékos történetekből felépülő War Stories kampánytól kezdve a klasszikus és új multiplayer játékmódokon át egészen a hatalmas Grand Operations módig. Élvezd, hogy nincs Premium Pass, mindenki megkapja az összes kiegészítőt. Fogd barátaidat, és vessétek bele magatokat az eddigi legnagyobb szabású Battlefield játékba!', 'BFV.jpg', 16, 0, 140, 5, 2, '2023-12-01 18:41:13', 0),
(15, 'Call of Duty: Modern Warfare III', 27900, 'Egy évvel a minden rekordot megdöntő Call of Duty: Modern Warfare II után itt a közvetlen folytatás, a Call of Duty: Modern Warfare III. Captain Price és a Task Force 141 a legvégső fenyegetéssel néz szembe a Sledgehammer Games és az Infinity Ward nagyszabású produkciójában. Az ultranacionalista háborús bűnök, Vladimir Makarov visszatért, és folyamatosan növeli befolyását és hatalmát a világban, így a Task Force 141-nek keményebben kell harcolnia, mint valaha. Ez a végső konfliktus egy mindent elpusztító harmadik világháború peremére sodródott helyzetben!', 'CoDMW3.jpg', 18, 0, 390, 6, 1, '2023-12-01 18:51:27', 0),
(16, 'Animal Crossing: New Horizons', 20900, 'A Nintendo legendás Animal Crossing sorozatának legújabb része a Switch konzol legmodernebb funkcióit és képességeit kihasználva egy minden korábbinál látványosabb és izgalmasabb kalandra hív. Ha a modern élet mókuskerekét és viszontagságait magad mögött szeretnéd hagyni, akkor irány a Nook Inc. lakatlan szigete, ahol új kihívások várnak! Irány a sziget, ahol egy új élet és új kalandok várnak a horizonton túl.', 'AnimalCrossing.jpg', 3, 0, 166, 7, 3, '2023-12-01 19:05:45', 0),
(17, 'Super Smash Bros. Ultimate', 23900, 'Ez a harc lesz a végső! A Nintendo egyik legnagyobb sikersorozata, a Smash Bros. végre Switch-re is ellátogat, a valaha készült legszebb és legtartalmasabb epizóddal. A Super Smash Bros. Ultimate az összes szereplőt tartalmazza, aki valaha felbukkant a sorozat epizódjaiban, ez összesen 66 karaktert jelent! Mindenki itt van a Nintendo univerzumából, de a Capcom és más kiadók hősei is megjelennek, hogy borsot törjenek egymás orra alá. A számtalan figurához rengeteg pálya is dukál, melyek között új, és jelentősen felújított korábbi helyszínek is akadnak. A verekedős játék egyjátékos módokat is kínál, de a 4 fős multiplayert nem übereli semmi, ez a Smash Bros legvonzóbb játékmódja. Vesd bele magad te is a Nintendo univerzum legnagyobb össznépi bunyójába!', 'SSBU.jpg', 12, 20, 110, 7, 3, '2023-12-01 19:14:06', 0),
(18, 'The Legend of Zelda: Tears of the Kingdom', 23000, 'A 2017-es, világsikert aratott Legend of Zelda: Breath of the Wild folytatása egy még szebb, még nagyobb kaland, amelyben a főhős, Link immár nemcsak Hyrule világát, hanem a felette elhelyezkedő égi világot is felfedezheti. Készülj fel életed egyik legnagyobb kalandjára, kizárólag Nintendo Switch-en!', 'TLoZToK.jpg', 12, 0, 99, 7, 3, '2023-12-01 19:19:27', 0),
(19, 'Mortal Kombat 1', 17900, 'Fedezd fel az újjászületett Mortal Kombat Univerzumot, amelyet a Tűzisten, Liu Kang hozott létre, a Mortal Kombat 1 játékkal! Ez az ikonikus verekedős játéksorozat új korszakának kezdete, vadonatúj harcrendszerrel, új játékmódokkal és kivégzésekkel! Készülj fel egy új univerzumra, új hősökre és az új háborúra. A Földet továbbra is meg kell védeni! Új korszak kezdődik a Mortal Kombat 1 világában.', 'MK1.jpg', 18, 0, 150, 4, 1, '2023-12-02 17:18:02', 0),
(20, 'Assassin\'s Creed Valhalla', 9000, 'Teremtsd meg a saját viking legendádat az Assassin’s Creed Valhalla gigászi kalandjában! Ez minden idők legnagyobb Assassin’s Creed játéka, amelyen nem kevesebb, mint 14 Ubisoft stúdió dolgozott! Bújj Eivor, a legendás viking hódító bőrébe, és vezesd klánodat a fagyos Norvégia partjaitól egészen Anglia buja tájaiig a 9. században. Fedezz fel egy gyönyörű és rejtélyes világot, ahol brutális ellenségek várnak, és lenyűgöző kastélyokat igázhatsz le. Építs saját települést, bővítsd új épületekkel és létesítményekkel. Szerezz szövetségeseket a világban, és írd be nevedet Valhalla történelmébe!', 'ACV.jpg', 18, 0, 56, 4, 4, '2023-12-02 17:25:28', 0),
(21, 'Dying Light 2', 11900, 'Életben tudsz maradni egy zombik által ostromolt városban? Az apokalipszis kitörése után túlélők csoportjai próbálnak boldogulni a mindennapokban, és ehhez neked is segítened kell: járd be a hatalmas várost, teljesíts küldetéseket a különböző frakcióknak, és védd meg a közösséget a külső támadásokról. A Dying Light 2 szebb, és sokkal nagyobb, mint az első rész, új mozdulatokkal még könnyebben közlekedhetsz a városban. A játéktér dinamikus, a döntéseidtől függően átalakul a környezet, és a történet is új fordulatokat vehet!', 'DL2.jpg', 18, 0, 156, 4, 2, '2023-12-02 17:27:53', 0),
(22, 'Star Wars: Jedi Survivor Deluxe Edition', 29990, 'Cal Kestis története folytatódik a Star Wars Jedi: Survivor játékban, amely egy külső nézetes, galaxison átívelő akció-kalandjáték a Respawn Entertainment és a Lucasfilm Games fejlesztésében. Ez a történetvezérelt, egyjátékos cím öt évvel a Star Wars Jedi: Fallen Order eseményei után kezdődik, és Cal egyre elkeseredettebb harcát követi, amint a galaxis egyre mélyebb sötétségbe süllyed. A Birodalom által a galaxis pereméig üldözött Cal ismerős és új fenyegetésekkel találja szembe magát. Az egyik utolsó Jedi túlélőként Cal kénytelen kiállni a szabadságért a galaxis legsötétebb óráiban – de milyen messzire képes elmenni azért, hogy megvédje magát, csapatát, és a Jedi rendet?', 'SWJS.jpg', 12, 10, 68, 3, 2, '2023-12-02 17:33:20', 0),
(23, 'Forza Motorsport', 24990, 'Az Xbox legnagyobb autós sorozata, a Forza Motorsport visszatér egy vadonatúj, következő generációs epizóddal! Az új Forza Motorsport az Xbox Series X|S konzolok képességeihez készül, 4K felbontást, 60 fps képfrissítést és sugárkövetéses (raytracing) technológiát kínál, ezzel minden idők legélethűbb Xboxos autós játéka lesz. Előtérbe kerül az autóversenyzés: ülj be a legmodernebb versenygépek volánja mögé, vegyél részt nagyszabású bajnokságokban, hosszútávú versenyeken, ahol a minden korábbinál realisztikusabb fizika, a box stratégia és az időjárás is komoly szerepet játszik.', 'ForzaM.png', 3, 0, 231, 3, 1, '2023-12-02 17:39:18', 0),
(24, 'The Elder Scrolls V Skyrim Special Edition', 7999, 'Újgenerációs köntösbe bújik az utóbbi évtized egyik legjobb szerepjátékaként számon tartott The Elder Scrolls V: Skyrim, amelynek PS4-es változata 1080p-s felbontással futtatja a csodaszép textúrákat, aminek köszönhetően páratlanul elragadó látványvilág társaságában élheted át a Skyrim földjére kalauzoló, sárkányvadászattal tarkított kalandokat.', 'ESVSkyrim.jpg', 18, 0, 143, 3, 4, '2023-12-02 17:44:44', 0),
(25, 'Resident Evil 4', 22900, 'Éld át újra minden idők egyik legjobb túlélő-horror játékát, a Resident Evil 4-et, hihetetlen módon felújított grafikával és új játékmeneti elemekkel! A Capcom a korábbi Resident Evil játékokhoz hasonlóan a legendás negyedik epizódot is teljeskörűen felújította, hogy a meghatározó kaland egy új generáció számára is elérhető legyen.\r\n\r\nA Resident Evil 4-ben a túlélés csak a kezdet. A Raccoon City-ben történt biológiai katasztrófa óta hat év telt el. Leon S. Kennedy ügynök, az incidens egyik túlélője új küldetésre indul, hogy megmentse az elnök elrabolt lányát. A nyomok egy elhagyatott európai kisvárosba vezetnek, ahol valami komoly baj van a helyi lakókkal. Élet és halál, a terror és a katarzis rémületes érzése találkozik, ahogy felgördül a függöny, és kezdetét veszi egy emlékezetes történet.', 'RE4.jpg', 18, 10, 274, 6, 1, '2023-12-02 17:49:28', 0),
(26, 'Resident Evil 3', 9999, 'A Capcom a Resident Evil 2 remake óriási sikere után a sorozat rajongóinak egyik kedvencét, a Resident Evil 3-at is felújított formában teszi újra elérhetővé! A klasszikus játék a Capcom saját RE Engine grafikus motorjával születik újjá, lenyűgözően élethű grafikával, új kameranézettel, és minden korábbinál félelmetesebb hangulattal.\r\n\r\nA Resident Evil 3-ban Jill Valentine az egyik utolsó túlélő Raccoon City-ben, aki egyúttal annak is tanúja, hogy mit tesz az Umbrella Corporation a zombi apokalipszis eltusolásának érdekében. A cég, hogy megfékezze Jill-t, elszabadítja a legszörnyűbb kísérletét: megérkezik Nemesis!', 'RE3.jpg', 18, 0, 197, 5, 4, '2023-12-02 17:57:07', 0);

-- --------------------------------------------------------

--
-- Table structure for table `order_`
--

CREATE TABLE `order_` (
  `id` int(9) NOT NULL,
  `userId` int(9) NOT NULL,
  `totalAmount` int(9) NOT NULL,
  `orderedAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `platform`
--

CREATE TABLE `platform` (
  `id` int(9) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `platform`
--

INSERT INTO `platform` (`id`, `name`) VALUES
(3, 'Epic Games'),
(2, 'Origin'),
(1, 'Steam'),
(4, 'Ubisoft');

-- --------------------------------------------------------

--
-- Table structure for table `productkey`
--

CREATE TABLE `productkey` (
  `id` int(9) NOT NULL,
  `key` char(50) NOT NULL,
  `gameId` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `productkey`
--

INSERT INTO `productkey` (`id`, `key`, `gameId`) VALUES
(1, 'a1de-2jg2-jas7', 1),
(2, 'b2fg-1hd3-k9s8', 2),
(3, 'k1op-2qr3-3st4', 3),
(4, 'l2uv-3wx4-4yz5', 4),
(5, 'm3ab-4cd5-5ef6', 5),
(6, 'n4gh-5ij6-6kl7', 6),
(7, 'o5mn-6op7-7qr8', 7),
(8, 'p6st-7uv8-8wx9', 8),
(9, 'q7yz-8ab0-9cd1', 9),
(10, 'r8ef-9gh0-0ij2', 10),
(11, 's9kl-0mn1-op2q', 11),
(12, 't0qr-1st2-2uv3', 12),
(13, 'u1wx-2yz3-3ab4', 13),
(14, 'v2cd-3ef4-4gh5', 14),
(15, 'w3ij-4kl5-5mn6', 15),
(16, 'x4op-5qr6-6st7', 16),
(17, 'y5uv-6wx7-7yz8', 17),
(18, 'z6ab-7cd8-8ef9', 18),
(19, 'a7gh-8ij9-9kl0', 19),
(20, 'b8mn-9op0-0qr1', 20),
(21, 'c9st-0uv1-1wx2', 21),
(22, 'd0yz-1ab2-2cd3', 22),
(23, 'e1ef-2gh3-3ij4', 23),
(24, 'f2kl-3mn4-4op5', 24),
(25, 'g3qr-4st5-5uv6', 25),
(26, 'h4ij-5kl6-6mn7', 26);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(9) NOT NULL,
  `userName` varchar(100) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `firstName` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` text NOT NULL,
  `createdAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `userName`, `lastName`, `firstName`, `email`, `password`, `createdAt`, `updatedAt`) VALUES
(1, 'SunnyDay21', 'Kéri', 'Bence', 'keribence0@gmail.com', 'c50797c1a6a1f41b96234209b5f59300cfdcc1ee489d91cde8b97cbd20270018', '2024-02-05 11:31:27', NULL),
(2, 'AdventureSeeker', 'Nagy', 'Péter', 'peter.nagy@example.com', '3e89a2becc5561f645e8898cb198ff7e9d97f90482d486addee0dce8a0c8d475', '2024-02-05 11:31:27', NULL),
(3, 'MusicLover88', 'Tóth', 'Eszter', 'eszter.toth@example.com', 'c68a132c5365595dc889cedd4be5b2cdde2167c06d3557d521ac2d64c20aa2b1', '2024-02-05 11:31:27', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `basket`
--
ALTER TABLE `basket`
  ADD PRIMARY KEY (`id`),
  ADD KEY `felhasznaloId` (`userId`,`gameId`);

--
-- Indexes for table `device`
--
ALTER TABLE `device`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Egyedi` (`name`);

--
-- Indexes for table `game`
--
ALTER TABLE `game`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Egyedi` (`name`,`image`),
  ADD KEY `kategoriaId` (`deviceId`,`platformId`);

--
-- Indexes for table `order_`
--
ALTER TABLE `order_`
  ADD PRIMARY KEY (`id`),
  ADD KEY `felhasznaloId` (`userId`);

--
-- Indexes for table `platform`
--
ALTER TABLE `platform`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Egyedi` (`name`);

--
-- Indexes for table `productkey`
--
ALTER TABLE `productkey`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `kulcs` (`key`),
  ADD UNIQUE KEY `jatekId` (`gameId`),
  ADD KEY `ajandekKartyaId` (`gameId`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Egyedi` (`userName`,`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `basket`
--
ALTER TABLE `basket`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `device`
--
ALTER TABLE `device`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `game`
--
ALTER TABLE `game`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `order_`
--
ALTER TABLE `order_`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `platform`
--
ALTER TABLE `platform`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `productkey`
--
ALTER TABLE `productkey`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
