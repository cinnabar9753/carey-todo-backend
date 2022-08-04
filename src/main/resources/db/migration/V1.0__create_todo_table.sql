CREATE TABLE if NOT EXISTS todo
(
    id   int  not null auto_increment primary key,
    context varchar(255) not null,
    done boolean
);
