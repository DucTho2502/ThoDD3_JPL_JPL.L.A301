create database SMS

use SMS

CREATE TABLE Customer(
	[customer_id] [int] IDENTITY(1,1) PRIMARY KEY not null,
	[customer_name] [nvarchar](250) UNIQUE not null
)

CREATE TABLE Employee(
	[employee_id] [int] IDENTITY(1,1) PRIMARY KEY not null,
	[employee_name] [nvarchar](250) UNIQUE not null,
	[salary] [nvarchar](250) not null,
	[supervisor_id] INT REFERENCES Employee(employee_id),
)

CREATE TABLE Product(
	[product_id] [int] IDENTITY(1,1) PRIMARY KEY not null,
	[product_name] [nvarchar](250) UNIQUE not null,
	[list_price] [money] not null
)

CREATE TABLE Orders(
	[order_id] [int] IDENTITY(1,1) PRIMARY KEY not null,
	[order_date] [date] not null,
	[customer_id] [int] REFERENCES Customer(customer_id),
	[employee_id] [int] REFERENCES Employee(employee_id),
	[total] int
)

CREATE TABLE LineItem(
	[order_id] [int] REFERENCES Orders(order_id),
	[product_id] [int] REFERENCES Product(product_id),
	[quantity] [int] not null,
	[price] [int] not null
)

INSERT INTO Customer VALUES ('Nguyen Van A'), ('Nguyen Van B'), ('Nguyen Van C'), ('Nguyen Van D'), ('Nguyen Van E')

INSERT INTO Employee VALUES ('Emp A', 10000, 2), ('Emp B', 20000, 1), ('Emp C', 15000, 2), ('Emp D', 10000, 1), ('Emp E', 10000, 1)

INSERT INTO Product VALUES ('Product A', 100), ('Product B', 150), ('Product C', 160), ('Product D', 170), ('Product E', 180), ('Product F', 200)

INSERT INTO Orders VALUES ('2010-02-25', 1, 1, 0), ('2011-02-25', 2, 2, 0), ('2012-02-25', 3, 3, 0), ('2013-02-25', 4, 1, 0), ('2014-02-25', 5, 2, 0), ('2015-02-25', 5, 1, 0)

INSERT INTO LineItem VALUES (1, 1, 10, 100), (1, 2, 5, 200), (2, 3, 10, 150), (3, 3, 10, 100), (4, 4, 10, 100), (5, 5, 10, 100), (5, 4, 20, 120)