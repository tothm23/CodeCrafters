-- phpMyAdmin SQL Dump
-- version 5.1.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 11, 2023 at 04:01 PM
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `ajandekKartya` (IN `ajandekKartyaIdBE` INT(9))   SELECT * 
FROM ajandekkartya
WHERE ajandekkartya.id = ajandekKartyaIdBE$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ajandekKartyak` ()   SELECT *
FROM ajandekkartya$$

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

CREATE DEFINER=`root`@`localhost` PROCEDURE `felhasznaloKosar` (IN `felhasznaloIdBE` INT(9))   SELECT * 
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `frissitesFelhasznalo` (IN `felhasznaloNevBE` VARCHAR(100), IN `vezetekNevBE` VARCHAR(100), IN `keresztNev` VARCHAR(100), IN `szuletesiDatumBE` DATE, IN `emailBE` VARCHAR(100), IN `orszagBE` VARCHAR(100), IN `telefonBE` VARCHAR(14), IN `profilkepBE` VARCHAR(100), IN `jelszoBE` TEXT)   UPDATE felhasznalo
SET felhasznalo.felhasznaloNev = felhasznaloNevBE,
	felhasznalo.vezetekNev = vezetekNevBE,
    felhasznalo.keresztNev = keresztNev,
    felhasznalo.szuletesiDatum = szuletesiDatumBE,
    felhasznalo.email = emailBE,
    felhasznalo.orszag = orszagBE,
    felhasznalo.telefon = telefonBE,
    felhasznalo.profilkep = profilkepBE,
    felhasznalo.frissitve = CURRENT_TIMESTAMP
WHERE felhasznalo.jelszo = SHA1(jelszoBE)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `frissitesJatek` (IN `idBE` INT(9), IN `nevBE` VARCHAR(100), IN `arBE` INT(9), IN `leirasBE` TEXT, IN `kepBE` VARCHAR(100), IN `korhatarBE` INT(2), IN `akcioBE` INT(3), IN `mennyisegraktaronBE` INT(5), IN `kategoriaIdBE` INT(9), IN `eszkozIdBE` INT(9), IN `platformIdBE` INT(9))   UPDATE jatek
SET jatek.nev = nevBE,
	jatek.ar = arBE,
    jatek.leiras = leirasBE,
    jatek.kep = kepBE,
    jatek.korhatar = korhatarBE,
    jatek.akcio = akcioBE,
    jatek.mennyisegraktaron = mennyisegraktaronBE,
    jatek.kategoriaId = kategoriaIdBE,
    jatek.eszkozId = eszkozIdBE,
    jatek.platformId = platformIdBE,
    jatek.frissitve = CURRENT_TIMESTAMP
WHERE jatek.id = idBE$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `jatek` (IN `idBe` INT(9))   SELECT *
FROM jatek
WHERE jatek.id = idBe$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `jatekok` ()   SELECT *
FROM jatek$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `novekvoArAjandekKartya` ()   SELECT *
FROM ajandekkartya
ORDER BY ajandekkartya.ar ASC$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `novekvoArJatek` ()   SELECT *
FROM jatek
ORDER BY jatek.ar ASC$$

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

CREATE DEFINER=`root`@`localhost` PROCEDURE `ujFelhasznalo` (IN `felhasznaloNevBE` VARCHAR(100), IN `vezetekNevBE` VARCHAR(100), IN `keresztNev` VARCHAR(100), IN `szuletesiDatumBE` DATE, IN `emailBE` VARCHAR(100), IN `jelszoBE` TEXT, IN `orszagBE` VARCHAR(100), IN `telefon` VARCHAR(14))   INSERT INTO felhasznalo(
	felhasznalo.felhasznaloNev,
    felhasznalo.vezetekNev,
    felhasznalo.keresztNev,
    felhasznalo.szuletesiDatum,
    felhasznalo.email,
    felhasznalo.jelszo,
    felhasznalo.orszag,
    felhasznalo.telefon
)
VALUES(
	felhasznaloNevBE,
    vezetekNevBE,
    keresztNev,
    szuletesiDatumBE,
    emailBE,
    SHA1(jelszoBE),
    orszagBE,
    telefon
)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ujJatek` (IN `nevBE` VARCHAR(100), IN `arBE` INT(9), IN `leirasBE` TEXT, IN `kepBE` VARCHAR(100), IN `korhatarBE` INT(2), IN `akcioBE` INT(3), IN `mennyisegraktaronBE` INT(5), IN `kategoriaIdBE` INT(9), IN `eszkozIdBE` INT(9), IN `platformIdBE` INT(9))   INSERT INTO jatek(
	jatek.nev,
    jatek.ar,
    jatek.leiras,
    jatek.kep,
    jatek.korhatar,
    jatek.akcio,
    jatek.mennyisegraktaron,
    jatek.kategoriaId,
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
    kategoriaIdBE,
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
(5, 'FC 24 2800 FUT Points', 10000, 'A kártyán 2800 FUT Point-ot találsz, melyet kedved szerint elkölthetsz az FC 24 játékban.', '2800FUTpoints.jpg', 0, 0, 1, 2, '2023-11-11 13:02:51', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `cupon`
--

CREATE TABLE `cupon` (
  `id` int(9) NOT NULL,
  `cuponKod` varchar(14) NOT NULL,
  `ertek` int(2) NOT NULL,
  `ervenyessegIdo` datetime NOT NULL,
  `ervenyes` tinyint(1) NOT NULL DEFAULT '0',
  `aktivalva` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `eszkoz`
--

CREATE TABLE `eszkoz` (
  `id` int(9) NOT NULL,
  `eszkozNev` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `eszkoz`
--

INSERT INTO `eszkoz` (`id`, `eszkozNev`) VALUES
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
  `szuletesiDatum` date NOT NULL,
  `email` varchar(100) NOT NULL,
  `jelszo` text NOT NULL,
  `orszag` varchar(100) NOT NULL,
  `telefon` varchar(14) NOT NULL,
  `aktiv` tinyint(1) NOT NULL DEFAULT '0',
  `profilkep` varchar(100) DEFAULT NULL,
  `jogosultsagId` int(9) NOT NULL DEFAULT '1',
  `letrehozva` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `frissitve` datetime DEFAULT NULL,
  `torolve` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `felhasznalo`
--

INSERT INTO `felhasznalo` (`id`, `felhasznaloNev`, `vezetekNev`, `keresztNev`, `szuletesiDatum`, `email`, `jelszo`, `orszag`, `telefon`, `aktiv`, `profilkep`, `jogosultsagId`, `letrehozva`, `frissitve`, `torolve`) VALUES
(2, 'Probafriss', 'Ujnev1', 'Ujnev2', '2000-03-23', 'proba@gamil.com', 'a58c0c94fba109fa2e93458e184f3007bd2552a1', 'Németország', '+360620607210', 1, '', 1, '2023-09-18 11:28:10', '2023-09-25 12:41:04', '2023-09-18 16:54:16'),
(3, 'Proba2', 'ASD', 'DSA', '1987-04-07', 'kerib.113sz@acsjszki.hu', 'b6157765d4e408995d0b67b0956d3c0a3215a57e', 'Magyarország', '+3606204077373', 0, NULL, 1, '2023-09-25 12:33:02', NULL, NULL),
(4, 'Proba3', 'Jancsi', 'Béla', '1997-02-24', 'jani@gmail.com', 'f1ff673bf872ea25ce8fcd148fdfbe7129e5380a', 'Franciaország', '+36204032312', 0, NULL, 1, '2023-10-03 11:01:46', NULL, NULL);

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
(3, 'Minecraft', 2999, 'A Minecraft egy sandbox játék, ahol a játékosok kockákból építhetnek és felfedezhetnek egy végtelen világot.', 'minecraft.jpg', 6, 0, 200, 7, 3, '2023-11-11 12:04:14', NULL),
(4, 'FC 24', 9999, 'A FC 24 egy futballszimulátor játék, ahol a játékosok vezethetik kedvenc csapatukat és részt vehetnek különböző versenyeken.', 'fc24.jpg', 0, 20, 150, 2, 2, '2023-11-11 12:04:14', NULL),
(5, 'Grand Theft Auto V', 6999, 'A Grand Theft Auto V egy akció-kaland játék, melyben a játékosok Los Santos városában szabadon közlekedhetnek és különböző küldetéseket teljesíthetnek.', 'gta5.jpg', 18, 0, 100, 1, 3, '2023-11-11 12:14:09', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `jogosultsag`
--

CREATE TABLE `jogosultsag` (
  `id` int(9) NOT NULL,
  `jogosultsagNev` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `cuponId` int(9) NOT NULL,
  `vegosszeg` int(9) NOT NULL
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
(1, 'Steam'),
(2, 'Origin'),
(3, 'Epic Games'),
(4, 'Ubisoft Connect');

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
-- Indexes for table `cupon`
--
ALTER TABLE `cupon`
  ADD PRIMARY KEY (`id`);

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
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `cupon`
--
ALTER TABLE `cupon`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `eszkoz`
--
ALTER TABLE `eszkoz`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `felhasznalo`
--
ALTER TABLE `felhasznalo`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `jatek`
--
ALTER TABLE `jatek`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `jogosultsag`
--
ALTER TABLE `jogosultsag`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `kategoria`
--
ALTER TABLE `kategoria`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

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
-- AUTO_INCREMENT for table `termekkulcsok`
--
ALTER TABLE `termekkulcsok`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
