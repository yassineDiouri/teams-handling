CREATE SEQUENCE "player_seq"
    MINVALUE 1
    MAXVALUE 999999999
    INCREMENT BY 1;

create table player
(
    id        bigint auto_increment,
    firstname varchar(10),
    lastname  varchar(10),
    position  varchar(10),
    birthdate timestamp,
    constraint player_pk primary key (id)
);