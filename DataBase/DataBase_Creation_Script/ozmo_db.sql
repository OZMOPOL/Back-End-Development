/*** LET THERE BE DATABASE ***/
DROP DATABASE IF EXISTS ozmoPol;
CREATE DATABASE IF NOT EXISTS ozmoPol;

/*... and let a Jedi control it*/
GRANT ALL ON ozmoPol.* TO 'ozmoJedi'@'localhost' IDENTIFIED BY 'ozmoPolJedi' WITH GRANT OPTION;

/* Day #1: Let there be  Tables */
USE ozmoPol;

DROP TABLE IF EXISTS X_user_flw_user;
DROP TABLE IF EXISTS X_user_flw_room;
DROP TABLE IF EXISTS ozVote;
DROP TABLE IF EXISTS ozPost;
DROP TABLE IF EXISTS ozUser;
DROP TABLE IF EXISTS ozRoom;
DROP TRIGGER IF EXISTS make_post_c_time;

/* # Let there be ozRooms */
/* Status will be shown as "1" for "ACTIVE" and "0" for "INACTIVE"  */
CREATE TABLE ozRoom
(
pk_room_id varchar(32) UNIQUE NOT NULL,
room_title varchar(64) NOT NULL UNIQUE,
room_desc text,
room_status bit DEFAULT 1,

PRIMARY KEY (pk_room_id)
);

/* Let there be ozUsers */
/* Status will be shown as "1" for "ACTIVE" and "0" for "INACTIVE"  */
CREATE TABLE ozUser
(
pk_user_id varchar(32)  UNIQUE NOT NULL,
user_name varchar(32) NOT NULL UNIQUE,
user_pass char(64) NOT NULL,
user_email varchar(32) NOT NULL UNIQUE,
user_bday date,
user_status bit DEFAULT 0,
user_actHash char(128) NOT NULL,

PRIMARY KEY (pk_user_id)
);

/* Let there be ozPosts */
CREATE TABLE ozPost
(
pk_post_id varchar(32) UNIQUE NOT NULL,
post_title varchar(256),
post_content text NOT NULL,
post_c_date DATETIME ,
post_e_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
post_status bit DEFAULT 1,
fk_post_user_id varchar(32) NOT NULL,
fk_post_room_id varchar(32) NOT NULL,
fk_post_prnt_id varchar(32),


PRIMARY KEY (pk_post_id),

FOREIGN KEY (fk_post_user_id) REFERENCES ozUser(pk_user_id),
FOREIGN KEY (fk_post_room_id) REFERENCES ozRoom(pk_room_id),
FOREIGN KEY (fk_post_prnt_id) REFERENCES ozPost(pk_post_id)
);

/* Let There be ozVotes */
CREATE TABLE ozVote
(
pk_vote_id varchar(32) UNIQUE NOT NULL,
vote_value TINYINT(1),
fk_vote_user_id varchar(32) NOT NULL,
fk_vote_post_id varchar(32) NOT NULL,

PRIMARY KEY (pk_vote_id),

FOREIGN KEY (fk_vote_user_id) REFERENCES ozUser(pk_user_id),
FOREIGN KEY (fk_vote_post_id) REFERENCES ozPost(pk_post_id),

UNIQUE KEY user_vote_post (fk_vote_user_id, fk_vote_post_id)
);

/* Let ozUsers Follow ozRooms */
CREATE TABLE X_user_flw_room
(
pk_userXroom_id varchar(32) UNIQUE NOT NULL,
fk_userXroom_user_id varchar(32) NOT NULL,
fk_userXroom_room_id varchar(32) NOT NULL,

PRIMARY KEY (pk_userXroom_id),

FOREIGN KEY (fk_userXroom_user_id) REFERENCES ozUser(pk_user_id),
FOREIGN KEY (fk_userXroom_room_id) REFERENCES ozRoom(pk_room_id),

UNIQUE KEY user_flw_room (fk_userXroom_user_id,fk_userXroom_room_id)
);

/* Let ozUsers Follow ozUsers */
CREATE TABLE X_user_flw_user
(
pk_userXuser_id varchar(32) UNIQUE NOT NULL,
fk_userXuser_flwr_user_id varchar(32) NOT NULL,
fk_userXuser_flwd_user_id varchar(32) NOT NULL,

PRIMARY KEY (pk_userXuser_id),

FOREIGN KEY (fk_userXuser_flwr_user_id) REFERENCES ozUser(pk_user_id),
FOREIGN KEY (fk_userXuser_flwd_user_id) REFERENCES ozUser(pk_user_id),

UNIQUE KEY user_flw_user (fk_userXuser_flwr_user_id, fk_userXuser_flwd_user_id)
);



/* When post is edited, post_e_time is updated */
DELIMITER //

CREATE TRIGGER make_post_c_time 
BEFORE INSERT ON ozPost
FOR EACH ROW
BEGIN
  IF NEW.post_c_date IS NULL THEN
    SET NEW.post_c_date = NOW();
  END IF;
END //

DELIMITER ;



/*** And the time came to pass ***/

/*...*/

/*** Day #2: LET THERE BE TUPLES IN TABLES ACCORDINGLY ***/

/* Then ozRooms had tuples... */
INSERT INTO ozRoom VALUES
('ffffffffffffffff', 'HALL', 'HALL', 1),
('3uh2gkejasdy9243', 'Dining Hall', 'Wahz cookin doc?',1),
('uiybewg4jh3g4gbs', 'Shuttle Hour Updates', 'Shuttles come, Shuttles go, eat a tomato',1),
('ju3hyd2762uytgds', 'Music Events', '... d(~_~)b... ',1),
('rrhysd276185jstf', 'Party Nation', 'This is a serious room',1),
('rakhsd2775632g4s', 'Lets Study', 'Seriously?! who the fu... Mom! Get of the internet!',1),
('weddweddweddwedd', 'Message from sky', 'I heard something from God', 1),
('zxcvzxcvzxcvzxcv' , 'Programming' , 'We need some contribution for startup company', 1),
('nmmmnmmmnmmmnmmm',  'piknik' , 'Ozu family we are planning to have a piknik, anyone ready', 1),
('mxxcmxxcmxxcmxxc' , 'Deadline for project' , 'what do you think we can add to Agile course project?' ,1),
('weddwe11222dwedd', 'Ozumopoling', 'The best app ive seen!!!', 1);


/* So did ozUsers... */
INSERT INTO ozUser VALUES
('ffffffffffffffff', 'GOD', 'ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff', 'god@ozu.edu.tr', '1000-01-01', 1, 'hash'),
('bjkweshgk48y93s4', 'Mosey', 'bjkweshgk48y93s4bjkweshgk48y93s4bjkweshgk48y93s4bjkweshgk48y93s4', 'mosey@ozu.edu.tr', '1100-01-01', 1, 'hash'),
('urb49ne9bek30mme', 'Abey', 'urb49ne9bek30mmeurb49ne9bek30mmeurb49ne9bek30mmeurb49ne9bek30mme', 'abey@ozu.edu.tr', '1000-01-01', 1, 'hash'),
('bjksefkhjw49ub43', 'Jesey', 'bjksefkhjw49ub43bjksefkhjw49ub43bjksefkhjw49ub43bjksefkhjw49ub43', 'jesey@ozu.edu.tr', '1200-01-01', 1, 'hash'),
('geidgeidgeidgeid', 'Mary', 'geidgeidgeidgeidgeidgeidgeidgeidgeidgeidgeidgeidgeidgeidgeidgeid', 'mary@ozu.edu.tr', '1150-01-01', 1, 'hash'),
('euritolfkjnndjfj', 'Momo', 'iuryfhnvjmsklsosiuryfhnvjmsklsosiuryfhnvjmsklsosiuryfhnvjmsklsos', 'Momo@ozu.edu.tr', '1004-01-01', 1, 'hash'),
('bssdbssdbssdbssd', 'noho', 'bssdbssdbssdbssdbssdbssdbssdbssdbssdbssdbssdbssdbssdbssdbssdbssd', 'noho@ozu.edu.tr', '1001-01-04', 1, 'hash'),
('qqwwqqwwqqwwqqww' , 'tyron', 'qqwwqqwwqqwwqqwwqqwwqqwwqqwwqqwwqqwwqqwwqqwwqqwwqqwwqqwwqqwwqqww', 'tyron@ozu.edu.tr','1200-05-09', 1, 'hash'),
('alcnalcnalcnalcn' , 'snow' , 'alcnalcnalcnalcnalcnalcnalcnalcnalcnalcnalcnalcnalcnalcnalcnalcn' , 'snow@ozu.edu.tr' , '1230-09-25', 1, 'hash'),
('fgfufgfufgfufgfu', 'arya', 'fgfufgfufgfufgfufgfufgfufgfufgfufgfufgfufgfufgfufgfufgfufgfufgfu', 'arya@ozu.edu.tr', '1245-04-07', 1, 'hash'),
('hwhshwhshwhshwhs', 'cersi','hwhshwhshwhshwhshwhshwhshwhshwhshwhshwhshwhshwhshwhshwhshwhshwhs', 'cersi@ozu.edu.tr', '1235-10-12', 1 , 'hash'),
('syttsyttsyttsytt', 'little','syttsyttsyttsyttsyttsyttsyttsyttsyttsyttsyttsyttsyttsyttsyttsytt', 'little@ozu.edu.tr', '1235-10-02', 1 , 'hash'),
('uuiiuuiiuuiiuuii', 'sansa','uuiiuuiiuuiiuuiiuuiiuuiiuuiiuuiiuuiiuuiiuuiiuuiiuuiiuuiiuuiiuuii', 'sansa@ozu.edu.tr', '1245-10-02', 1 , 'hash')
 ;

/* And ozPosts started to populate Gods Hall. */

INSERT INTO ozPost VALUES ('kklokklokklokklo' , 'Smilies' , 'if we can add some small images to explain emotion it would be great, isnt it?' , NULL, NULL, NULL, 'uuiiuuiiuuiiuuii' , 'mxxcmxxcmxxcmxxc' , NULL);

INSERT INTO ozPost VALUES ('mmlkmmlkmmlkmmlk' , 'Conference deadline!' , 'The IEEE conference deadline is extented for 1 wee' , NULL, NULL, NULL, 'alcnalcnalcnalcn' , 'zxcvzxcvzxcvzxcv' , NULL);

INSERT INTO ozPost VALUES ('ppaappaappaappaa' , 'Free chai latte' , 'You may come to student services to get a free card as a gift for drinking cafe latte in Nero, CHeeerS!' , NULL, NULL, NULL, 'bssdbssdbssdbssd' , 'nmmmnmmmnmmmnmmm' , NULL);

INSERT INTO ozPost VALUES ('portportportport' , 'Shuttle android app' , 'Do you have any idea about why the app is not working anymore, I dont have access to hours?' , NULL, NULL, NULL, 'bssdbssdbssdbssd' , 'weddwe11222dwedd' , NULL);

INSERT INTO ozPost VALUES ('avnmavnmavnmavnm' , 'Food Quality!' , 'The dishes in dining hall is not edible what is going on? I can not anything there anymore!' , NULL, NULL, NULL, 'bssdbssdbssdbssd' , 'weddwe11222dwedd' , NULL);

INSERT INTO ozPost VALUES ('vllovllovllovllo' , 'Emergency!!' , 'I lost my ID card where I might ask for lost stuff?' , NULL, NULL, NULL,  'urb49ne9bek30mme' , 'weddwe11222dwedd' , NULL);

INSERT INTO ozPost VALUES ('bvnmbvnmbvnmbvnm' , 'Free Pizza party!' , 'There is gathering of machine learning researchers, is there anyone know about details and the Pizza!' , NULL, NULL, NULL, 'qqwwqqwwqqwwqqww' , 'weddwe11222dwedd' , NULL);

DO SLEEP(1);
INSERT INTO ozPost VALUES ('plsdplsdplsdplsd' , 'Opeth Concert' , 'Is there anyone to join us for the concert? Ther are in Istanbul YaaaY!' , NULL, NULL, NULL,  'alcnalcnalcnalcn' , 'ju3hyd2762uytgds' , NULL);

INSERT INTO ozPost VALUES ('ponoponoponopono' , 'Lana Del Ray ' , 'I would be happy if anyone share a high quality full album of hers with me ))' , NULL, NULL, NULL,  'qqwwqqwwqqwwqqww' , 'ju3hyd2762uytgds' , NULL);

INSERT INTO ozPost VALUES
('jdu5oxnwmoylyedr', 'Party at ma haus :D everyone invited... woop woop!!!', 'if you have nothin to do and passing by kadikoy, jus knock n join us. its 24 falan st. no 29. BYOB!!!', NULL , NULL , NULL, 'urb49ne9bek30mme', 'rrhysd276185jstf', NULL );

DO SLEEP(2);

INSERT INTO ozPost VALUES
('lrncndhrkktnchw0', NULL, 'what time is the parteeey? ', NULL , NULL, NULL, 'bjkweshgk48y93s4', 'rrhysd276185jstf', 'jdu5oxnwmoylyedr' );

DO SLEEP(1);

INSERT INTO ozPost VALUES
('hhegdteuiwoodadr', NULL, 'anytime after 8 is fine... before 6 in the next morning ofcourse (6_6) ', NULL , NULL , NULL, 'euritolfkjnndjfj', 'rrhysd276185jstf', 'lrncndhrkktnchw0' );

DO SLEEP(1);

INSERT INTO ozPost VALUES
('h1e322eui322da1r', 'Hey every1!', 'anybody there???! ', NULL , NULL , NULL, 'urb49ne9bek30mme', 'rrhysd276185jstf', 'lrncndhrkktnchw0' );

DO SLEEP(1);

INSERT INTO ozPost VALUES
('krt74yehancleor8', NULL, 'awesome! count me in xoxo ', NULL , NULL , NULL, 'bjksefkhjw49ub43', 'rrhysd276185jstf', 'jdu5oxnwmoylyedr' );

DO SLEEP(1);

INSERT INTO ozPost VALUES
('63yehcldr24qy3er', NULL, 'woohoo... guess whos da DJ :D ', NULL , NULL , NULL, 'urb49ne9bek30mme', 'rrhysd276185jstf', 'krt74yehancleor8' );

DO SLEEP(1);

INSERT INTO ozPost VALUES
('blablabla8i3teh6', NULL, 'Probably maybe definitely ADAM hahahaaa', NULL , NULL , NULL, 'bjksefkhjw49ub43', 'rrhysd276185jstf', '63yehcldr24qy3er' );

INSERT INTO ozPost VALUES
('polishnfnfojndkn', NULL, '...is it Adam?!!', NULL , NULL , NULL, 'bjkweshgk48y93s4', 'rrhysd276185jstf', '63yehcldr24qy3er' );

DO SLEEP(1);

INSERT INTO ozPost VALUES
('uejd[rotlzbah17f', NULL, 'Hahahaha who else could it be!', NULL , NULL , NULL, 'bjkweshgk48y93s4', 'rrhysd276185jstf', 'polishnfnfojndkn' );

DO SLEEP(1);

INSERT INTO ozPost VALUES
('owieowieowieowie', NULL, 'Hahahaha what the hell is going on!', NULL , NULL , NULL, 'euritolfkjnndjfj', 'weddweddweddwedd', 'polishnfnfojndkn' );

/* And people started Voting for ozPosts... and it was all good */

INSERT INTO ozVote VALUES ('ykdrykdrykdrykdr', '1', 'geidgeidgeidgeid', 'owieowieowieowie');
INSERT INTO ozVote VALUES ('dfeldfeldfeldfel', '-1', 'ffffffffffffffff', 'krt74yehancleor8');
INSERT INTO ozVote VALUES ('vnbnvnbnvnbnvnbn', '1', 'bjkweshgk48y93s4', 'krt74yehancleor8');
INSERT INTO ozVote VALUES ('yuiuyuiuyuiuyuiu', '1', 'euritolfkjnndjfj', 'krt74yehancleor8');
INSERT INTO ozVote VALUES ('tqwftqwftqwftqwf', '-1', 'urb49ne9bek30mme', 'owieowieowieowie');
INSERT INTO ozVote VALUES ('frgtfrgtfrgtfrgt', '-1', 'euritolfkjnndjfj', 'owieowieowieowie');
INSERT INTO ozVote VALUES ('41234rgtfrg33rgt', '-1', 'ffffffffffffffff', 'polishnfnfojndkn');
INSERT INTO ozVote VALUES ('lkmmlkmmlkmmlkmm', '1', 'geidgeidgeidgeid', 'krt74yehancleor8');
INSERT INTO ozVote VALUES ('cchhcchhcchhcchh', '1', 'bssdbssdbssdbssd', 'bvnmbvnmbvnmbvnm');
INSERT INTO ozVote VALUES ('mmnnmmnnmmnnmmnn', '1', 'bssdbssdbssdbssd', 'owieowieowieowie');



/* People also could change her/his opinion.... Don't you think different when you learn something new?? */

UPDATE ozVote SET vote_value='-1' WHERE pk_vote_id='ykdrykdrykdrykdr';
UPDATE ozVote SET vote_value='1' WHERE pk_vote_id='dfeldfeldfeldfel';
UPDATE ozVote SET vote_value='-1' WHERE pk_vote_id='vnbnvnbnvnbnvnbn';
UPDATE ozVote SET vote_value='-1' WHERE pk_vote_id='yuiuyuiuyuiuyuiu';
UPDATE ozVote SET vote_value='1' WHERE pk_vote_id='tqwftqwftqwftqwf';
UPDATE ozVote SET vote_value='1' WHERE pk_vote_id='frgtfrgtfrgtfrgt';

/* So everyone can follow ozRooms  */

INSERT INTO X_user_flw_room VALUES('trtgtrtgtrtgtrtg', 'euritolfkjnndjfj' , '3uh2gkejasdy9243');
INSERT INTO X_user_flw_room VALUES('reteretereterete', 'geidgeidgeidgeid' , 'rakhsd2775632g4s');
INSERT INTO X_user_flw_room VALUES('retyretyretyrety', 'bjkweshgk48y93s4' , 'uiybewg4jh3g4gbs');
INSERT INTO X_user_flw_room VALUES('efrtefrtefrtefrt', 'ffffffffffffffff' , 'uiybewg4jh3g4gbs');
INSERT INTO X_user_flw_room VALUES('sbnmsbnmsbnmsbnm', 'ffffffffffffffff' , 'weddwe11222dwedd');
INSERT INTO X_user_flw_room VALUES('mklsmklsmklsmkls', 'ffffffffffffffff' , 'nmmmnmmmnmmmnmmm');
INSERT INTO X_user_flw_room VALUES('asdfasdfasdfasdf', 'qqwwqqwwqqwwqqww' , 'uiybewg4jh3g4gbs');
INSERT INTO X_user_flw_room VALUES('opopopopopopopop', 'alcnalcnalcnalcn' , 'uiybewg4jh3g4gbs');
INSERT INTO X_user_flw_room VALUES('ioppioppioppiopp', 'syttsyttsyttsytt' , 'mxxcmxxcmxxcmxxc');
INSERT INTO X_user_flw_room VALUES('kmnbkmnbkmnbkmnb', 'uuiiuuiiuuiiuuii' , '3uh2gkejasdy9243');
INSERT INTO X_user_flw_room VALUES('ploiploiploiploi', 'uuiiuuiiuuiiuuii' , 'mxxcmxxcmxxcmxxc');


/* And people can follow other people as well, Intresting!! */
INSERT INTO X_user_flw_user VALUES ('gnvbgnvbgnv33nvb', 'urb49ne9bek30mme', 'bjksefkhjw49ub43');
INSERT INTO X_user_flw_user VALUES ('iopliopliopliopl', 'bjksefkhjw49ub43', 'geidgeidgeidgeid');
INSERT INTO X_user_flw_user VALUES ('rthdrthdrthdrthd', 'euritolfkjnndjfj' , 'ffffffffffffffff');
INSERT INTO X_user_flw_user VALUES ('gnvbgnvbgnvbgnvb', 'geidgeidgeidgeid' , 'bjksefkhjw49ub43');
INSERT INTO X_user_flw_user VALUES ('jnmvjnmvjnmvjnmv', 'urb49ne9bek30mme' , 'euritolfkjnndjfj');
INSERT INTO X_user_flw_user VALUES ('iopdiopdiopdiopd', 'urb49ne9bek30mme' , 'geidgeidgeidgeid');
INSERT INTO X_user_flw_user VALUES ('dfrtdfrtdfrtdfrt', 'ffffffffffffffff' , 'euritolfkjnndjfj');
INSERT INTO X_user_flw_user VALUES ('aqwsaqwsaqwsaqws', 'hwhshwhshwhshwhs' , 'fgfufgfufgfufgfu');
INSERT INTO X_user_flw_user VALUES ('mzlamzlamzlamzla', 'uuiiuuiiuuiiuuii' , 'alcnalcnalcnalcn');
INSERT INTO X_user_flw_user VALUES ('qwedqwedqwedqwed', 'ffffffffffffffff' , 'uuiiuuiiuuiiuuii');
INSERT INTO X_user_flw_user VALUES ('rewqrewqrewqrewq', 'syttsyttsyttsytt' , 'euritolfkjnndjfj');
INSERT INTO X_user_flw_user VALUES ('qancqancqancqanc', 'fgfufgfufgfufgfu' , 'alcnalcnalcnalcn');

