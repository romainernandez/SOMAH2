-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Ven 02 Décembre 2016 à 21:35
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `somah2_web`
--
CREATE DATABASE IF NOT EXISTS `somah2_web` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `somah2_web`;

-- --------------------------------------------------------

--
-- Structure de la table `association_period_topic`
--

DROP TABLE IF EXISTS `association_period_topic`;
CREATE TABLE IF NOT EXISTS `association_period_topic` (
  `period_id` int(11) NOT NULL,
  `topic_id` int(11) NOT NULL,
  PRIMARY KEY (`period_id`,`topic_id`),
  KEY `association_period_topic_ibfk_2` (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Vider la table avant d'insérer `association_period_topic`
--

TRUNCATE TABLE `association_period_topic`;
--
-- Contenu de la table `association_period_topic`
--

INSERT INTO `association_period_topic` (`period_id`, `topic_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9);

-- --------------------------------------------------------

--
-- Structure de la table `content`
--

DROP TABLE IF EXISTS `content`;
CREATE TABLE IF NOT EXISTS `content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image` longblob,
  `topic_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `content_ibfk_1` (`topic_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Vider la table avant d'insérer `content`
--

TRUNCATE TABLE `content`;
--
-- Contenu de la table `content`
--

INSERT INTO `content` (`id`, `image`, `topic_id`) VALUES
(1, NULL, 1),
(2, NULL, 1),
(3, NULL, 2),
(4, NULL, 2),
(5, NULL, 2),
(6, NULL, 2);

-- --------------------------------------------------------

--
-- Structure de la table `content_tr`
--

DROP TABLE IF EXISTS `content_tr`;
CREATE TABLE IF NOT EXISTS `content_tr` (
  `content_id` int(11) NOT NULL,
  `language_code` varchar(2) NOT NULL,
  `title` text NOT NULL,
  `text` text NOT NULL,
  PRIMARY KEY (`content_id`,`language_code`),
  KEY `content_tr_ibfk_2` (`language_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Vider la table avant d'insérer `content_tr`
--

TRUNCATE TABLE `content_tr`;
--
-- Contenu de la table `content_tr`
--

INSERT INTO `content_tr` (`content_id`, `language_code`, `title`, `text`) VALUES
(1, 'en', 'Benefits for the baby', 'Breast milk provides the ideal nutrition for infants. It has a nearly perfect mix of vitamins, protein, and fat -- everything your baby needs to grow. And it''s all provided in a form more easily digested than infant formula. Breast milk contains antibodies that help your baby fight off viruses and bacteria. Plus, babies who are breastfed exclusively for the first 4-6 months, without any formula, have fewer ear infections, respiratory illnesses, and bouts of diarrhea. They also have fewer hospitalizations and trips to the doctor.\r\n\r\nBreastfeeding has been linked to higher IQ scores in later childhood in some studies. What''s more, the physical closeness, skin-to-skin touching, and eye contact all help your baby bond with you and feel secure. Breastfed infants are more likely to gain the right amount of weight as they grow rather than become overweight children. The Directorate of Health says breastfeeding also may play a role in the prevention of SIDS (sudden infant death syndrome). It''s been thought to lower the risk of diabetes, obesity, and certain cancers as well, but more research is needed.'),
(2, 'en', 'Benefits for the mother', 'Breastfeeding burns extra calories, so it can help you lose pregnancy weight faster. It releases the hormone oxytocin, which helps your uterus return to its pre-pregnancy size and may reduce uterine bleeding after birth. Breastfeeding also lowers your risk of breast and ovarian cancer. It may lower your risk of diabetes and cardiac heart disease, too.\r\nSince you don''t have to buy and prepare formula, it saves you time and money. It also gives you regular time to relax quietly with your newborn as you bond.'),
(3, 'en', '', 'The most important preparation is that you know yourself that you want to breastfeed. It can be difficult starting, but there is advice for the most part.'),
(4, 'en', '', 'If you have not breastfed before, you may have questions about certain things, such as whether the breasts or breast bud the shape does not matter, whether you can breastfeed despite breast operations, the ability to breastfeed can be inherited or what you can or should eat and drink. On most issues, there are answers.'),
(5, 'en', '', 'Some women are worried about breast buds their are too big, too small or too flat to trouble breastfeeding. The shape of the breast buds does not really matter as long as they and tissue surrounding them are stretchy. On rare occasions they are called introspective and slips away when you try to pull them out. But this does not necessarily preclude successful breastfeeding.'),
(6, 'en', '', 'Some women find that they get gestational streaks (striae) on the breasts, which you can also get the stomach and thighs. Some believe that such streaks can be prevented if one massaging the skin lightly with eg. olive oil or lotion. The effect is not scientifically proven, but this old wives'' tale is at least both conveniently and harmless.');

-- --------------------------------------------------------

--
-- Structure de la table `language`
--

DROP TABLE IF EXISTS `language`;
CREATE TABLE IF NOT EXISTS `language` (
  `code` varchar(2) NOT NULL,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Vider la table avant d'insérer `language`
--

TRUNCATE TABLE `language`;
--
-- Contenu de la table `language`
--

INSERT INTO `language` (`code`, `name`) VALUES
('en', 'English'),
('fr', 'Français'),
('nb', 'Norsk bokmål');

-- --------------------------------------------------------

--
-- Structure de la table `period`
--

DROP TABLE IF EXISTS `period`;
CREATE TABLE IF NOT EXISTS `period` (
  `id` int(11) NOT NULL,
  `image` longblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Vider la table avant d'insérer `period`
--

TRUNCATE TABLE `period`;
--
-- Contenu de la table `period`
--

INSERT INTO `period` (`id`, `image`) VALUES
(1, NULL),
(2, NULL),
(3, NULL),
(4, NULL),
(5, NULL),
(6, NULL),
(7, NULL),
(8, NULL),
(9, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `period_tr`
--

DROP TABLE IF EXISTS `period_tr`;
CREATE TABLE IF NOT EXISTS `period_tr` (
  `period_id` int(11) NOT NULL,
  `language_code` varchar(2) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`period_id`,`language_code`),
  KEY `period_tr_ibfk_2` (`language_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Vider la table avant d'insérer `period_tr`
--

TRUNCATE TABLE `period_tr`;
--
-- Contenu de la table `period_tr`
--

INSERT INTO `period_tr` (`period_id`, `language_code`, `name`) VALUES
(1, 'en', 'During pregnancy'),
(1, 'fr', 'Durant la grossesse'),
(2, 'en', '0-2 weeks'),
(3, 'en', '1-2 months'),
(4, 'en', '3-4 months'),
(5, 'en', '5-6 months'),
(6, 'en', '7-9 months'),
(7, 'en', '10-12 months'),
(8, 'en', '13-15 months'),
(9, 'en', '16-26 months');

-- --------------------------------------------------------

--
-- Structure de la table `topic`
--

DROP TABLE IF EXISTS `topic`;
CREATE TABLE IF NOT EXISTS `topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image` longblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

--
-- Vider la table avant d'insérer `topic`
--

TRUNCATE TABLE `topic`;
--
-- Contenu de la table `topic`
--

INSERT INTO `topic` (`id`, `image`) VALUES
(1, NULL),
(2, NULL),
(3, NULL),
(4, NULL),
(5, NULL),
(6, NULL),
(7, NULL),
(8, NULL),
(9, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `topic_tr`
--

DROP TABLE IF EXISTS `topic_tr`;
CREATE TABLE IF NOT EXISTS `topic_tr` (
  `topic_id` int(11) NOT NULL,
  `language_code` varchar(2) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`topic_id`,`language_code`),
  KEY `topic_tr_ibfk_2` (`language_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Vider la table avant d'insérer `topic_tr`
--

TRUNCATE TABLE `topic_tr`;
--
-- Contenu de la table `topic_tr`
--

INSERT INTO `topic_tr` (`topic_id`, `language_code`, `name`) VALUES
(1, 'en', 'Benefits of breastfeeding'),
(2, 'en', 'Preparation for breastfeeding'),
(3, 'en', 'Motivation for breastfeeding'),
(4, 'en', 'Anatomy of the breast'),
(5, 'en', 'Previous negative breastfeeding experiences ?'),
(6, 'en', 'Has the mother had any operation of the breast ?'),
(7, 'en', 'Information about Ammehjelpen'),
(8, 'en', 'Information about Mother-child friendly hospitals'),
(9, 'en', 'Maternal food intake during breastfeeding');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `association_period_topic`
--
ALTER TABLE `association_period_topic`
  ADD CONSTRAINT `association_period_topic_ibfk_1` FOREIGN KEY (`period_id`) REFERENCES `period` (`id`),
  ADD CONSTRAINT `association_period_topic_ibfk_2` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`);

--
-- Contraintes pour la table `content`
--
ALTER TABLE `content`
  ADD CONSTRAINT `content_ibfk_1` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`);

--
-- Contraintes pour la table `content_tr`
--
ALTER TABLE `content_tr`
  ADD CONSTRAINT `content_tr_ibfk_1` FOREIGN KEY (`content_id`) REFERENCES `content` (`id`),
  ADD CONSTRAINT `content_tr_ibfk_2` FOREIGN KEY (`language_code`) REFERENCES `language` (`code`);

--
-- Contraintes pour la table `period_tr`
--
ALTER TABLE `period_tr`
  ADD CONSTRAINT `period_tr_ibfk_1` FOREIGN KEY (`period_id`) REFERENCES `period` (`id`),
  ADD CONSTRAINT `period_tr_ibfk_2` FOREIGN KEY (`language_code`) REFERENCES `language` (`code`);

--
-- Contraintes pour la table `topic_tr`
--
ALTER TABLE `topic_tr`
  ADD CONSTRAINT `topic_tr_ibfk_1` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`),
  ADD CONSTRAINT `topic_tr_ibfk_2` FOREIGN KEY (`language_code`) REFERENCES `language` (`code`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
