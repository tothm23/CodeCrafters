-- phpMyAdmin SQL Dump
-- version 5.1.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 20, 2023 at 01:40 PM
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `ajandekKartya` (IN `ajandekKartyaIdBE` INT(9))   SELECT 
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
ON ajandekkartya.platformId = platform.id

WHERE ajandekkartya.id = ajandekKartyaIdBE$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ajandekKartyak` ()   SELECT 
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
ON ajandekkartya.platformId = platform.id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `carouselRandomKepek` ()   SELECT jatek.kep
FROM jatek
ORDER BY RAND()
LIMIT 3$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `csokkenoArAjandekKartya` ()   SELECT *
FROM ajandekkartya
ORDER BY ajandekkartya.ar DESC$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `csokkenoArJatek` ()   SELECT *
FROM jatek
ORDER BY jatek.ar DESC$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `felhasznalo` (IN `idBE` INT(9))   SELECT *
FROM felhasznalo
WHERE felhasznalo.id = idBE$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `felhasznaloBelepes` (IN `felhasznaloNevBE` VARCHAR(100), IN `jelszoBE` TEXT, OUT `felhasznaloIdKI` INT(9))   SELECT felhasznalo.id
FROM felhasznalo
WHERE felhasznalo.felhasznaloNev = felhasznaloNevBE AND felhasznalo.jelszo = SHA1(jelszoBE)
INTO felhasznaloIdKI$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `felhasznalok` ()   SELECT *
FROM felhasznalo$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `felhasznaloKosar` (IN `felhasznaloIdBE` INT(9))   SELECT 
	kosar.id,
	kosar.felhasznaloId,
    kosar.jatekId,
    kosar.ajandekKartyaId,
    kosar.vegosszeg
FROM kosar
LEFT JOIN felhasznalo
ON kosar.felhasznaloId = felhasznalo.id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `frissitesAjandekKartya` (IN `idBE` INT(9), IN `nevBE` VARCHAR(100), IN `arBE` INT(9), IN `leirasBE` TEXT, IN `kepBE` VARCHAR(100), IN `akcioBE` INT(3), IN `mennyisegraktaronBE` INT(5), IN `eszkozIdBE` INT(9), IN `platformIdBE` INT(9))   UPDATE ajandekkartya
SET ajandekkartya.nev = nevBE,
	ajandekkartya.ar = arBE,
    ajandekkartya.leiras = leirasBE,
    ajandekkartya.kep = kepBE,
    ajandekkartya.akcio = akcioBE,
    ajandekkartya.mennyisegraktaron = mennyisegraktaronBE,
    ajandekkartya.eszkozId = eszkozIdBE,
    ajandekkartya.platformId = platformIdBE,
    ajandekkartya.frissitve = CURRENT_TIMESTAMP
WHERE ajandekkartya.id = idBE$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `frissitesFelhasznalo` (IN `felhasznaloNevBE` VARCHAR(100), IN `vezetekNevBE` VARCHAR(100), IN `keresztNev` VARCHAR(100), IN `emailBE` VARCHAR(100), IN `jelszoBE` TEXT)   UPDATE felhasznalo
SET felhasznalo.felhasznaloNev = felhasznaloNevBE,
	felhasznalo.vezetekNev = vezetekNevBE,
    felhasznalo.keresztNev = keresztNev,
    felhasznalo.email = emailBE,
    felhasznalo.frissitve = CURRENT_TIMESTAMP
WHERE felhasznalo.jelszo = SHA1(jelszoBE)$$

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

CREATE DEFINER=`root`@`localhost` PROCEDURE `kosar` (IN `jatekIdBE` INT(9), IN `ajandekKartyaIdBE` INT(9), IN `kosárIdBE` INT(9), IN `felhasznaloIdBE` INT(9))   BEGIN

DECLARE jatekAr INT(9);
DECLARE jatekAkcio INT(9);
DECLARE ajandekKartyaAr INT(9);
DECLARE ajandekKartyaAkcio INT(9);
DECLARE vegosszeg INT(9);

SELECT jatek.ar INTO jatekAr
FROM jatek
WHERE jatek.id = jatekIdBE;

SELECT jatek.akcio INTO jatekAkcio
FROM jatek
WHERE jatek.id = jatekIdBE;

SELECT ajandekkartya.ar INTO ajandekKartyaAr
FROM ajandekkartya
WHERE ajandekkartya.id = ajandekKartyaIdBE;

SELECT ajandekkartya.akcio INTO ajandekKartyaAkcio
FROM ajandekkartya
WHERE ajandekkartya.id = ajandekKartyaIdBE;

UPDATE kosar 
SET kosar.vegosszeg = jatekAr - (jatekAr * jatekAkcio / 100) + ajandekKartyaAr - (ajandekKartyaAr * ajandekKartyaAkcio / 100)
WHERE kosar.id = kosárIdBE;

INSERT INTO kosar(
    kosar.felhasznaloId,
    kosar.jatekId,
    kosar.ajandekKartyaId
)
VALUES(
    felhasznaloIdBE,
	jatekIdBE,
    ajandekKartyaIdBE
);

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `novekvoArAjandekKartya` ()   SELECT *
FROM ajandekkartya
ORDER BY ajandekkartya.ar ASC$$

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

CREATE DEFINER=`root`@`localhost` PROCEDURE `torlesAjandekKartya` (IN `idBE` INT(9))   DELETE 
FROM ajandekkartya
WHERE ajandekkartya.id = idBE$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `torlesFelhasznalo` (IN `felhasznaloNevBE` VARCHAR(100), IN `jelszoBE` TEXT)   UPDATE felhasznalo
SET felhasznalo.aktiv = 1,
	felhasznalo.torolve = CURRENT_TIMESTAMP
WHERE felhasznalo.felhasznaloNev = felhasznaloNevBE AND felhasznalo.jelszo = SHA1(jelszoBE)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `torlesJatek` (IN `idBE` INT(9))   DELETE
FROM jatek
WHERE jatek.id = idBE$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ujAjandekKartya` (IN `nevBE` VARCHAR(100), IN `arBE` INT(9), IN `leirasBE` TEXT, IN `kepBE` VARCHAR(100), IN `akcioBE` INT(3), IN `mennyisegraktaronBE` INT(5), IN `eszkozIdBE` INT(9), IN `platformIdBE` INT(9))   INSERT INTO ajandekkartya(
	ajandekkartya.nev,
    ajandekkartya.ar,
    ajandekkartya.leiras,
    ajandekkartya.kep,
    ajandekkartya.akcio,
    ajandekkartya.mennyisegraktaron,
    ajandekkartya.eszkozId,
    ajandekkartya.platformId
)
VALUES(
	nevBE,
    arBE,
    leirasBE,
    kepBE,
    akcioBE,
    mennyisegraktaronBE,
    eszkozIdBE,
    platformIdBE
)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ujFelhasznalo` (IN `felhasznaloNevBE` VARCHAR(100), IN `vezetekNevBE` VARCHAR(100), IN `keresztNev` VARCHAR(100), IN `emailBE` VARCHAR(100), IN `jelszoBE` TEXT, IN `tokenBE` TEXT)   INSERT INTO felhasznalo(
	felhasznalo.felhasznaloNev,
    felhasznalo.vezetekNev,
    felhasznalo.keresztNev,
    felhasznalo.email,
    felhasznalo.jelszo,
    felhasznalo.token
)
VALUES(
	felhasznaloNevBE,
    vezetekNevBE,
    keresztNev,
    emailBE,
    SHA1(jelszoBE),
    tokenBE
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
-- Table structure for table `ajandekkartya`
--

CREATE TABLE `ajandekkartya` (
  `id` int(9) NOT NULL,
  `nev` varchar(100) NOT NULL,
  `ar` int(9) NOT NULL,
  `leiras` text NOT NULL,
  `kep` varchar(100) NOT NULL,
  `akcio` int(3) NOT NULL DEFAULT '0',
  `mennyisegraktaron` int(5) NOT NULL,
  `eszkozId` int(9) NOT NULL,
  `platformId` int(9) NOT NULL,
  `letrehozva` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `frissitve` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ajandekkartya`
--

INSERT INTO `ajandekkartya` (`id`, `nev`, `ar`, `leiras`, `kep`, `akcio`, `mennyisegraktaron`, `eszkozId`, `platformId`, `letrehozva`, `frissitve`) VALUES
(1, 'PlayStation Store Ajándékkártya 7500 Ft', 7500, 'A kártyát felhasználva játékok, filmek, interaktív könyvek, játék kiegészítések és számos más érdekes letölthető tartalom várja, hogy kedvenc Sony konzolodra (legyen az PS4, PS5) beszerezd őket! Témák, háttérképek, kosztümök - válogass kedvedre, úgy, hogy még a kanapédból sem állsz fel. A virtuális vásárlási utalvány ajándéknak is ideális!', '7500PScard.jpg', 0, 500, 5, 1, '2023-11-11 12:52:17', NULL),
(2, 'PlayStation Store Ajándékkártya 30000 Ft', 30000, 'A kártyát felhasználva játékok, filmek, interaktív könyvek, játék kiegészítések és számos más érdekes letölthető tartalom várja, hogy kedvenc Sony konzolodra (legyen az PS4, PS5) beszerezd őket! Témák, háttérképek, kosztümök - válogass kedvedre, úgy, hogy még a kanapédból sem állsz fel. A virtuális vásárlási utalvány ajándéknak is ideális!', '30000PScard.jpg', 15, 200, 5, 1, '2023-09-18 17:44:16', '2023-09-18 17:44:56'),
(3, 'Grand Theft Auto Online: Great White Shark Card', 5000, 'A Great White Shark kártyán 1 250 000 virtuális GTA dollárt találsz, melyet kedved szerint elkölthetsz a Grand Theft Auto Online játékban.', 'white-shark-card.jpg', 0, 100, 1, 1, '2023-09-25 12:34:39', '2023-09-25 12:42:04'),
(4, 'Grand Theft Auto Online: Whale Shark Card', 12000, 'A Whale Shark kártyán 3 500 000 virtuális GTA dollárt találsz, melyet kedved szerint elkölthetsz a Grand Theft Auto Online játékban.', 'whale-shark-card.jpg', 10, 150, 1, 1, '2023-11-11 12:59:53', NULL),
(5, 'FC 24 2800 FUT Points', 10000, 'A kártyán 2800 FUT Point-ot találsz, melyet kedved szerint elkölthetsz az FC 24 játékban.', '2800FUTpoints.jpg', 0, 0, 1, 2, '2023-11-11 13:02:51', NULL),
(6, '5990 forintos XBOX ajándékkártya digitális kód', 5990, 'Használja Xbox ajándékkártyáit játékok és alkalmazások vásárlásakor Xbox konzolhoz. Egy ajándékkártya felhasználásához először váltsa be azt saját , vagy böngéssze a Játékáruházat Xbox konzolján. Az ajándékkártya bármeddig felhasználható, még a beváltást követően is.', '5990XBOXcard.webp', 0, 66, 2, 4, '2023-11-13 21:35:14', NULL),
(7, '12990 forintos XBOX ajándékkártya digitális kód', 12990, 'Használja Xbox ajándékkártyáit játékok és alkalmazások vásárlásakor Xbox konzolhoz. Egy ajándékkártya felhasználásához először váltsa be azt saját , vagy böngéssze a Játékáruházat Xbox konzolján. Az ajándékkártya bármeddig felhasználható, még a beváltást követően is. ', '12990XBOXcard.jpg', 10, 175, 2, 4, '2023-11-13 21:41:57', NULL),
(8, 'FC 24 5900 FC Points', 19990, 'A kártyán 5900 FUT Point-ot találsz, melyet kedved szerint elkölthetsz az FC 24 játékban.', '5900FUTpoints.webp', 0, 210, 1, 2, '2023-11-13 21:47:38', NULL),
(9, 'Battlefield 2042 Coins - 1100 BFC', 4490, 'Vásárolj 1100 BFC-t! Ez a játékbeli valuta új kozmetikai elemek vásárlására használható fegyverekhez, járművekhez és a játékban elérhető Battle Pass elemeihez.\r\n\r\n\r\n\r\n\r\n', '1100BFC.jpg', 0, 125, 2, 4, '2023-11-13 22:38:12', NULL),
(10, 'Steam 50€ ajándékkártya', 18890, 'A Steam ajándékkártya egy digitális ajándékkártya, amelyet csak a Steamen lehet felhasználni, hogy meghatározott összeget adjon hozzá a Steam Wallet fiókodhoz. A 30 eurós Steam kártya 30 eurót utal át közvetlenül a Steam pénztárcádba. Amint a pénz megérkezett a fiókodba, vásárolhatsz a Steam áruházban.', 'Steam50card.jpeg', 0, 350, 1, 1, '2023-11-13 22:50:31', NULL);

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
(1, 'PC'),
(2, 'XBOX One'),
(3, 'XBOX Series S'),
(4, 'XBOX Series X'),
(5, 'PS4'),
(6, 'PS5'),
(7, 'Nintendo Switch');

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
  `token` text NOT NULL,
  `letrehozva` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `frissitve` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `felhasznalo`
--

INSERT INTO `felhasznalo` (`id`, `felhasznaloNev`, `vezetekNev`, `keresztNev`, `email`, `jelszo`, `jogosultsagId`, `token`, `letrehozva`, `frissitve`) VALUES
(2, 'Probafriss', 'Ujnev1', 'Ujnev2', 'proba@gamil.com', 'a58c0c94fba109fa2e93458e184f3007bd2552a1', 1, '', '2023-09-18 11:28:10', '2023-09-25 12:41:04'),
(3, 'Proba2', 'ASD', 'DSA', 'kerib.113sz@acsjszki.hu', 'b6157765d4e408995d0b67b0956d3c0a3215a57e', 2, '', '2023-09-25 12:33:02', NULL),
(4, 'Proba3', 'Jancsi', 'Béla', 'jani@gmail.com', 'f1ff673bf872ea25ce8fcd148fdfbe7129e5380a', 1, '', '2023-10-03 11:01:46', NULL),
(5, 'FelHasznToken', 'Fel', 'Haszn', 'felhaszn@gmail.com', '21ea74394033249e614ee141e7dd5205a0b6a9eb', 1, '123abc', '2023-11-20 14:36:32', NULL);

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
(1, 'The Witcher 3: Wild Hunt', 5999, 'A The Witcher 3: Wild Hunt egy fantasy szerepjáték, melyben Geralt of Rivia karakterét irányítva különböző küldetéseket teljesíthetsz egy nyitott világban.', 'witcher3.jpg', 18, 15, 100, 1, 1, '2023-11-11 11:57:24', NULL),
(2, 'Counter-Strike: Global Offensive', 1499, 'A Counter-Strike: Global Offensive egy taktikai lövöldözős játék, ahol két csapat, a terroristák és az antiterroristák egymás ellen küzdenek.', 'csgo.jpg', 16, 0, 300, 1, 1, '2023-11-11 12:02:56', NULL),
(3, 'Minecraft', 2999, 'A Minecraft egy sandbox játék, ahol a játékosok kockákból építhetnek és felfedezhetnek egy végtelen világot.', 'minecraft.jpg', 7, 0, 200, 7, 3, '2023-11-11 12:04:14', NULL),
(4, 'FC 24', 9999, 'A FC 24 egy futballszimulátor játék, ahol a játékosok vezethetik kedvenc csapatukat és részt vehetnek különböző versenyeken.', 'fc24.jpg', 7, 20, 150, 2, 2, '2023-11-11 12:04:14', NULL),
(5, 'Grand Theft Auto V', 6999, 'A Grand Theft Auto V egy akció-kaland játék, melyben a játékosok Los Santos városában szabadon közlekedhetnek és különböző küldetéseket teljesíthetnek.', 'gta5.jpg', 18, 0, 100, 1, 3, '2023-11-11 12:14:09', NULL),
(6, 'Red Dead Redemption 2: Ultimate Edition', 28999, 'Amerika, 1899. A vadnyugat hőskora lassan véget ér, ahogy a törvény emberei az utolsó bűnbandákat is levadásszák. Aki nem hajlandó megadni magát, halál fia. Arthur Morgan és a Van der Linde banda egy félresikerült rablási kísérlet miatt menekülni kényszerül Blackwater városából. Miközben szövetségi ügynökök és az ország legjobb fejvadászai lihegnek a nyomukban, a bandának nincs más választása, mint hogy a túlélés érdekében rabolva és fosztogatva átverekedjék magukat Amerika könyörtelen belvidékein. Amikor az egyre élesedő belső ellentétek a banda szétszakításával fenyegetnek, Arthur kénytelen eldönteni, hogy saját elveihez marad hű, vagy a bandához, amely felnevelte.', 'RDR2.jfif', 18, 0, 235, 1, 3, '2023-11-13 21:57:50', NULL),
(7, 'Alan Wake 2', 17900, 'Az Alan Wake 2 egy túlélőhorror játék, amit a Remedy Entertainment stúdió fejlesztett ki. Ez a 2010-ben megjelent Alan Wake folytatása.', 'AlanWake2.webp', 18, 0, 50, 6, 1, '2023-11-13 22:06:04', NULL),
(8, 'Star Wars Battlefront 2', 13990, 'A Star Wars Battlefront II-ben lehetőségünk nyílik átvenni az irányítást Iden Versio, a Birodalom jól ismert Inferno Squad-jának parancsnoka felett, hogy a mintegy 30 évtizedet felölelő kampány során megismerhessünk egy izgalmas sztorit a bosszúról, az árulásról és a megváltásról, méghozzá a mozifilmekhez kapcsolódó áthallásokkal és szereplőkkel a középpontban.', 'BF2.webp', 16, 15, 188, 5, 1, '2023-11-13 22:09:22', NULL),
(9, 'Battlefield 2042', 19990, 'Az Amerikai Egyesült Államok és Oroszország a háború szélére sodródott. Miután a két nagyhatalom peremháborúi már évtizedek óta tartanak, számos tapasztalt veterán harcol a világban, akiknek elege van a fennálló rendszerből. Ezek a specialisták a saját, különleges harci képzettségüket arra használják, hogy alakítsák az emberiség jövőjét. Ebben a futurisztikus háborúban vehetsz részt te is a Battlefield 2042 harcosaként.', 'BF2042.png', 16, 0, 150, 2, 2, '2023-11-13 22:18:27', NULL),
(10, 'Tom Clancy\'s Rainbow Six Siege', 6660, 'A taktikai FPS-ek egyik legjobbjaként számon tartott Rainbow Six-sorozat legújabb epizódjában ismét megtapasztalhatjuk, hogy milyen egy speciális katonai egység tagjaként csapatban, parancsra engedelmeskedni, és egy előre meghatározott stratégia mentén együtt végrehajtani a feladatokat.', 'R6.jpg', 18, 0, 125, 1, 4, '2023-11-13 22:23:30', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `jogosultsag`
--

CREATE TABLE `jogosultsag` (
  `id` int(9) NOT NULL,
  `jogosultsagNev` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `jogosultsag`
--

INSERT INTO `jogosultsag` (`id`, `jogosultsagNev`) VALUES
(1, 'user'),
(2, 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `kategoria`
--

CREATE TABLE `kategoria` (
  `id` int(9) NOT NULL,
  `nev` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kategoria`
--

INSERT INTO `kategoria` (`id`, `nev`) VALUES
(1, 'Játék'),
(2, 'Ajándék Kártya');

-- --------------------------------------------------------

--
-- Table structure for table `kosar`
--

CREATE TABLE `kosar` (
  `id` int(9) NOT NULL,
  `felhasznaloId` int(9) NOT NULL,
  `jatekId` int(9) NOT NULL,
  `ajandekKartyaId` int(9) NOT NULL,
  `vegosszeg` int(9) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kosar`
--

INSERT INTO `kosar` (`id`, `felhasznaloId`, `jatekId`, `ajandekKartyaId`, `vegosszeg`) VALUES
(1, 2, 3, 7, 14690),
(3, 2, 3, 7, 17999),
(4, 2, 3, 7, 0),
(5, 2, 4, 5, 0);

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
(1, 'Steam'),
(2, 'Origin'),
(3, 'Epic Games'),
(4, 'Ubisoft');

-- --------------------------------------------------------

--
-- Table structure for table `rendeles`
--

CREATE TABLE `rendeles` (
  `id` int(9) NOT NULL,
  `felhasznaloId` int(9) NOT NULL,
  `feladva` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `kosarId` int(9) NOT NULL,
  `kiszallitva` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `termekkulcsok`
--

CREATE TABLE `termekkulcsok` (
  `id` int(9) NOT NULL,
  `termekKulcs` char(50) NOT NULL,
  `ajandekKartyaId` int(9) NOT NULL,
  `jatekId` int(9) NOT NULL,
  `eladva` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ajandekkartya`
--
ALTER TABLE `ajandekkartya`
  ADD PRIMARY KEY (`id`),
  ADD KEY `platformId` (`platformId`),
  ADD KEY `eszkozId` (`eszkozId`);

--
-- Indexes for table `eszkoz`
--
ALTER TABLE `eszkoz`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `felhasznalo`
--
ALTER TABLE `felhasznalo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `jogosultsagId` (`jogosultsagId`);

--
-- Indexes for table `jatek`
--
ALTER TABLE `jatek`
  ADD PRIMARY KEY (`id`),
  ADD KEY `kategoriaId` (`eszkozId`,`platformId`);

--
-- Indexes for table `jogosultsag`
--
ALTER TABLE `jogosultsag`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `kategoria`
--
ALTER TABLE `kategoria`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `kosar`
--
ALTER TABLE `kosar`
  ADD PRIMARY KEY (`id`),
  ADD KEY `felhasznaloId` (`felhasznaloId`,`jatekId`,`ajandekKartyaId`);

--
-- Indexes for table `platform`
--
ALTER TABLE `platform`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `rendeles`
--
ALTER TABLE `rendeles`
  ADD PRIMARY KEY (`id`),
  ADD KEY `felhasznaloId` (`felhasznaloId`,`kosarId`);

--
-- Indexes for table `termekkulcsok`
--
ALTER TABLE `termekkulcsok`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ajandekKartyaId` (`ajandekKartyaId`,`jatekId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ajandekkartya`
--
ALTER TABLE `ajandekkartya`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `eszkoz`
--
ALTER TABLE `eszkoz`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `felhasznalo`
--
ALTER TABLE `felhasznalo`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `jatek`
--
ALTER TABLE `jatek`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `jogosultsag`
--
ALTER TABLE `jogosultsag`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `kategoria`
--
ALTER TABLE `kategoria`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `kosar`
--
ALTER TABLE `kosar`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

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
-- AUTO_INCREMENT for table `termekkulcsok`
--
ALTER TABLE `termekkulcsok`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
