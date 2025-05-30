-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Aug 10, 2020 at 04:57 PM
-- Server version: 5.7.25-28-log
-- PHP Version: 7.3.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ApiTestingDB`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(256) NOT NULL,
  `description` text NOT NULL,
  `created` datetime NOT NULL,
  `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`, `description`, `created`, `modified`) VALUES
(1, 'Active Wear - Men', 'H+ ACTIVE Apparel-Men', '2014-06-01 00:35:07', '2014-05-30 17:34:33'),
(2, 'Active Wear - Women', 'H+ ACTIVE Apparel-Women', '2014-06-01 00:35:07', '2014-05-30 17:34:33'),
(3, 'Active Wear - Unisex', 'H+ ACTIVE Apparel-Unisex', '2014-06-01 00:35:07', '2014-05-30 17:34:33'),
(4, 'Supplements', 'H+ POWER Supplements', '0000-00-00 00:00:00', '2016-01-08 13:27:26'),
(19, 'test x name A', 'test x description A', '2020-07-29 07:34:15', '2020-07-26 14:34:35');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `name` varchar(32) NOT NULL,
  `description` text NOT NULL,
  `price` decimal(65,2) NOT NULL,
  `category_id` int(11) NOT NULL,
  `created` datetime NOT NULL,
  `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `name`, `description`, `price`, `category_id`, `created`, `modified`) VALUES
(1, 'Bamboo Thermal Ski Coat', 'You’ll be the most environmentally conscious skier on the slopes – and the most stylish – wearing our fitted bamboo thermal ski coat, made from organic bamboo with recycled plastic down filling.', '99.00', 2, '2020-07-25 07:48:26', '2020-07-25 14:55:42'),
(2, 'Cross-Back Training Tank', 'The most awesome phone of 2013!', '299.00', 2, '2020-07-25 07:48:27', '2020-07-25 14:55:42'),
(3, 'Grunge Skater Jeans', 'Our boy-cut jeans are for men and women who appreciate that skate park fashions aren’t just for skaters. Made from the softest and most flexible organic cotton denim.', '68.00', 3, '2020-07-25 07:48:28', '2020-07-25 14:55:42'),
(4, 'Slicker Jacket', 'Wind and rain are no match for our organic bamboo slicker jacket for men and women. Triple stitched seams, zippered pockets, and a stay-tight hood are just a few features of our best-selling jacket.', '125.00', 3, '2020-07-25 07:48:29', '2020-07-25 14:55:42'),
(5, 'Stretchy Dance Pants', 'Whether dancing the samba, mastering a yoga pose, or scaling the climbing wall, our stretchy dance pants, made from 80% organic cotton and 20% Lycra, are the most versatile and comfortable workout pants you’ll ever have the pleasure of wearing.', '55.00', 2, '2020-07-25 07:48:30', '2020-07-25 14:55:42'),
(6, 'UItra-Soft Tank Top', 'This versatile tank can be worn in the gym, under a blazer, or for a day out in the sun. Made from our patented organic bamboo and cotton mix, our ultra-soft tank never stops feeling soft, even when you forget the fabric softener.', '22.00', 2, '2020-07-25 07:48:31', '2020-07-25 14:55:42'),
(7, 'Unisex Thermal Vest', 'Our thermal vest, made from organic bamboo with recycled plastic down filling, is a favorite of both men and women. You’ll help the environment, and have a wear-easy piece for many occasions.', '95.00', 3, '2020-07-25 07:48:32', '2020-07-25 14:55:42'),
(8, 'V-Neck T-Shirt', 'Our pre-shrunk organic cotton t-shirt, with its slightly fitted waist and elegant V-neck is designed to flatter. You’ll want one in every color!', '17.00', 3, '2020-07-25 07:48:33', '2020-07-25 14:55:42'),
(9, 'Polo Shirt', 'Our pre-shrunk organic cotton polo shirt is perfect for weekend activities, lounging around the house, and casual days at the office. With its triple-stitched sleeves and waistband, our polo has maximum durability.', '35.00', 1, '2020-07-25 07:48:34', '2020-07-25 14:55:42'),
(10, 'Skater Graphic T-Shirt', 'Hip at the skate park or around down, our pre-shrunk organic cotton graphic T-shirt has you covered.', '33.00', 1, '2020-07-25 07:48:35', '2020-07-25 14:55:42'),
(11, 'Thermal Fleece Jacket', 'Cool red shirt!aOur thermal organic fleece jacket, is brushed on both sides for ultra softness and warmth. This medium-weight jacket is versatile all year around, and can be worn with layers for the winter season.', '60.00', 1, '2020-07-25 07:48:36', '2020-07-25 14:55:42'),
(12, 'V-Neck Pullover', 'This organic hemp jersey pullover is perfect in a pinch. Wear for casual days at the office, a game of hoops after work, or running your weekend errands.', '65.00', 1, '2020-07-25 07:48:37', '2020-07-25 14:55:42'),
(13, 'V-Neck Sweater', 'This medium-weight sweater, made from organic knitted cotton and bamboo, is the perfect solution to a chilly night at the campground or a misty walk on the beach.', '65.00', 1, '2020-07-25 07:48:38', '2020-07-25 14:55:42'),
(14, 'Calcium 400 IU (150 tablets)', 'Our Calcium supports strong bones and teeth and may help prevent osteoporosis.', '10.00', 4, '2020-07-25 07:48:39', '2020-07-25 14:55:42'),
(15, 'Flaxseed Oil 1000 mg (90 capsule', 'Our Flaxseed Oil contains Omegas 3, 6, and 9 for a heart-healthy option without the use of fish oil.', '12.00', 4, '2020-07-25 07:48:40', '2020-07-25 14:55:42'),
(16, 'Iron 65 mg (150 caplets)', 'Iron: Re+vive, re+fuel, re+generate, naturally, with H+SPORT Iron. Our supplements are sourced from the purest ingredients—and potency is proven in independent laboratory tests. Iron is essential for transporting oxygen in the body and for the formation of red blood cells.', '14.00', 4, '2020-07-25 07:48:41', '2020-07-25 14:55:42'),
(17, 'Magnesium 250 mg (100 tablets)', 'Magnesium is critical to many bodily processes, and supports nerve, muscle, and heart function.', '12.00', 4, '2020-07-25 07:48:42', '2020-07-25 14:55:42'),
(18, 'Multi-Vitamin (90 capsules)', 'A daily dose of our Multi-Vitamins fulfills a day’s nutritional needs for over 12 vitamins and minerals.', '10.00', 4, '2020-07-25 07:48:43', '2020-07-25 14:55:42');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1000;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
