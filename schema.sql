create table ers_users (
	ers_users_id 	serial	 primary key,
	ers_username 	varchar(50) unique,
	ers_password 	varchar(50),
	user_first_name varchar(100),
	user_last_name 	varchar(100),
	user_email 		varchar(150) unique,
	user_role_id	integer
);

create table ers_users_roles (
	ers_user_role_id	integer foreign key,
	user_role			varchar(20)
);

create table salts (
	ers_users_id	integer foreign key,
	salt			varchar(20)
);

create table ers_reimbursment (
	reimb_id serial primary key,
	reimb_amount numeric not null,
	reimb_submitted timestamp default current_date,
	reimb_resolved timestamp,
	reimb_description text,
	reimb_receipt varchar,
	reimb_author_id integer foreign key not null,
	reimb_resolver_id integer foreign key,
	reimb_status_id integer foreign key,
	reimb_type_id integer foreign key 
);

create table ers_reimbursement_status (
	reimb_status_id		integer primary key,
	reimb_status		varchar(20)
);

create table ers_reimb_type (
	reimb_type_id		integer primary key,
	reimb_type			varchar(20)
);

-- --------------------------  SEEDS  --------------------------

insert into salts(ers_user_id, salt) values 
	(1, '8411656f62ede1de1a2aa4e3625041a8'),
	(2, '8d14d00c1a4d9c44ebcd3124e42a94f1'),
	(3, '7cd21eb2fde753f51a7d8ebf72b18a0e');

insert into ers_users_roles(ers_user_role_id, user_role) values
	(1, 'Finance Manager'),
	(2, 'Employee');

insert into ers_reimbursement_status (reimb_status_id, reimb_status) values
	(1, 'PENDING'),
	(2, 'APPROVED'),
	(3, 'DENIED');

insert into ers_reimbursement_type (reimb_type_id, reimb_type) values 
	(1, 'LODGING'),
	(2, 'TRAVEL'),
	(3, 'FOOD'),
	(4, 'OTHER');

insert into ers_reimbursement (reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_receipt, reimb_author_id, reimb_resolver_id, reimb_status_id, reimb_type_id) values
	(1, 50, 2019-11-01 00:00:00, null, 'Hotel', 'https://s3.amazonaws.com/cdn-origin-etr.akc.org/wp-content/uploads/2017/11/12231413/Labrador-Retriever-MP.jpg', 2, 1, null, 1),
	(2, 50, 2019-11-01 00:00:00, null, 'Hotel', 'https://s3.amazonaws.com/cdn-origin-etr.akc.org/wp-content/uploads/2017/11/12231413/Labrador-Retriever-MP.jpg', 2, 1, null, 1),
	(3, 50, 2019-11-01 00:00:00, null, 'Hotel', 'https://s3.amazonaws.com/cdn-origin-etr.akc.org/wp-content/uploads/2017/11/12231413/Labrador-Retriever-MP.jpg', 2, 1, null, 1),
	(4, 50, 2019-11-01 00:00:00, null, 'Hotel', 'https://s3.amazonaws.com/cdn-origin-etr.akc.org/wp-content/uploads/2017/11/12231413/Labrador-Retriever-MP.jpg', 2, 1, null, 1),
	
insert into ers_users (ers_users_id, ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id) values
	(1, 'aadams', 'b04fc1651221026a6c38bec20e56d308', 'Abby', 'Adams', 'aadams@users.net', 1),
	(2, 'bburke', '8d091b7aa4437123b747d536dfac8692', 'Billy', 'Burke', 'bburke@users.net', 2),
	(3, 'cchaus', 'dd4e4649fb3385a850ef97ba9f8bb5be', 'Cindy', 'Chaus', 'cchaus@users.net', 1);