DROP DATABASE IF EXISTS `FILM_STORE_DB`;
CREATE DATABASE FILM_STORE_DB CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE FILM_STORE_DB;

DROP TABLE IF EXISTS TB_MOVIES,TD_DIRECTORS;

CREATE TABLE TB_DIRECTORS(ID_DIRECTOR INT NOT NULL AUTO_INCREMENT,
                          NAME_DIRECTOR VARCHAR(50) NOT NULL,
                          AGE INT NOT NULL,
						  PRIMARY KEY (ID_DIRECTOR));
						  
CREATE TABLE TB_MOVIES(ID_FILM INT NOT NULL AUTO_INCREMENT,
						   TITLE varchar (50) NOT NULL,
                           CATEGORY varchar (50),
                           ID_DIRECTOR INT,
                           PRIMARY KEY (ID_FILM),
                           CONSTRAINT FK_MOVIE_DIRECTOR FOREIGN KEY (ID_DIRECTOR)
                           REFERENCES TB_DIRECTORS (ID_DIRECTOR)
                           ON DELETE NO ACTION
                           ON UPDATE NO ACTION);
                           


INSERT INTO TB_DIRECTORS VALUES(null,'Nombre',122);
INSERT INTO TB_MOVIES VALUES(null,'Titulo','Categoría',1);


<-- select -->
SELECT TITLE,CATEGORY, NAME_DIRECTOR,AGE FROM TB_MOVIES M , TB_DIRECTORS D WHERE M.ID_DIRECTOR = D.ID_DIRECTOR;
<-- get-->
SELECT TITLE,CATEGORY, NAME_DIRECTOR,AGE FROM TB_MOVIES M , TB_DIRECTORS D WHERE M.ID_DIRECTOR = D.ID_DIRECTOR AND ID_FILM = ?;
<-- delete -->
DELETE * FROM TB_MOVIES WHERE ID_FILM = ?;
<--create-->
INSERT INTO TB_DIRECTORS VALUES(null,?,?);
INSERT INTO TB_MOVIES VALUES(null,?,?,(SELECT ID_DIRECTOR FROM TB_DIRECTORS WHERE NAME_DIRECTOR = ?));
<-- update -->


