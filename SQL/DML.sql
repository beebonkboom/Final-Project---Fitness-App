-- Populate members
INSERT INTO fit_member(first_name, last_name, email, signedup_date, username, mem_password, age) VALUES 
('Alice', 'Smith', 'alice.smith@email.com', '2024-01-01', 'alicesmith', 'abc', 24),
('Bob', 'Johnson', 'bob.johnson@email.com', '2024-01-02', 'bobjohnson', 'abc', 20);

INSERT INTO health_metrics(mem_id, weight, height, bmi, body_fat_perc) VALUES 
(1, 72.50, 1.60, 28.32, 20.20),
(2, 85.00, 1.78, 26.83, 22.00);

INSERT INTO fitness_goal(mem_id, goal, st_date, end_date) VALUES 
(1, 'Building muscle', '2024-01-03', '2024-12-01'),
(2, 'Shedding fat', '2024-01-05', '2024-06-01');

INSERT INTO health_stat(steps, oxygen_intake, heart_rate, blood_pressure) VALUES 
(10000, 35.80, 120, 130),
(2000, 36.00, 100, 120);

INSERT INTO exercise_routine(mem_id, health_stat_id, exercise, duration, target) VALUES 
(1, 1, 'crunches, plank, leg-lift, flutter kick, mountain climber', 30, 'Core'),
(2, 2, 'squat, deadlift, bench press, rows, pull ups', 45, 'Strength');

INSERT INTO fitness_ach(mem_id, fit_goal_id, complete, comp_date) VALUES 
(1, 1, false, NULL),
(2, 2, false, NULL);


-- Populate trainers
INSERT INTO trainer(first_name, last_name, email, username, trainer_password) VALUES
('Charlie', 'Williams', 'charlie.williams@email.com', 'charliewilliams', 'abc'), 
('David', 'Brown', 'david.brown@email.com', 'davidbrown', 'abc');

INSERT INTO availability(trainer_id, avail_date, target, st_time, end_time) VALUES 
(1, '2024-04-04', 'Pilates', '09:00:00', '11:00:00'),
(1, '2024-04-06', 'Zumba', '12:00:00', '14:00:00'),
(1, '2024-04-08', 'Spin','18:00:00', '20:00:00'),
(2, '2024-04-05', 'Kickboxing', '14:00:00', '16:00:00'),
(2, '2024-04-07', 'Tai Chi', '15:00:00', '17:00:00'),
(2, '2024-04-09', 'Yoga', '08:00:00', '10:00:00');

-- Populate admin

INSERT INTO fit_admin(first_name, last_name, email, username, admin_password) VALUES 
('Eva', 'Jones', 'eva.jones@email.com', 'evajones', 'abc'), 
('Frank', 'Davis', 'frank.davis@email.com', 'frankdavis', 'abc');

INSERT INTO room(room_name) VALUES
('Studio A'),
('Studio B'),
('Studio C'),
('Studio D');

INSERT INTO room_booking(trainer_id, avail_id, room_num, st_time, end_time, booking_date, capacity, curr_size, price) VALUES 
(1, 1, 1, '09:00:00', '11:00:00', '2024-04-04', 1, 0, 60.00),
(1, 2, 2, '12:00:00', '14:00:00', '2024-04-06', 20, 2, 25.99),
(2, 4, 3, '14:00:00', '16:00:00', '2024-04-05', 15, 0, 30.00),
(2, 6, 4, '08:00:00', '10:00:00', '2024-04-09', 30, 1, 20.00);

INSERT INTO fit_session(mem_id, trainer_id, room_book_id, target) VALUES
(1, 1, 2, 'Zumba'),
(2, 1, 2, 'Zumba'),
(2, 2, 4, 'Yoga');
