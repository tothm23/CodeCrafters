-- phpMyAdmin SQL Dump
-- version 5.1.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Dec 19, 2023 at 05:39 PM
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `3veletlenjatek` ()   SELECT 
	jatek.id,
    jatek.nev AS "jatekNev",
    jatek.ar,
    jatek.leiras,
    jatek.kep,
    jatek.korhatar,
    jatek.akcio,
    jatek.mennyisegraktaron,
    eszkoz.nev AS "eszkozNev",
    platform.nev AS "platformNev"
FROM jatek

INNER JOIN eszkoz
ON jatek.eszkozId = eszkoz.id

INNER JOIN platform
ON jatek.platformId = platform.id

ORDER BY RAND()
LIMIT 3$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `carouselRandomKepek` ()   SELECT jatek.kep
FROM jatek
ORDER BY RAND()
LIMIT 3$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `csokkenoArJatek` ()   SELECT *
FROM jatek
ORDER BY jatek.ar DESC$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `eszkozNevEllenorzes` (IN `nevBE` VARCHAR(100), OUT `dbKI` INT(1))   SELECT COUNT(eszkoz.id) INTO dbKI
FROM eszkoz
WHERE eszkoz.nev = nevBE$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `felhasznalo` (IN `idBE` INT(9))   SELECT *
FROM felhasznalo
WHERE felhasznalo.id = idBE$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `felhasznaloBelepes` (IN `felhasznaloNevBE` VARCHAR(100), IN `jelszoBE` TEXT, OUT `felhasznaloIdKI` INT(9))   SELECT felhasznalo.id
FROM felhasznalo
WHERE felhasznalo.felhasznaloNev = felhasznaloNevBE AND felhasznalo.jelszo = SHA1(jelszoBE)
INTO felhasznaloIdKI$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `felhasznaloEmailEllenorzes` (IN `emailBE` VARCHAR(100), OUT `dbKI` INT(1))   SELECT COUNT(felhasznalo.id) INTO dbKI
FROM felhasznalo
WHERE felhasznalo.email = emailBE$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `felhasznaloIdEllenorzes` (IN `felhasznaloIdBE` INT(9), OUT `dbKI` INT(1))   SELECT COUNT(felhasznalo.id) INTO dbKI
FROM felhasznalo
WHERE felhasznalo.id = felhasznaloIdBE$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `felhasznaloIdKosarEllenorzes` (IN `felhasznaloIdBE` INT(9), OUT `dbKI` INT(1))   SELECT COUNT(kosar.felhasznaloId) INTO dbKI
FROM kosar
WHERE kosar.felhasznaloId = felhasznaloIdBE$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `felhasznalok` ()   SELECT *
FROM felhasznalo$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `felhasznaloKosar` (IN `felhasznaloIdBE` INT(9))   BEGIN

SELECT 
	kosar.felhasznaloId,
    jatek.id as "jatekId",
	jatek.nev AS "jatekNev",
    kosar.vegosszeg AS "jatekAr",
    jatek.kep AS "jatekKep"
FROM kosar

INNER JOIN jatek
ON kosar.jatekId = jatek.id

WHERE kosar.felhasznaloId = felhasznaloIdBE;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `felhasznaloNevEllenorzes` (IN `nevBE` VARCHAR(100), OUT `dbKI` INT(1))   SELECT COUNT(felhasznalo.id) INTO dbKI
FROM felhasznalo
WHERE felhasznalo.felhasznaloNev = nevBE$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `frissitesFelhasznalo` (IN `idBE` INT(9), IN `felhasznaloNevBE` VARCHAR(100), IN `vezetekNevBE` VARCHAR(100), IN `keresztNevBE` VARCHAR(100), IN `jelszoBE` TEXT)   UPDATE felhasznalo
SET felhasznalo.felhasznaloNev = felhasznaloNevBE,
	felhasznalo.vezetekNev = vezetekNevBE,
    felhasznalo.keresztNev = keresztNevBE,
  	felhasznalo.jelszo = SHA1(jelszoBE),
    felhasznalo.frissitve = CURRENT_TIMESTAMP
WHERE felhasznalo.id = idBE$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `frissitesJatek` (IN `idBE` INT(9), IN `nevBE` VARCHAR(100), IN `arBE` INT(9), IN `leirasBE` TEXT, IN `kepBE` VARCHAR(100), IN `korhatarBE` INT(2), IN `akcioBE` INT(3), IN `mennyisegraktaronBE` INT(5), IN `eszkozIdBE` INT(9), IN `platformIdBE` INT(9))   UPDATE jatek
SET jatek.nev = nevBE,
	jatek.ar = arBE,
    jatek.leiras = leirasBE,
    jatek.kep = kepBE,
    jatek.korhatar = korhatarBE,
    jatek.akcio = akcioBE,
    jatek.mennyisegraktaron = mennyisegraktaronBE,
    jatek.eszkozId = eszkozIdBE,
    jatek.platformId = platformIdBE,
    jatek.frissitve = CURRENT_TIMESTAMP
WHERE jatek.id = idBE$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `jatek` (IN `idBe` INT(9))   SELECT 
	jatek.id,
    jatek.nev AS "jatekNev",
    jatek.ar,
    jatek.leiras,
    jatek.kep,
    jatek.korhatar,
    jatek.akcio,
    jatek.mennyisegraktaron,
    eszkoz.nev AS "eszkozNev",
    platform.nev AS "platformNev"
FROM jatek

INNER JOIN eszkoz
ON jatek.eszkozId = eszkoz.id

INNER JOIN platform
ON jatek.platformId = platform.id

WHERE jatek.id = idBe$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `jatekIdEllenorzes` (IN `jatekIdBE` INT(9), OUT `dbKI` INT(1))   SELECT COUNT(jatek.id) INTO dbKI
FROM jatek
WHERE jatek.id = jatekIdBE$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `jatekIdKosarEllenorzes` (IN `jatekIdBE` INT(9), OUT `dbKI` INT(1))   SELECT COUNT(kosar.jatekId) INTO dbKI
FROM kosar
WHERE kosar.jatekId = jatekIdBE$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `jatekKepEllenorzes` (IN `kepBE` VARCHAR(100), OUT `dbKI` INT(1))   SELECT COUNT(jatek.id) INTO dbKI
FROM jatek
WHERE jatek.kep = kepBE$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `jatekNevEllenorzes` (IN `nevBE` VARCHAR(100), OUT `dbKI` INT(1))   SELECT COUNT(jatek.id) INTO dbKI
FROM jatek
WHERE jatek.nev = nevBE$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `jatekok` ()   SELECT 
	jatek.id,
    jatek.nev AS "jatekNev",
    jatek.ar,
    jatek.leiras,
    jatek.kep,
    jatek.korhatar,
    jatek.akcio,
    jatek.mennyisegraktaron,
    eszkoz.nev AS "eszkozNev",
    platform.nev AS "platformNev"
FROM jatek

INNER JOIN eszkoz
ON jatek.eszkozId = eszkoz.id

INNER JOIN platform
ON jatek.platformId = platform.id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `kosar` (IN `jatekIdBE` INT(9), IN `felhasznaloIdBE` INT(9))   BEGIN

DECLARE jatekAr INT(9);
DECLARE jatekAkcio INT(9);

SELECT jatek.ar INTO jatekAr
FROM jatek
WHERE jatek.id = jatekIdBE;

SELECT jatek.akcio INTO jatekAkcio
FROM jatek
WHERE jatek.id = jatekIdBE;

INSERT INTO kosar(
    kosar.felhasznaloId,
    kosar.jatekId,
    kosar.vegosszeg
)
VALUES(
    felhasznaloIdBE,
	jatekIdBE,
    ROUND( jatekAr - (jatekAr * jatekAkcio / 100), 0)
);

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `novekvoArJatek` ()   SELECT *
FROM jatek
ORDER BY jatek.ar ASC$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `osszesTermek` ()   BEGIN

SELECT 
	jatek.id,
    jatek.nev AS "jatekNev",
    jatek.ar,
    jatek.leiras,
    jatek.kep,
    jatek.korhatar,
    jatek.akcio,
    jatek.mennyisegraktaron,
    eszkoz.nev AS "eszkozNev",
    platform.nev AS "platformNev"
FROM jatek

INNER JOIN eszkoz
ON jatek.eszkozId = eszkoz.id

INNER JOIN platform
ON jatek.platformId = platform.id;

SELECT 
    ajandekkartya.id,
    ajandekkartya.nev AS "ajandekKartyaNev",
    ajandekkartya.ar,
    ajandekkartya.leiras,
    ajandekkartya.kep,
    ajandekkartya.akcio,
    ajandekkartya.mennyisegraktaron,
    eszkoz.nev AS "eszkozNev",
    platform.nev AS "platformNev"
FROM ajandekkartya

INNER JOIN eszkoz
ON ajandekkartya.eszkozId = eszkoz.id

INNER JOIN platform
ON ajandekkartya.platformId = platform.id;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `platformNevEllenorzes` (IN `nevBE` VARCHAR(100), OUT `dbKI` INT(1))   SELECT COUNT(platform.id) INTO dbKI
FROM platform
WHERE platform.nev = nevBE$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `rendeles` (IN `felhasznaloIdBE` INT(9))   BEGIN

DECLARE vegosszeg INT(9);

SELECT SUM(kosar.vegosszeg) INTO vegosszeg
FROM kosar
WHERE kosar.felhasznaloId = felhasznaloIdBE;

INSERT INTO rendeles(
    rendeles.felhasznaloId,
    rendeles.vegosszeg
)
VALUES(
    felhasznaloIdBE,
    vegosszeg
);

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `termekKulcs` (IN `felhasznaloIdBE` INT(9))   SELECT 
    kosar.jatekId,
    felhasznalo.email,
    felhasznalo.felhasznaloNev,
    jatek.nev AS "jatekNev",
    kosar.vegosszeg AS "jatekAr",
    termekkulcs.kulcs
FROM kosar

INNER JOIN felhasznalo
ON kosar.felhasznaloId = felhasznalo.id

INNER JOIN jatek
ON kosar.jatekId = jatek.id

INNER JOIN termekkulcs
ON kosar.jatekId = termekkulcs.jatekId

WHERE kosar.felhasznaloId = felhasznaloIdBE$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `torlesFelhasznalo` (IN `felhasznaloIdBE` INT(9))   DELETE FROM felhasznalo
WHERE felhasznalo.id = felhasznaloIdBE$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `torlesJatek` (IN `idBE` INT(9))   DELETE
FROM jatek
WHERE jatek.id = idBE$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `torlesJatekKosarbol` (IN `felhasznaloIdBE` INT(9), IN `jatekIdBE` INT(9))   DELETE 
FROM kosar
WHERE kosar.felhasznaloId = felhasznaloIdBE AND kosar.jatekId = jatekIdBE$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ujFelhasznalo` (IN `felhasznaloNevBE` VARCHAR(100), IN `vezetekNevBE` VARCHAR(100), IN `keresztNev` VARCHAR(100), IN `emailBE` VARCHAR(100), IN `jelszoBE` TEXT)   INSERT INTO felhasznalo(
	felhasznalo.felhasznaloNev,
    felhasznalo.vezetekNev,
    felhasznalo.keresztNev,
    felhasznalo.email,
    felhasznalo.jelszo
)
VALUES(
	felhasznaloNevBE,
    vezetekNevBE,
    keresztNev,
    emailBE,
    SHA1(jelszoBE)
)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ujJatek` (IN `nevBE` VARCHAR(100), IN `arBE` INT(9), IN `leirasBE` TEXT, IN `kepBE` VARCHAR(100), IN `korhatarBE` INT(2), IN `akcioBE` INT(3), IN `mennyisegraktaronBE` INT(5), IN `eszkozIdBE` INT(9), IN `platformIdBE` INT(9))   INSERT INTO jatek(
	jatek.nev,
    jatek.ar,
    jatek.leiras,
    jatek.kep,
    jatek.korhatar,
    jatek.akcio,
    jatek.mennyisegraktaron,
    jatek.eszkozId,
    jatek.platformId
)
VALUES(
	nevBE,
    arBE,
    leirasBE,
    kepBE,
    korhatarBE,
    akcioBE,
    mennyisegraktaronBE,
    eszkozIdBE,
    platformIdBE
)$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `eszkoz`
--

CREATE TABLE `eszkoz` (
  `id` int(9) NOT NULL,
  `nev` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `eszkoz`
--

INSERT INTO `eszkoz` (`id`, `nev`) VALUES
(7, 'Nintendo Switch'),
(1, 'PC'),
(5, 'PS4'),
(6, 'PS5'),
(2, 'XBOX One'),
(3, 'XBOX Series S'),
(4, 'XBOX Series X');

-- --------------------------------------------------------

--
-- Table structure for table `felhasznalo`
--

CREATE TABLE `felhasznalo` (
  `id` int(9) NOT NULL,
  `felhasznaloNev` varchar(100) NOT NULL,
  `vezetekNev` varchar(100) NOT NULL,
  `keresztNev` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `jelszo` text NOT NULL,
  `jogosultsagId` int(9) NOT NULL DEFAULT '1',
  `letrehozva` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `frissitve` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `felhasznalo`
--

INSERT INTO `felhasznalo` (`id`, `felhasznaloNev`, `vezetekNev`, `keresztNev`, `email`, `jelszo`, `jogosultsagId`, `letrehozva`, `frissitve`) VALUES
(1, 'SunnyDay21', 'Kéri', 'Bence', 'keribence0@gmail.com', '49e6586c9fbf5cdbfb820bde5c0f6800a21b549a', 2, '2023-11-20 17:13:02', NULL),
(2, 'AdventureSeeker', 'Nagy', 'Péter', 'peter.nagy@example.com', '9c073111b80b0af312f9c9f8bb4baa1c41e86d51', 1, '2023-11-20 17:13:35', NULL),
(3, 'MusicLover88', 'Tóth', 'Eszter', 'eszter.toth@example.com', '1e0acaff2cd87e52f18dbc6c9b6cad2b62483e49', 1, '2023-12-15 15:06:49', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `jatek`
--

CREATE TABLE `jatek` (
  `id` int(9) NOT NULL,
  `nev` varchar(100) NOT NULL,
  `ar` int(9) NOT NULL,
  `leiras` text NOT NULL,
  `kep` varchar(100) NOT NULL,
  `korhatar` int(2) NOT NULL,
  `akcio` int(3) NOT NULL DEFAULT '0',
  `mennyisegraktaron` int(5) NOT NULL,
  `eszkozId` int(9) NOT NULL,
  `platformId` int(9) NOT NULL,
  `letrehozva` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `frissitve` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `jatek`
--

INSERT INTO `jatek` (`id`, `nev`, `ar`, `leiras`, `kep`, `korhatar`, `akcio`, `mennyisegraktaron`, `eszkozId`, `platformId`, `letrehozva`, `frissitve`) VALUES
(1, 'The Witcher 3: Wild Hunt', 5990, 'A The Witcher 3: Wild Hunt egy fantasy szerepjáték, melyben Geralt of Rivia karakterét irányítva különböző küldetéseket teljesíthetsz egy nyitott világban.', 'witcher3.jpg', 18, 15, 100, 1, 1, '2023-11-11 11:57:24', NULL),
(2, 'Counter-Strike: Global Offensive', 1490, 'A Counter-Strike: Global Offensive egy taktikai lövöldözős játék, ahol két csapat, a terroristák és az antiterroristák egymás ellen küzdenek.', 'csgo.jpg', 16, 0, 300, 1, 1, '2023-11-11 12:02:56', NULL),
(3, 'Minecraft', 2990, 'A Minecraft egy sandbox játék, ahol a játékosok kockákból építhetnek és felfedezhetnek egy végtelen világot.', 'minecraft.jpg', 7, 0, 200, 7, 3, '2023-11-11 12:04:14', NULL),
(4, 'FC 24', 9990, 'A FC 24 egy futballszimulátor játék, ahol a játékosok vezethetik kedvenc csapatukat és részt vehetnek különböző versenyeken.', 'fc24.jpg', 7, 20, 150, 2, 2, '2023-11-11 12:04:14', NULL),
(5, 'Grand Theft Auto V', 6990, 'A Grand Theft Auto V egy akció-kaland játék, melyben a játékosok Los Santos városában szabadon közlekedhetnek és különböző küldetéseket teljesíthetnek.', 'gta5.jpg', 18, 0, 100, 1, 3, '2023-11-11 12:14:09', NULL),
(6, 'Red Dead Redemption 2: Ultimate Edition', 28990, 'Amerika, 1899. A vadnyugat hőskora lassan véget ér, ahogy a törvény emberei az utolsó bűnbandákat is levadásszák. Aki nem hajlandó megadni magát, halál fia. Arthur Morgan és a Van der Linde banda egy félresikerült rablási kísérlet miatt menekülni kényszerül Blackwater városából. Miközben szövetségi ügynökök és az ország legjobb fejvadászai lihegnek a nyomukban, a bandának nincs más választása, mint hogy a túlélés érdekében rabolva és fosztogatva átverekedjék magukat Amerika könyörtelen belvidékein. Amikor az egyre élesedő belső ellentétek a banda szétszakításával fenyegetnek, Arthur kénytelen eldönteni, hogy saját elveihez marad hű, vagy a bandához, amely felnevelte.', 'RDR2.jpg', 18, 0, 235, 1, 3, '2023-11-13 21:57:50', NULL),
(7, 'Alan Wake 2', 17900, 'Az Alan Wake 2 egy túlélőhorror játék, amit a Remedy Entertainment stúdió fejlesztett ki. Ez a 2010-ben megjelent Alan Wake folytatása.', 'AlanWake2.jpg', 18, 0, 50, 6, 1, '2023-11-13 22:06:04', NULL),
(8, 'Star Wars Battlefront 2', 13990, 'A Star Wars Battlefront II-ban lehetőséged nyílik átvenni az irányítást Iden Versio-n, a Birodalom jól ismert Inferno Squad-jának parancsnoka felett, hogy a mintegy 30 évtizedet felölelő kampány során megismerhess egy izgalmas sztorit a bosszúról, az árulásról és a megváltásról, méghozzá a mozifilmekhez kapcsolódó áthallásokkal és szereplőkkel a középpontban.', 'BF2.jpg', 16, 15, 188, 5, 1, '2023-11-13 22:09:22', NULL),
(9, 'Battlefield 2042', 19990, 'Az Amerikai Egyesült Államok és Oroszország a háború szélére sodródott. Miután a két nagyhatalom peremháborúi már évtizedek óta tartanak, számos tapasztalt veterán harcol a világban, akiknek elege van a fennálló rendszerből. Ezek a specialisták a saját, különleges harci képzettségüket arra használják, hogy alakítsák az emberiség jövőjét. Ebben a futurisztikus háborúban vehetsz részt te is a Battlefield 2042 harcosaként.', 'BF2042.png', 16, 0, 150, 2, 2, '2023-11-13 22:18:27', NULL),
(10, 'Tom Clancy\'s Rainbow Six Siege', 6660, 'A taktikai FPS-ek egyik legjobbjaként számon tartott Rainbow Six-sorozat legújabb epizódjában ismét megtapasztalhatod, hogy milyen egy speciális katonai egység tagjaként csapatban, parancsra engedelmeskedni, és egy előre meghatározott stratégia mentén együtt végrehajtani a feladatokat.', 'R6.jpg', 18, 0, 125, 1, 4, '2023-11-13 22:23:30', NULL),
(11, 'Assassin\'s Creed Odyssey', 6990, 'Írd meg saját odüsszeiádat, és válj legendás spártai hőssé az Assassin’s Creed sorozat legnagyobb és legszebb epizódjában! Több száz évvel az Assassin’s Creed Origins eseményei előtt, az ókori Görögországban háború dúl Spárta és a görögök között. Ez a te lehetőséged, hogy kitaszítottból igazi legendává válj! Ez az első eset, hogy a nyílt világú Assassin’s Creed sorozat valódi RPG-vé válik. Irányíthatod a beszélgetéseket, hatással lehetsz a küldetések alakulására és a történet végkimenetelére. Fejleszd saját férfi vagy női karakteredet, szerezz új fegyvereket, lopakodj, támadj, barangolj és hajózz a lenyűgöző világban. A legősibb kaland lesz a legnagyobb!', 'ACOdyssey.jpg', 18, 0, 150, 2, 4, '2023-12-01 18:12:39', NULL),
(12, 'Assassin\'s Creed Mirage', 14900, 'Éld át Basim, az Assassin’s Creed Valhallában megismert bérgyilkos izgalmas történetét az Assassin’s Creed Mirage játékban! Basim egy furfangos utcai tolvaj volt rémálomszerű víziókkal, aki válaszokat és igazságot keresett a 9. századi Bagdad utcáin. Miután találkozott a rejtélyes szervezettel, a Hidden Ones-szal, halálos bérgyilkos vált belőle, és oly módon változott meg a sorsa, ahogyan sosem gondolta volna. Térj vissza a legendás sorozat gyökereihez, és ismerd meg az egyik leghalálosabb bérgyilkos izgalmas történetét!', 'ACMirage.jpg', 18, 0, 220, 6, 4, '2023-12-01 18:19:26', NULL),
(13, 'The Sims 4', 2990, 'Átesett a generációváltáson a még mindig rendkívüli népszerűségnek örvendő The Sims-sorozat is, így a műfajában egyedinek tekinthető élet-szimulátor keretein belül ezúttal minden eddiginél nagyobb szabadság, felejthetetlen élmények, valamint a virtuális világ szépségei várnak rád. A The Sims 4-ben tehát lehetőséged lesz valóra váltani minden olyan álmodat, amire a való életben korántsem biztos, hogy képes lennél, mindezt pedig megerősíthetjük azzal, hogy a minden eddiginél komolyabb lehetőséget kínáló Create-A-Sim opcióban nem csak külsőleg, hanem belsőleg is megalkothatod virtuális másodat.', 'Sims4.jpg', 12, 0, 70, 5, 2, '2023-12-01 18:30:38', NULL),
(14, 'Battlefield V', 6990, 'Vegyél részt az emberiség történelmének legnagyobb háborújában, ahogy a híres lövöldözős játéksorozat visszatér a gyökereihez, és úgy ábrázolja a II. világháborút, mint még soha! Állítsd össze a csapatodat teljes egészében testreszabható katonákból, fegyverekből és járművekből – aztán vidd el őket egy epikus kalandra a Tides of War játékmódban! Éld át az eddigi legélethűbb Battlefield élményt, az egyjátékos történetekből felépülő War Stories kampánytól kezdve a klasszikus és új multiplayer játékmódokon át egészen a hatalmas Grand Operations módig. Élvezd, hogy nincs Premium Pass, mindenki megkapja az összes kiegészítőt. Fogd barátaidat, és vessétek bele magatokat az eddigi legnagyobb szabású Battlefield játékba!', 'BFV.jpg', 16, 0, 140, 5, 2, '2023-12-01 18:41:13', NULL),
(15, 'Call of Duty: Modern Warfare III', 27900, 'Egy évvel a minden rekordot megdöntő Call of Duty: Modern Warfare II után itt a közvetlen folytatás, a Call of Duty: Modern Warfare III. Captain Price és a Task Force 141 a legvégső fenyegetéssel néz szembe a Sledgehammer Games és az Infinity Ward nagyszabású produkciójában. Az ultranacionalista háborús bűnök, Vladimir Makarov visszatért, és folyamatosan növeli befolyását és hatalmát a világban, így a Task Force 141-nek keményebben kell harcolnia, mint valaha. Ez a végső konfliktus egy mindent elpusztító harmadik világháború peremére sodródott helyzetben!', 'CoDMW3.jpg', 18, 0, 390, 6, 1, '2023-12-01 18:51:27', NULL),
(16, 'Animal Crossing: New Horizons', 20900, 'A Nintendo legendás Animal Crossing sorozatának legújabb része a Switch konzol legmodernebb funkcióit és képességeit kihasználva egy minden korábbinál látványosabb és izgalmasabb kalandra hív. Ha a modern élet mókuskerekét és viszontagságait magad mögött szeretnéd hagyni, akkor irány a Nook Inc. lakatlan szigete, ahol új kihívások várnak! Irány a sziget, ahol egy új élet és új kalandok várnak a horizonton túl.', 'AnimalCrossing.jpg', 3, 0, 166, 7, 3, '2023-12-01 19:05:45', NULL),
(17, 'Super Smash Bros. Ultimate', 23900, 'Ez a harc lesz a végső! A Nintendo egyik legnagyobb sikersorozata, a Smash Bros. végre Switch-re is ellátogat, a valaha készült legszebb és legtartalmasabb epizóddal. A Super Smash Bros. Ultimate az összes szereplőt tartalmazza, aki valaha felbukkant a sorozat epizódjaiban, ez összesen 66 karaktert jelent! Mindenki itt van a Nintendo univerzumából, de a Capcom és más kiadók hősei is megjelennek, hogy borsot törjenek egymás orra alá. A számtalan figurához rengeteg pálya is dukál, melyek között új, és jelentősen felújított korábbi helyszínek is akadnak. A verekedős játék egyjátékos módokat is kínál, de a 4 fős multiplayert nem übereli semmi, ez a Smash Bros legvonzóbb játékmódja. Vesd bele magad te is a Nintendo univerzum legnagyobb össznépi bunyójába!', 'SSBU.jpg', 12, 20, 110, 7, 3, '2023-12-01 19:14:06', NULL),
(18, 'The Legend of Zelda: Tears of the Kingdom', 23000, 'A 2017-es, világsikert aratott Legend of Zelda: Breath of the Wild folytatása egy még szebb, még nagyobb kaland, amelyben a főhős, Link immár nemcsak Hyrule világát, hanem a felette elhelyezkedő égi világot is felfedezheti. Készülj fel életed egyik legnagyobb kalandjára, kizárólag Nintendo Switch-en!', 'TLoZToK.jpg', 12, 0, 99, 7, 3, '2023-12-01 19:19:27', NULL),
(19, 'Mortal Kombat 1', 17900, 'Fedezd fel az újjászületett Mortal Kombat Univerzumot, amelyet a Tűzisten, Liu Kang hozott létre, a Mortal Kombat 1 játékkal! Ez az ikonikus verekedős játéksorozat új korszakának kezdete, vadonatúj harcrendszerrel, új játékmódokkal és kivégzésekkel! Készülj fel egy új univerzumra, új hősökre és az új háborúra. A Földet továbbra is meg kell védeni! Új korszak kezdődik a Mortal Kombat 1 világában.', 'MK1.jpg', 18, 0, 150, 4, 1, '2023-12-02 17:18:02', NULL),
(20, 'Assassin\'s Creed Valhalla', 9000, 'Teremtsd meg a saját viking legendádat az Assassin’s Creed Valhalla gigászi kalandjában! Ez minden idők legnagyobb Assassin’s Creed játéka, amelyen nem kevesebb, mint 14 Ubisoft stúdió dolgozott! Bújj Eivor, a legendás viking hódító bőrébe, és vezesd klánodat a fagyos Norvégia partjaitól egészen Anglia buja tájaiig a 9. században. Fedezz fel egy gyönyörű és rejtélyes világot, ahol brutális ellenségek várnak, és lenyűgöző kastélyokat igázhatsz le. Építs saját települést, bővítsd új épületekkel és létesítményekkel. Szerezz szövetségeseket a világban, és írd be nevedet Valhalla történelmébe!', 'ACV.jpg', 18, 0, 56, 4, 4, '2023-12-02 17:25:28', NULL),
(21, 'Dying Light 2', 11900, 'Életben tudsz maradni egy zombik által ostromolt városban? Az apokalipszis kitörése után túlélők csoportjai próbálnak boldogulni a mindennapokban, és ehhez neked is segítened kell: járd be a hatalmas várost, teljesíts küldetéseket a különböző frakcióknak, és védd meg a közösséget a külső támadásokról. A Dying Light 2 szebb, és sokkal nagyobb, mint az első rész, új mozdulatokkal még könnyebben közlekedhetsz a városban. A játéktér dinamikus, a döntéseidtől függően átalakul a környezet, és a történet is új fordulatokat vehet!', 'DL2.jpg', 18, 0, 156, 4, 2, '2023-12-02 17:27:53', NULL),
(22, 'Star Wars: Jedi Survivor Deluxe Edition', 29990, 'Cal Kestis története folytatódik a Star Wars Jedi: Survivor játékban, amely egy külső nézetes, galaxison átívelő akció-kalandjáték a Respawn Entertainment és a Lucasfilm Games fejlesztésében. Ez a történetvezérelt, egyjátékos cím öt évvel a Star Wars Jedi: Fallen Order eseményei után kezdődik, és Cal egyre elkeseredettebb harcát követi, amint a galaxis egyre mélyebb sötétségbe süllyed. A Birodalom által a galaxis pereméig üldözött Cal ismerős és új fenyegetésekkel találja szembe magát. Az egyik utolsó Jedi túlélőként Cal kénytelen kiállni a szabadságért a galaxis legsötétebb óráiban – de milyen messzire képes elmenni azért, hogy megvédje magát, csapatát, és a Jedi rendet?', 'SWJS.jpg', 12, 10, 68, 3, 2, '2023-12-02 17:33:20', NULL),
(23, 'Forza Motorsport', 24990, 'Az Xbox legnagyobb autós sorozata, a Forza Motorsport visszatér egy vadonatúj, következő generációs epizóddal! Az új Forza Motorsport az Xbox Series X|S konzolok képességeihez készül, 4K felbontást, 60 fps képfrissítést és sugárkövetéses (raytracing) technológiát kínál, ezzel minden idők legélethűbb Xboxos autós játéka lesz. Előtérbe kerül az autóversenyzés: ülj be a legmodernebb versenygépek volánja mögé, vegyél részt nagyszabású bajnokságokban, hosszútávú versenyeken, ahol a minden korábbinál realisztikusabb fizika, a box stratégia és az időjárás is komoly szerepet játszik.', 'ForzaM.png', 3, 0, 231, 3, 1, '2023-12-02 17:39:18', NULL),
(24, 'The Elder Scrolls V Skyrim Special Edition', 7999, 'Újgenerációs köntösbe bújik az utóbbi évtized egyik legjobb szerepjátékaként számon tartott The Elder Scrolls V: Skyrim, amelynek PS4-es változata 1080p-s felbontással futtatja a csodaszép textúrákat, aminek köszönhetően páratlanul elragadó látványvilág társaságában élheted át a Skyrim földjére kalauzoló, sárkányvadászattal tarkított kalandokat.', 'ESVSkyrim.jpg', 18, 0, 143, 3, 4, '2023-12-02 17:44:44', NULL),
(25, 'Resident Evil 4', 22900, 'Éld át újra minden idők egyik legjobb túlélő-horror játékát, a Resident Evil 4-et, hihetetlen módon felújított grafikával és új játékmeneti elemekkel! A Capcom a korábbi Resident Evil játékokhoz hasonlóan a legendás negyedik epizódot is teljeskörűen felújította, hogy a meghatározó kaland egy új generáció számára is elérhető legyen.\r\n\r\nA Resident Evil 4-ben a túlélés csak a kezdet. A Raccoon City-ben történt biológiai katasztrófa óta hat év telt el. Leon S. Kennedy ügynök, az incidens egyik túlélője új küldetésre indul, hogy megmentse az elnök elrabolt lányát. A nyomok egy elhagyatott európai kisvárosba vezetnek, ahol valami komoly baj van a helyi lakókkal. Élet és halál, a terror és a katarzis rémületes érzése találkozik, ahogy felgördül a függöny, és kezdetét veszi egy emlékezetes történet.', 'RE4.jpg', 18, 10, 274, 6, 1, '2023-12-02 17:49:28', NULL),
(26, 'Resident Evil 3', 9999, 'A Capcom a Resident Evil 2 remake óriási sikere után a sorozat rajongóinak egyik kedvencét, a Resident Evil 3-at is felújított formában teszi újra elérhetővé! A klasszikus játék a Capcom saját RE Engine grafikus motorjával születik újjá, lenyűgözően élethű grafikával, új kameranézettel, és minden korábbinál félelmetesebb hangulattal.\r\n\r\nA Resident Evil 3-ban Jill Valentine az egyik utolsó túlélő Raccoon City-ben, aki egyúttal annak is tanúja, hogy mit tesz az Umbrella Corporation a zombi apokalipszis eltusolásának érdekében. A cég, hogy megfékezze Jill-t, elszabadítja a legszörnyűbb kísérletét: megérkezik Nemesis!', 'RE3.jpg', 18, 0, 197, 5, 4, '2023-12-02 17:57:07', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `kosar`
--

CREATE TABLE `kosar` (
  `id` int(9) NOT NULL,
  `felhasznaloId` int(9) NOT NULL,
  `jatekId` int(9) NOT NULL,
  `vegosszeg` int(9) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `platform`
--

CREATE TABLE `platform` (
  `id` int(9) NOT NULL,
  `nev` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `platform`
--

INSERT INTO `platform` (`id`, `nev`) VALUES
(3, 'Epic Games'),
(2, 'Origin'),
(1, 'Steam'),
(4, 'Ubisoft');

-- --------------------------------------------------------

--
-- Table structure for table `rendeles`
--

CREATE TABLE `rendeles` (
  `id` int(9) NOT NULL,
  `felhasznaloId` int(9) NOT NULL,
  `vegosszeg` int(9) NOT NULL,
  `feladva` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `termekkulcs`
--

CREATE TABLE `termekkulcs` (
  `id` int(9) NOT NULL,
  `kulcs` char(50) NOT NULL,
  `jatekId` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `termekkulcs`
--

INSERT INTO `termekkulcs` (`id`, `kulcs`, `jatekId`) VALUES
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

--
-- Indexes for dumped tables
--

--
-- Indexes for table `eszkoz`
--
ALTER TABLE `eszkoz`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Egyedi` (`nev`);

--
-- Indexes for table `felhasznalo`
--
ALTER TABLE `felhasznalo`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Egyedi` (`felhasznaloNev`,`email`),
  ADD KEY `jogosultsagId` (`jogosultsagId`);

--
-- Indexes for table `jatek`
--
ALTER TABLE `jatek`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Egyedi` (`nev`,`kep`),
  ADD KEY `kategoriaId` (`eszkozId`,`platformId`);

--
-- Indexes for table `kosar`
--
ALTER TABLE `kosar`
  ADD PRIMARY KEY (`id`),
  ADD KEY `felhasznaloId` (`felhasznaloId`,`jatekId`);

--
-- Indexes for table `platform`
--
ALTER TABLE `platform`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Egyedi` (`nev`);

--
-- Indexes for table `rendeles`
--
ALTER TABLE `rendeles`
  ADD PRIMARY KEY (`id`),
  ADD KEY `felhasznaloId` (`felhasznaloId`);

--
-- Indexes for table `termekkulcs`
--
ALTER TABLE `termekkulcs`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `kulcs` (`kulcs`),
  ADD UNIQUE KEY `jatekId` (`jatekId`),
  ADD KEY `ajandekKartyaId` (`jatekId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `eszkoz`
--
ALTER TABLE `eszkoz`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `felhasznalo`
--
ALTER TABLE `felhasznalo`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `jatek`
--
ALTER TABLE `jatek`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `kosar`
--
ALTER TABLE `kosar`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `platform`
--
ALTER TABLE `platform`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `rendeles`
--
ALTER TABLE `rendeles`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `termekkulcs`
--
ALTER TABLE `termekkulcs`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
