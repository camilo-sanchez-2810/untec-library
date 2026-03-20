create database if not exists library;

use library;

create table user(
	id binary(16) primary key,
	name varchar(25) not null,
	middle_name varchar(25) null,
	surname varchar(25) not null,
	second_surname varchar(25) null,
	email varchar(255) unique not null,
	password varchar(255) not null,
	type enum("student", "librarian") default "student",
	created_at timestamp default current_timestamp,

	index idx_user_name (name),
	index idx_user_surname (surname)
);

create table language(
	id int auto_increment primary key,
	name varchar(100) not null,
	code char(3) unique not null,

	index idx_language_name (name),
	index idx_language_code (code)
);

create table category(
	id int auto_increment primary key,
	title varchar(50) unique not null
);

create table author(
	id int auto_increment primary key,
	name varchar(25) not null,
	middle_name varchar(25) null,
	surname varchar(25) not null,
	second_surname varchar(25) null,

	index idx_author_name (name),
	index idx_author_surname (surname)
);

create table book(
	id binary(16) primary key,
	title varchar(200) not null,
	description text null,
	cover_url varchar(500) null,
	file_type enum("pdf", "epub") default "pdf",
	file_url varchar(500) not null,
	language_id int,
	year int not null,

	index idx_book_title (title),
	index idx_book_year (year),

	constraint fk_book_language foreign key (language_id) references language(id)
	on update cascade
	on delete set null
);

create table student(
	id binary(16) primary key,
	user_id binary(16) not null,
	status enum("active", "suspended") default "active",
	max_loan_limit int default 5,

	constraint fk_member_user_id foreign key (user_id) references user(id)
	on update cascade
	on delete restrict
);

create table librarian(
	id binary(16) primary key,
	user_id binary(16) not null,
	access_level int default 1,

	constraint fk_librarian_user_id foreign key (user_id) references user(id)
	on update cascade
	on delete restrict
);

create table loan(
	id binary(16) primary key,
	student_id binary(16),
	book_id binary(16),
	start_date timestamp default current_timestamp,
	expiration_date timestamp not null,
	is_active boolean default true,

	index idx_loan_start (start_date),
	index idx_loan_expiration (expiration_date),
	index idx_loan_active (is_active),
	index idx_loan_student (student_id),
	index idx_loan_book (book_id),

	constraint fk_loan_student foreign key (student_id) references student(id)
	on update cascade
	on delete restrict,
	constraint fk_loan_book foreign key (book_id) references book(id)
	on update cascade
	on delete restrict
);

create table book_author(
	book_id binary(16) not null,
	author_id int not null,

	primary key (book_id, author_id),

	constraint fk_bookauthor_book foreign key (book_id) references book(id)
	on delete cascade,
	constraint fk_bookauthor_author foreign key (author_id) references author(id)
	on delete cascade
);

create table book_category(
	book_id binary(16) not null,
	category_id int not null,

	primary key (book_id, category_id),

	constraint fk_bookcategory_book foreign key (book_id) references book(id)
	on delete cascade,
	constraint fk_bookcategory_category foreign key (category_id) references category(id)
	on delete cascade
);
