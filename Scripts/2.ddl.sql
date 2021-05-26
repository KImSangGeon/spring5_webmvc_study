create table MEMBER (
ID int auto_increment primary key,
EMAIL varchar(255),
PASSWORD varchar(100),
NAME varchar(100),
REGDATE datetime,
unique key (EMAIL) 
);

select * from member;
select * from  member where id = "67";

delete from member where id in ();
select * from member where REGDATE between '2021-05-17' and '2021-05-26' order by regdate desc;

