drop table users
create table users(
	username varchar primary key,
	pass varchar,
	admin boolean
);
create table expenses(
	id serial primary key,
	amount numeric,
	type varchar,
	description varchar,
	username varchar references users(username),
	status varchar default 'PENDING'
)
insert into users(username, pass, admin) values('Abby', 'abs',true),('Billy','biceps',false);