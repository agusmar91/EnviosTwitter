-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-02-2018 a las 14:33:08
-- Versión del servidor: 10.1.29-MariaDB
-- Versión de PHP: 7.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `javaweb_crud`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulos`
--

CREATE TABLE `articulos` (
  `id` int(10) NOT NULL,
  `origen` varchar(50) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `destino` varchar(50) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `paquete` varchar(50) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `fecha` timestamp NULL DEFAULT NULL,
  `remitente` varchar(50) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `transportista` varchar(50) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `precio` double(6,2) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `articulos`
--

INSERT INTO `articulos` (`id`, `origen`, `destino`, `paquete`, `fecha`, `remitente`, `transportista`, `precio`) VALUES
(100, 'Madrid', 'Barcelona', 'grande', '2018-02-13 23:00:00', 'Juan', 'Pedro', 150.00),
(101, 'ghgf', 'hgfhgf', 'ghgfh', '1999-10-09 22:00:00', 'ghgf', 'gfhgf', 4.00),
(102, 'ghfgt', 'gfjgf', 'jghj', '1999-10-09 22:00:00', 'jhgjhg', 'hjhg', 47.00),
(103, 'hukuk', 'hgkhgk', 'khghjk', '1999-10-09 22:00:00', 'hgjgh', 'hgjg', 4.00),
(104, 'barcelona', 'idsblks', 'lkjfdasbkljf', '1999-10-09 22:00:00', 'sdlkjnvflkj', 'ikjsabfñl', 50.00),
(105, 'Sevilla', 'jksnfk', 'ijvgbos', '1999-10-09 22:00:00', 'asfhbvguhj', 'zbihojdsb', 60.00),
(106, 'Madrid, España', 'Barcelona, España', 'pequeño', '1999-10-09 22:00:00', 'tu', 'yo', 50.00),
(107, 'Madrid, España', 'Barcelona, España', 'grande', '1999-10-09 22:00:00', 'tu', 'yo', 50.00),
(108, 'Sevilla, España', 'Valencia, España', 'pequeño', '1999-10-09 22:00:00', 'Dos', 'Uno', 60.00);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `articulos`
--
ALTER TABLE `articulos`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `articulos`
--
ALTER TABLE `articulos`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=109;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
