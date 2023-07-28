create table customer(
	id int auto_increment primary key,
    c_name varchar(255),
    phone varchar(15),
    cnic varchar(50),
    address varchar(255),
    refphone varchar(15)
);

create table vehicle_owner(
	id int auto_increment primary key,
    o_name varchar(255),
    phone varchar(15),
    cnic varchar(50),
    address varchar(255),
    commission double
);

create table vehicle(
	id int auto_increment primary key,
    v_name varchar(255),
    model int,
    brand varchar(50),
    color varchar(255),
    o_id int,
    foreign key (o_id) references vehicle_owenr (id)
);

SELECT v.id, v.v_name, v.model, v.brand, v.color, o.o_name FROM vehicle v INNER JOIN vehicle_owner o ON v.o_id = o.id;

SELECT b.b_id, c.c_name, v.v_name, b.b_date, b.price, b.status FROM booking b INNER JOIN customer c ON b.c_id = c.id
INNER JOIN vehicle v ON b.v_id = v.id;


create table user(
	id int primary key auto_increment,
    username varchar(255),
    pass varchar(255)
);


SELECT * FROM vehicle WHERE v_name like '%u%';

create table booking(
	b_id int auto_increment primary key,
    c_id int,
    v_id int,
    b_date date,
    complete_date date,
    price double,
    status varchar(50),
    foreign key (v_id) references vehicle (id),
    foreign key (c_id) references customer (id)
);

SELECT * FROM booking WHERE b_date BETWEEN '2021-01-30' AND '2023-01-30';
SELECT * FROM booking WHERE b_date >= '2023-01-30' AND b_date <= '2021-01-30';

SELECT o.o_name, SUM(o.commission) FROM vehicle_owner o INNER JOIN booking b ON o.id = b.v_id;


SELECT o.o_name, SUM(o.commission) FROM vehicle_owner o INNER JOIN vehicle v ON o.id = v.o_id INNER JOIN booking b ON v.id = b.v_id WHERE b.b_date BETWEEN '2021-01-30' AND '2023-01-30' GROUP BY o.o_name;

SELECT b.b_id, c.c_name, v.v_name, b.b_date, b.complete_date, (b.price * DATEDIFF(b.complete_date, b.b_date)) AS price, b.status FROM booking b INNER JOIN customer c ON b.c_id = c.id INNER JOIN vehicle v ON b.v_id = v.id;


SELECT b.b_id, c.c_name, v.v_name, b.b_date, b.price, o.commission, b.status FROM booking b INNER JOIN customer c ON b.c_id = c.id INNER JOIN vehicle v ON b.v_id = v.id INNER JOIN vehicle_owner o ON b.v_id = o.id;


insert into user (username,pass) values ('tabish','tabish123'), ('admin','admin123');

insert into customer (c_name,phone,cnic,address,refphone) values
('Haniya','03356743276','2367251475238','johar','03356743276'),
('Alli','03353183328','2367251475238','johar','03356743276'),
('Hassaan','03438983328','2343534575238','johar','03353123376'),
('Zaeem','03353186274','2367251475546','landhi','01233233344'),
('Basim','03334545638','23672515645238','shah faisal','03365783412');

insert into vehicle_owner (o_name,phone,cnic,address,commission) values
('Ali','03353564754','23672576765','Gulberg',500),
('Ramsha','06543287659','23672576876','Johar',400),
('Zeeshan','033539823249','23672654345487','Malir',300),
('Amir','03983987654','2367257876789','Lyari',200),
('Babar','03356734659','236725765876','Shah Faisal',800),
('Hashim','03365317659','236654365487','Bhadrabaad',700),
('Jamil','03353987987','2367876587','Sadar',900),
('Haresh','0356786662','23323257654577','Clifton',400);

SELECT LAST_INSERT_ID();
SELECT v.id, v.v_name, v.model, v.brand, v.color, o.o_name FROM vehicle v INNER JOIN vehicle_owner o ON v.o_id = o.id;

insert into vehicle (v_name,model,brand,color,o_id) values
('Civic','2022','Honda','black',2),
('Yaris','2023','Toyoa','blue',4),
('Alto','2016','Suzuki','white',3),
('Mehran','2015','Suzuki','white',1),
('Cultus','2018','Suzuki','red',1),
('City','2020','Honda','black',5),
('Swift','2021','Suzuki','green',2),
('Sonata','2020','Hyundai ','black',7),
('BR-V','2022','Honda','white',8),
('Tucson','2023','Hyundai','blue',4),
('Sportage','2022','Kia','black',6);

insert into booking (c_id,v_id,b_date,price, status) values
(1,'18','2023-07-12',255,'on');

insert into booking (c_id,v_id,b_date,price, status, complete_date) values
(1,'9','2022-01-12',255,'Complete','2022-01-22'),
(1,'2','2023-10-15',2325,'Open','2023-11-22'),
(3,'7','2022-04-12',2555,'Open','2022-08-12'),
(1,'5','2022-12-29',5000,'Complete','2023-01-21'),
(2,'11','2021-01-16',1100,'Complete','2021-02-16'),
(2,'6','2021-01-16',1100,'Complete','2021-02-16'),
(2,'1','2021-01-16',1100,'Complete','2021-02-16'),
(4,'3','2022-01-11',1200,'Complete','2022-01-14');


truncate booking;
truncate vehicle_owner;
truncate vehicle;


SELECT sum() FROM booking b INNER JOIN customer c ON b.c_id = c.id INNER JOIN vehicle v ON b.v_id = v.id INNER JOIN vehicle_owner o ON b.v_id = o.id WHERE b_date BETWEEN ? AND ?;
SELECT sum(o.commission) FROM vehicle_owner o INNER JOIN booking b ON o.id = b.v_id GROUP BY o.commission HAVING b.b_date BETWEEN ? AND ?;
SELECT sum(o.commission) FROM vehicle_owner o INNER JOIN booking b ON o.id = b.v_id GROUP BY o.commission Having b.b_date BETWEEN '2021-01-16' AND '2023-08-12';
--
SELECT SUM(price) FROM booking WHERE b_date BETWEEN '2021-01-01' AND '2023-01-12';
-- GROUP BY o.commission



-- .
-- .
-- .
-- .
-- .
SELECT SUM(b.price) - SUM(o.commission) FROM vehicle_owner o INNER JOIN booking b ON o.id = b.v_id WHERE b.b_date BETWEEN '2021-01-16' AND '2023-08-12';



truncate customer;
truncate vehicle_owner;
truncate vehicle;
truncate booking;

describe customer;

SELECT * FROM booking b INNER JOIN customer c ON b.c_id = c.id WHERE c.c_name like 'T%';
SELECT * FROM vehicle v INNER JOIN booking b ON v.id = b.v_id WHERE v.id = 3;
SELECT v_id FROM booking

select * from user;
select * from customer;
select * from vehicle_owenr;
select * from vehicle;
select * from booking;

select c.id as customerid, c.c_name as customer_name, v.v_name as vehicle_name, v.color as color, b.price,
b.c_id, b.v_id from customer c inner join booking b on c.id =  b.c_id inner join vehicle v on v.id =  b.v_id;

-- select all the bookings of tabish
select c.id, c.c_name, b.c_id, b.v_id from customer c inner join booking b on c.id = b.c_id where b.c_id = 1;
select count(c.id) from customer c inner join booking b on c.id = b.c_id where b.c_id = 1;

select * from customer cross join vehicle;


-- Inner Join:
-- Retrieve all bookings along with the corresponding customer names and vehicle names:

SELECT b.b_id, c.c_name AS customer_name, v.v_name AS vehicle_name
FROM booking b
INNER JOIN customer c ON b.c_id = c.id
INNER JOIN vehicle v ON b.v_id = v.id;

-- Left Join:
-- Retrieve all customers and their bookings (if any):

SELECT c.c_name AS customer_name, b.b_id, b.b_date, b.price
FROM customer c
LEFT JOIN booking b ON c.id = b.c_id;

-- Right Join:
-- Retrieve all vehicles along with their corresponding bookings (if any):

SELECT v.v_name AS vehicle_name, b.b_id, b.b_date, b.price
FROM vehicle v
RIGHT JOIN booking b ON v.id = b.v_id;

-- Cross Join:
-- Retrieve all possible combinations of customers and vehicles:

SELECT c.c_name AS customer_name, v.v_name AS vehicle_name
FROM customer c
CROSS JOIN vehicle v;

-- Self Join:
-- Retrieve customers who have the same reference phone number:

SELECT c1.c_name AS customer1, c2.c_name AS customer2
FROM customer c1
INNER JOIN customer c2 ON c1.refphone = c2.refphone
WHERE c1.id <> c2.id;

select address from customer group by address;

SELECT c_id, SUM(price) AS total_price
FROM booking
GROUP BY c_id
ORDER BY total_price DESC;

SELECT c_id, COUNT(*) AS total_bookings
FROM booking
GROUP BY c_id
HAVING total_bookings >= 3
ORDER BY c_id ASC;

SELECT v_id, model, AVG(price) AS average_price
FROM booking
JOIN vehicle ON booking.v_id = vehicle.id
GROUP BY v_id, model
ORDER BY average_price ASC;

SELECT MONTH(b_date) AS month, COUNT(*) AS bookings_count
FROM booking
GROUP BY month
ORDER BY bookings_count DESC;

SELECT c_id, SUM(price) AS total_price
FROM booking
GROUP BY c_id
HAVING total_price > 2000
ORDER BY c_id ASC;


SELECT b.b_id, c.c_name, v.v_name, b.b_date, b.complete_date, b.price, b.status FROM booking b INNER JOIN customer c ON b.c_id = c.id INNER JOIN vehicle v ON b.v_id = v.id;

select v.v_name, o.o_name from vehicle v inner join vehicle_owenr o on v.o_id = o.id where o.o_name like 'Hamza';
select count(v.v_name), o.o_name from vehicle v inner join vehicle_owenr o on v.o_id = o.id where o.o_name like 'Hamza' group by o.o_name;

ALTER TABLE vehicle_owenr RENAME TO vehicle_owner;



ALTER TABLE booking
ADD COLUMN complete_date DATE;

truncate booking