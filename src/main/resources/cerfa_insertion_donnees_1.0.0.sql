INSERT INTO financement (libelle) VALUES 
	('Salariés bénéficiant d’un financement par l’employeur, par un OPCA ou un OPACIF'),
    ('Personnes en recherche d’emploi bénéficiant d’un financement public'),
    ('Personnes en recherche d’emploi bénéficiant d’un financement OPCA'),
    ('Particuliers à leurs propres frais'),
    ('Autres stagiaires');

INSERT INTO specialite (nom, code) VALUES
	('Commerce', '312'),
    ('Ressources humaines', '315'),
    ('Informatique', '326'),
    ('Physique', '115'),
    ('Chimie', '116'),
    ('Mathématiques', '114'),
    ('Sciences de la vie', '118');

INSERT INTO objectif (libelle) VALUES
	('Niveau I et II'),
    ('Niveau III'),
    ('Niveau IV'),
    ('Niveau V'),
    ('CQP'),
    ('CNCP'),
    ('Autres formations professionnelles continues'),
    ('Bilans de compétence'),
    ('VAE');
	
INSERT INTO personne (nom, prenom) VALUES
	('Tanchereau', 'Victor'),
    ('Pereira', 'Mickael'),
    ('Abraham', 'Nathan'),
    ('Martin', 'Coline'),
    ('Raulo', 'Erwan'),
    ('Pussault', 'Pierre'),
    ('Crossouard', 'Rémi'),
    ('Séjourné', 'Thibault'),
    ('Chazarain', 'Camille'),
    ('Daval', 'Eulalie'),
    ('Joubert', 'Romane'),
    ('Fradet', 'Salomé'),
    ('Laplaud', 'Ronan'),
    ('Girard', 'Mathilde'),
	('Poireau', 'Marie'),
	('Jung', 'Jean-François'),
	('Ranieri', 'Claudio'),
	('Broussard', 'Christiane'),
	('Giraud', 'Fabienne'),
    ('Laplaud', 'Clément');
	
INSERT INTO formateur (interne, idFormateur) VALUES
	(1, (SELECT idPersonne FROM personne WHERE nom = 'Jung')),
	(1, (SELECT idPersonne FROM personne WHERE nom = 'Ranieri')),
	(1, (SELECT idPersonne FROM personne WHERE nom = 'Broussard')),
	(0, (SELECT idPersonne FROM personne WHERE nom = 'Giraud')),
	(0, (SELECT idPersonne FROM personne WHERE nom = 'Laplaud' AND prenom = 'Clément'));

INSERT INTO stagiaire (interne, idStagiaire) VALUES
	(1, (SELECT idPersonne FROM personne WHERE nom = 'Tanchereau')),
	(1, (SELECT idPersonne FROM personne WHERE nom = 'Pereira')),
	(1, (SELECT idPersonne FROM personne WHERE nom = 'Abraham')),
	(1, (SELECT idPersonne FROM personne WHERE nom = 'Laplaud'  AND prenom = 'Ronan')),
	(1, (SELECT idPersonne FROM personne WHERE nom = 'Martin')),
	(1, (SELECT idPersonne FROM personne WHERE nom = 'Raulo')),
	(1, (SELECT idPersonne FROM personne WHERE nom = 'Pussault')),
	(1, (SELECT idPersonne FROM personne WHERE nom = 'Crossouard')),
	(1, (SELECT idPersonne FROM personne WHERE nom = 'Séjourné')),
	(1, (SELECT idPersonne FROM personne WHERE nom = 'Chazarain')),
	(1, (SELECT idPersonne FROM personne WHERE nom = 'Daval')),
	(1, (SELECT idPersonne FROM personne WHERE nom = 'Joubert')),
	(1, (SELECT idPersonne FROM personne WHERE nom = 'Fradet')),
	(1, (SELECT idPersonne FROM personne WHERE nom = 'Girard')),
	(1, (SELECT idPersonne FROM personne WHERE nom = 'Poireau'));

INSERT INTO financementstagiaire (fk_stagiaire, fk_financement, dateDebut, dateFin) VALUES
    (1, 1, '2015-01-01', '2017-01-01'),
    (2, 2, '2015-01-01', '2017-01-01'),
    (3, 3, '2015-01-01', '2017-01-01'),
    (4, 4, '2015-01-01', '2017-01-01'),
    (5, 5, '2015-01-01', '2017-01-01'),
    (6, 1, '2015-01-01', NULL),
    (7, 2, '2015-01-01', NULL),
    (8, 3, '2015-01-01', NULL),
    (9, 4, '2015-01-01', NULL),
    (10, 5, '2015-01-01', NULL),
    (11, 1, '2015-01-01', NULL),
    (12, 2, '2015-01-01', NULL),
    (13, 3, '2015-01-01', NULL),
    (14, 4, '2015-01-01', NULL),
    (15, 5, '2015-01-01', NULL),
    (1, 5, '2017-01-02', NULL),
    (2, 4, '2017-01-02', NULL),
    (3, 3, '2017-01-02', NULL),
    (4, 2, '2017-01-02', NULL),
    (5, 1, '2017-01-02', NULL);

INSERT INTO formation (nom, fk_specialite, fk_objectif) VALUES 
    ('Manager d’Univers Marchand', (SELECT idSpecialite FROM specialite WHERE code = '312'), (SELECT idObjectif FROM objectif WHERE Libelle = 'Niveau III')),
    ('Conseiller Relation Client à Distance', (SELECT idSpecialite FROM specialite WHERE code = '312'), (SELECT idObjectif FROM objectif WHERE Libelle = 'Niveau IV')),
    ('Responsable des Ressources Humaine', (SELECT idSpecialite FROM specialite WHERE code = '315'), (SELECT idObjectif FROM objectif WHERE Libelle = 'Niveau I et II')),
    ('Directeur des Ressources Humaines', (SELECT idSpecialite FROM specialite WHERE code = '315'), (SELECT idObjectif FROM objectif WHERE Libelle = 'VAE')),
    ('Développeur Logiciel', (SELECT idSpecialite FROM specialite WHERE code = '326'), (SELECT idObjectif FROM objectif WHERE Libelle = 'Niveau V')),
    ('Concepteur Développeur Informatique', (SELECT idSpecialite FROM specialite WHERE code = '326'), (SELECT idObjectif FROM objectif WHERE Libelle = 'CQP')),
    ('Expert en Etude et Développement du SI', (SELECT idSpecialite FROM specialite WHERE code = '326'), (SELECT idObjectif FROM objectif WHERE Libelle = 'Niveau I et II')),
    ('License Physique', (SELECT idSpecialite FROM specialite WHERE code = '115'), (SELECT idObjectif FROM objectif WHERE Libelle = 'CNCP')),
    ('License Chimie', (SELECT idSpecialite FROM specialite WHERE code = '116'), (SELECT idObjectif FROM objectif WHERE Libelle = 'Autres formations professionnelles continues')),
    ('License Maths', (SELECT idSpecialite FROM specialite WHERE code = '114'), (SELECT idObjectif FROM objectif WHERE Libelle = 'Bilans de compétence')),
    ('License Bio', (SELECT idSpecialite FROM specialite WHERE code = '118'), (SELECT idObjectif FROM objectif WHERE Libelle = 'CQP'));

INSERT INTO creneau (dateDebut, dateFin, interne, fk_formation) VALUES
    ('2017-02-25 09:00:00', '2017-02-25 12:00:00', 1, 1),
    ('2017-02-25 13:00:00', '2017-02-25 17:00:00', 0, 1),
    ('2017-02-25 09:00:00', '2017-02-25 12:00:00', 1, 2),
    ('2017-02-25 13:00:00', '2017-02-25 17:00:00', 0, 2),
    ('2017-02-26 09:00:00', '2017-02-26 17:00:00', 1, 3),
    ('2017-02-26 09:00:00', '2017-02-26 17:00:00', 1, 4),
    ('2017-02-28 10:00:00', '2017-02-28 18:00:00', 1, 5),
    ('2017-03-05 09:00:00', '2017-03-05 12:00:00', 1, 6);
        
INSERT INTO creneaustagiaire (fk_creneau, fk_stagiaire) VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 6),
    (7, 7),
    (8, 8),
    (1, 9),
    (2, 10),
    (3, 11),
    (4, 12),
    (5, 13),
    (6, 14),
    (7, 15),
    (8, 1);
    
INSERT INTO creneauformateur (fk_creneau, fk_formateur) VALUES
    (1,16),
    (2,17),
    (3,18),
    (4,19),
    (5,20),
    (6,16),
    (7,17),
    (8,18);