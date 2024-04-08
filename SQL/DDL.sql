-- Member tables
create table fit_member (
	mem_id 				serial, 
	first_name			text not null,
	last_name			text not null, 
	email               text not null unique,
	signedup_date       date,
	username            text not null unique,
	mem_password        text not null,
	age					int,
	bill				decimal(5, 2) DEFAULT 50.00,
	primary key (mem_id)
);


create table health_metrics (
	health_met_id	serial, 
	mem_id			integer not null,
	weight			decimal(5,2),
	height 			decimal(5,2),
	bmi				decimal(5,2),
	body_fat_perc	decimal(5,2),
	foreign key (mem_id) references fit_member
		on delete CASCADE,
	primary key (health_met_id)
);

create table fitness_goal (
	fit_goal_id 	serial,
	mem_id			integer not null,
	goal 			text,
	st_date 		date,
	end_date		date,
	foreign key (mem_id) references fit_member
		on delete CASCADE,
	primary key (fit_goal_id)
);

create table health_stat (
	health_stat_id		serial,
	steps 				int,
	oxygen_intake		decimal(5,2),
	heart_rate			int,
	blood_pressure		int,
	primary key (health_stat_id)
);

create table exercise_routine (
	exer_rout_id	serial,
	mem_id			integer not null,
	health_stat_id	integer not null,
	exercise		text,
	duration		int,
	target			text,
	
	foreign key (mem_id) references fit_member
		on delete CASCADE,
	foreign key (health_stat_id) references health_stat
		on delete CASCADE,
	primary key (exer_rout_id)
);

create table fitness_ach (
	fit_ach_id		serial,
	mem_id			integer not null,
	fit_goal_id		integer not null,
	complete 		boolean,
	comp_date		date,
	
	
	foreign key (mem_id) references fit_member
		on delete CASCADE,
	foreign key (fit_goal_id) references fitness_goal
		on delete CASCADE,
	primary key (fit_ach_id)
);



-- trainer table
create table trainer (
	trainer_id			serial,
	first_name			text not null,
	last_name			text not null, 
	email               text not null unique,
	username            text not null unique,
	trainer_password    text not null,
	primary key (trainer_id)
);


create table availability (
	avail_id			serial,
	trainer_id 			integer not null,
	avail_date 			date,
	target              text,
	st_time				time,
	end_time			time,
	
	
	foreign key (trainer_id) references trainer
		on delete CASCADE,
	primary key (avail_id)
);


-- Admin tables
create table fit_admin (
	admin_id 			serial,
	first_name			text not null,
	last_name			text not null, 
	email               text not null unique,
	username            text not null unique,
	admin_password      text not null,
	primary key (admin_id)
);

create table room (
    room_id             serial,
    room_name           text,    
    primary key (room_id)
);


create table room_booking (
	room_book_id	serial,
	trainer_id		integer not null,
	avail_id        int,
	room_num		int,
	st_time			time,
	end_time		time,
	booking_date	date,
	capacity		int,
	curr_size		int check(curr_size <= capacity),
	price           decimal(10, 2),
	
	foreign key (avail_id) references availability
		on delete CASCADE,
	foreign key (trainer_id) references trainer
		on delete CASCADE,
	foreign key (room_num) references room
		on delete CASCADE,
	primary key (room_book_id)
);


create table maintenance_booking (
	fit_equip_id		serial,
	admin_id		integer not null,
	room_id			integer not null,
	main_date		date,
	
	primary key (fit_equip_id),
	foreign key (admin_id) references fit_admin
		on delete CASCADE,
	foreign key (room_id) references room
		on delete CASCADE
);


create table fit_session (
	sess_id			serial,
	mem_id			integer not null,
	trainer_id		integer not null,
	room_book_id	integer not null,
	target			text,
	
	primary key(sess_id),
	foreign key (mem_id) references fit_member
		on delete CASCADE,
	foreign key (trainer_id) references trainer
		on delete CASCADE,
	foreign key (room_book_id) references room_booking
		on delete CASCADE
);
