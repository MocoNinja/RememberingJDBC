/**
*  Based on Makigas' JDBC Tutorial
*  https://gist.githubusercontent.com/danirod/482584904e41e1ec3422266776a9ea61/raw/82e96b6e77552bc90d7e4de068ecd4b8782860bb/alumnos.sql
*/

DROP DATABASE IF EXISTS simple_school;

CREATE DATABASE simple_school CHARACTER SET utf8 COLLATE utf8_spanish2_ci;

USE simple_school;

CREATE TABLE student (
	id_student INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(32) NOT NULL,
	surname VARCHAR(64) NOT NULL,
	birthdate DATE NOT NULL,
	PRIMARY KEY id_student (id_student)
);

CREATE TABLE teacher (
	id_teacher INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(32) NOT NULL,
	surname VARCHAR(64) NOT NULL,
	PRIMARY KEY id_teacher (id_teacher)
);

CREATE TABLE subject (
	id_subject INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(64) NOT NULL,
	id_teacher INT NOT NULL,
	PRIMARY KEY id_subject(id_subject),
	FOREIGN KEY id_teacher(id_teacher) REFERENCES teacher(id_teacher)
);

CREATE TABLE enrollment (
	id_student INT NOT NULL,
	id_subject INT NOT NULL,
	year YEAR NOT NULL,
	grade INT,
	PRIMARY KEY (id_student, id_subject, year),
	FOREIGN KEY id_student(id_student) REFERENCES student(id_student),
	FOREIGN KEY id_subject(id_subject) REFERENCES subject(id_subject)
);

INSERT INTO student VALUES
    (1,'Elena','Pérez','1993-02-18'),
    (2,'David','Sánchez','1992-11-13'),
    (3,'Miguel','López','1992-12-05'),
    (4,'Daniel','Gómez','1993-04-15'),
    (5,'Ana','Martínez','1992-09-29');

INSERT INTO teacher VALUES
    (1,'Javier','Sánchez'),
    (2,'Teresa','López'),
    (3,'Agustín','Domínguez');

INSERT INTO subject VALUES
    (6,'Fundamentos Tecnológicos',1),
    (7,'Teoría de la Programación',2),
    (8,'Bases de Datos',2),
    (9,'Introducción a Algoritmos',3);
