create table books (
	book_id int(5)  auto_increment, 
	book_author varchar	(50), 
	book_category varchar (40),
	book_status char (1),
	title varchar (50),
	primary key	(book_id)
);

create table members (
	member_id int(5)  auto_increment,
	first_name varchar(30), 
	last_name varchar(30), 
	member_status char(1), 
	primary key(member_id)
);

create table book_issues (
	issue_id int(5)  auto_increment, 
	return_date_actual date, 
	book_id int(5), 
	return_date_exp date, 
	issue_date date, 
	member_id int(5), 
	issue_status char(1), 
	primary key(issue_id)
);