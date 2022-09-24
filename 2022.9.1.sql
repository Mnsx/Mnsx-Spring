create table user(
id int primary key not null auto_increment,
name varchar(20) not null,
password varchar(20) not null default '123456',
balance double not null default 0);

insert into user values(1, 'mnsx', 123123, 1000);

# 线程池需求，将最大连接数调整至1000
set global max_connections=1000;
 