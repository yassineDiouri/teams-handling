create table team
(
    id      varchar(50),
    acronym varchar(10) unique,
    name    varchar(128),
    constraint team_pk primary key (id)
);