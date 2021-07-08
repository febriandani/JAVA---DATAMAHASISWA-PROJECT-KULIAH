-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 03, 2021 at 09:34 AM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 7.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `datamahasiswa1_2`
--

-- --------------------------------------------------------

--
-- Table structure for table `informasimahasiswa`
--

CREATE TABLE `informasimahasiswa` (
  `nim` varchar(50) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `tanggal_lahir` varchar(20) NOT NULL,
  `jurusan` varchar(30) NOT NULL,
  `angkatan` varchar(20) NOT NULL,
  `jenis_kelamin` varchar(20) NOT NULL,
  `email` varchar(40) NOT NULL,
  `ipk` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `informasimahasiswa`
--

INSERT INTO `informasimahasiswa` (`nim`, `nama`, `tanggal_lahir`, `jurusan`, `angkatan`, `jenis_kelamin`, `email`, `ipk`) VALUES
('191011400390', 'MUHAMMAD FEBRI ANDANI', '170300', 'TEKNIK INFORMATIKA', '2019', 'PRIA', 'febriandani@gmail.com', 245),
('191011400391', 'SYAHWA RAHMA ANDINI', '30600', 'TEKNIK INFORMATIKA', '2019', 'WANITA', 'syahwarhma@gmail.com', 183),
('191011400392', 'BAYU AKHMAD AMALUDIN', '260900', 'TEKNIK INFORMATIKA', '2019', 'PRIA', 'bayuakhmad@gmail.com', 0);

-- --------------------------------------------------------

--
-- Table structure for table `input_nilai`
--

CREATE TABLE `input_nilai` (
  `tugas` double NOT NULL,
  `uts` double NOT NULL,
  `uas` double NOT NULL,
  `ttl_nilai` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `khs`
--

CREATE TABLE `khs` (
  `id` int(10) NOT NULL,
  `nim` varchar(50) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `kode_mata_kuliah` varchar(50) NOT NULL,
  `nama_mata_kuliah` varchar(50) NOT NULL,
  `tugas` double NOT NULL,
  `uts` double NOT NULL,
  `uas` double NOT NULL,
  `ttl_nilai` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `khs`
--

INSERT INTO `khs` (`id`, `nim`, `nama`, `kode_mata_kuliah`, `nama_mata_kuliah`, `tugas`, `uts`, `uas`, `ttl_nilai`) VALUES
(1, '191011400390', 'MUHAMMAD FEBRI ANDANI', '04tpl01', 'pemrograman 1', 80, 95, 90, 265),
(3, '191011400390', 'MUHAMMAD FEBRI ANDANI', '04tpl02', 'Basis data', 85, 80, 80, 245),
(4, '191011400390', 'MUHAMMAD FEBRI ANDANI', '04tpl03', 'Bahasa inggris 4', 80, 75, 90, 245),
(5, '191011400391', 'SYAHWA RAHMA ANDINI', '04tpl01', 'pemrograman 1', 85, 80, 75, 240),
(6, '191011400391', 'SYAHWA RAHMA ANDINI', '04tpl03', 'Bahasa inggris 4', 95, 8, 80, 183);

-- --------------------------------------------------------

--
-- Table structure for table `krs`
--

CREATE TABLE `krs` (
  `kode_mata_kuliah` varchar(50) NOT NULL,
  `nama_mata_kuliah` varchar(50) NOT NULL,
  `sks` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `krs`
--

INSERT INTO `krs` (`kode_mata_kuliah`, `nama_mata_kuliah`, `sks`) VALUES
('04TPL01', 'pemrograman 1', 3),
('04TPL02', 'Basis data', 2),
('04TPL03', 'Bahasa inggris 4', 2),
('04TPL04', 'Statistik lanjut', 2),
('04TPL05', 'sistem operasi', 3);

-- --------------------------------------------------------

--
-- Table structure for table `loginadmin`
--

CREATE TABLE `loginadmin` (
  `nama` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `loginadmin`
--

INSERT INTO `loginadmin` (`nama`, `username`, `password`) VALUES
('Abdul aziz', 'abdula', 'admin01'),
('muhammad reza', 'reza12', 'admin02');

-- --------------------------------------------------------

--
-- Table structure for table `loginmhs`
--

CREATE TABLE `loginmhs` (
  `nama` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `loginmhs`
--

INSERT INTO `loginmhs` (`nama`, `username`, `password`) VALUES
('bayu akhmad amaludin', '191011400392', '170300'),
('muhammad febri andani', '191011400390', '170300'),
('muhammad rezza fadila', '191011400393', '170300'),
('syahwa rahma andini', '191011400391', '170300');

-- --------------------------------------------------------

--
-- Table structure for table `rankipk`
--

CREATE TABLE `rankipk` (
  `id` int(50) NOT NULL,
  `nim` varchar(50) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `jurusan` varchar(30) NOT NULL,
  `ipk` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `informasimahasiswa`
--
ALTER TABLE `informasimahasiswa`
  ADD PRIMARY KEY (`nim`);

--
-- Indexes for table `khs`
--
ALTER TABLE `khs`
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `krs`
--
ALTER TABLE `krs`
  ADD PRIMARY KEY (`kode_mata_kuliah`);

--
-- Indexes for table `loginadmin`
--
ALTER TABLE `loginadmin`
  ADD PRIMARY KEY (`nama`);

--
-- Indexes for table `loginmhs`
--
ALTER TABLE `loginmhs`
  ADD PRIMARY KEY (`nama`);

--
-- Indexes for table `rankipk`
--
ALTER TABLE `rankipk`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `khs`
--
ALTER TABLE `khs`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `rankipk`
--
ALTER TABLE `rankipk`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
