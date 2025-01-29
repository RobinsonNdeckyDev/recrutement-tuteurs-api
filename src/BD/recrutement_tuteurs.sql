-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 23 jan. 2025 à 21:25
-- Version du serveur : 10.4.27-MariaDB
-- Version de PHP : 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `recrutement_tuteurs`
--

-- --------------------------------------------------------

--
-- Structure de la table `admins`
--

CREATE TABLE `admins` (
  `id_admin` bigint(20) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `mot_de_passe` varchar(255) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `photo_profil` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `role` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `annees_annonces`
--

CREATE TABLE `annees_annonces` (
  `id_annee` bigint(20) NOT NULL,
  `annee` varchar(255) NOT NULL,
  `date_creation` date DEFAULT NULL,
  `date_modification` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `annonces`
--

CREATE TABLE `annonces` (
  `id_annonce` bigint(20) NOT NULL,
  `date_limite` date NOT NULL,
  `description` varchar(1000) NOT NULL,
  `etat` varchar(255) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `id_annee` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `candidats`
--

CREATE TABLE `candidats` (
  `id` bigint(20) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `photo_profil` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `candidatures`
--

CREATE TABLE `candidatures` (
  `id_candidature` bigint(20) NOT NULL,
  `etat` varchar(255) NOT NULL,
  `motif_refus` varchar(255) DEFAULT NULL,
  `id_annonce` bigint(20) NOT NULL,
  `id_candidat` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `documents`
--

CREATE TABLE `documents` (
  `id_document` bigint(20) NOT NULL,
  `nom_fichier` varchar(255) DEFAULT NULL,
  `type_fichier` varchar(255) DEFAULT NULL,
  `id_candidature` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `formats_documents`
--

CREATE TABLE `formats_documents` (
  `id_format_document` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `nom_format` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `notifications`
--

CREATE TABLE `notifications` (
  `id_notification` bigint(20) NOT NULL,
  `date_envoi` datetime(6) DEFAULT NULL,
  `message` varchar(500) NOT NULL,
  `id_candidat` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `types_documents`
--

CREATE TABLE `types_documents` (
  `id_type_document` bigint(20) NOT NULL,
  `date_creation` date DEFAULT NULL,
  `date_modification` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `est_obligatoire` bit(1) NOT NULL,
  `nom_type` varchar(255) NOT NULL,
  `id_format_document` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`id_admin`),
  ADD UNIQUE KEY `UK47bvqemyk6vlm0w7crc3opdd4` (`email`);

--
-- Index pour la table `annees_annonces`
--
ALTER TABLE `annees_annonces`
  ADD PRIMARY KEY (`id_annee`),
  ADD UNIQUE KEY `UK17cslluql57e74i1v93r7h526` (`annee`);

--
-- Index pour la table `annonces`
--
ALTER TABLE `annonces`
  ADD PRIMARY KEY (`id_annonce`),
  ADD KEY `FKiv1622yl6u7h5h5ofh10vdrwv` (`id_annee`);

--
-- Index pour la table `candidats`
--
ALTER TABLE `candidats`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKbye8vxe9axi0r4mvhuix89rtg` (`email`);

--
-- Index pour la table `candidatures`
--
ALTER TABLE `candidatures`
  ADD PRIMARY KEY (`id_candidature`),
  ADD KEY `FKio05ndprqxas5otcbm2kledd2` (`id_annonce`),
  ADD KEY `FK3pnee3chkfjy4bh0cjmvyp3v8` (`id_candidat`);

--
-- Index pour la table `documents`
--
ALTER TABLE `documents`
  ADD PRIMARY KEY (`id_document`),
  ADD KEY `FK2acu3p70lxd069b0p41gldhhc` (`id_candidature`);

--
-- Index pour la table `formats_documents`
--
ALTER TABLE `formats_documents`
  ADD PRIMARY KEY (`id_format_document`),
  ADD UNIQUE KEY `UK2o6biosfgl3hy4wi8v3rd4vng` (`nom_format`);

--
-- Index pour la table `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`id_notification`),
  ADD KEY `FKm8a2ittug8buk41t6u3nu8cj5` (`id_candidat`);

--
-- Index pour la table `types_documents`
--
ALTER TABLE `types_documents`
  ADD PRIMARY KEY (`id_type_document`),
  ADD KEY `FK4yoyg3b89nkbf3mbho52yap3p` (`id_format_document`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `admins`
--
ALTER TABLE `admins`
  MODIFY `id_admin` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `annees_annonces`
--
ALTER TABLE `annees_annonces`
  MODIFY `id_annee` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `annonces`
--
ALTER TABLE `annonces`
  MODIFY `id_annonce` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `candidats`
--
ALTER TABLE `candidats`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `candidatures`
--
ALTER TABLE `candidatures`
  MODIFY `id_candidature` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `documents`
--
ALTER TABLE `documents`
  MODIFY `id_document` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `formats_documents`
--
ALTER TABLE `formats_documents`
  MODIFY `id_format_document` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `notifications`
--
ALTER TABLE `notifications`
  MODIFY `id_notification` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `types_documents`
--
ALTER TABLE `types_documents`
  MODIFY `id_type_document` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `annonces`
--
ALTER TABLE `annonces`
  ADD CONSTRAINT `FKiv1622yl6u7h5h5ofh10vdrwv` FOREIGN KEY (`id_annee`) REFERENCES `annees_annonces` (`id_annee`);

--
-- Contraintes pour la table `candidatures`
--
ALTER TABLE `candidatures`
  ADD CONSTRAINT `FK3pnee3chkfjy4bh0cjmvyp3v8` FOREIGN KEY (`id_candidat`) REFERENCES `candidats` (`id`),
  ADD CONSTRAINT `FKio05ndprqxas5otcbm2kledd2` FOREIGN KEY (`id_annonce`) REFERENCES `annonces` (`id_annonce`);

--
-- Contraintes pour la table `documents`
--
ALTER TABLE `documents`
  ADD CONSTRAINT `FK2acu3p70lxd069b0p41gldhhc` FOREIGN KEY (`id_candidature`) REFERENCES `candidatures` (`id_candidature`);

--
-- Contraintes pour la table `notifications`
--
ALTER TABLE `notifications`
  ADD CONSTRAINT `FKm8a2ittug8buk41t6u3nu8cj5` FOREIGN KEY (`id_candidat`) REFERENCES `candidats` (`id`);

--
-- Contraintes pour la table `types_documents`
--
ALTER TABLE `types_documents`
  ADD CONSTRAINT `FK4yoyg3b89nkbf3mbho52yap3p` FOREIGN KEY (`id_format_document`) REFERENCES `formats_documents` (`id_format_document`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
