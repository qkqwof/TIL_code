use scott;

drop table sawon;
drop table phones;
drop table company;
drop table userinfo;

create table company(
	vcode char(2) primary key,
	vendor varchar(20) not null
);

insert into company values('10', '삼성');
insert into company values('20', '엘지');
insert into company values('30', '애플');

create table phones(
	num char(5) primary key,
	model varchar(20) not null,
	price int(6) not null,
	vcode char(2),
	constraint fk_vcode foreign key(vcode) references company(vcode)
);
insert into phones values('S105G','갤럭시 S10 5G',1397000,'10');
insert into phones values('V50SQ','LG V50 ThinQ',1199000,'20');

create table userinfo(
	id varchar(20) primary key,
	pw varchar(20) not null
);

insert into userinfo values('encore','encore');
insert into userinfo values('admin','admin');

commit;