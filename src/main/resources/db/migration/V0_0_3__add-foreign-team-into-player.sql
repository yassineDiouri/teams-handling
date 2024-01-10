alter table player
    add column team_id varchar(50);

alter table player
    add constraint team_fk foreign key (team_id) references team (id);