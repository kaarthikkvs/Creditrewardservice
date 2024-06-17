CREATE TABLE CUSTOMER (customer_id int, customer_name VARCHAR2(50) );
CREATE TABLE TRANSACTION (transaction_id int,customer_id int ,transaction_date DATE,transaction_amount int);
INSERT INTO CUSTOMER(customer_id,customer_name) values (101,'Customer1');
INSERT INTO CUSTOMER(customer_id,customer_name) values (102,'Customer2');
INSERT INTO CUSTOMER(customer_id,customer_name) values (103,'Customer3');
INSERT INTO TRANSACTION(transaction_id,customer_id,transaction_date,transaction_amount) VALUES (1001,101,'2024-04-22',120);
INSERT INTO TRANSACTION(transaction_id,customer_id,transaction_date,transaction_amount) VALUES (1002,101,'2024-04-22',85);
INSERT INTO TRANSACTION(transaction_id,customer_id,transaction_date,transaction_amount) VALUES (1003,101,'2024-03-22',160);
INSERT INTO TRANSACTION(transaction_id,customer_id,transaction_date,transaction_amount) VALUES (1004,101,'2024-03-222',90);
INSERT INTO TRANSACTION(transaction_id,customer_id,transaction_date,transaction_amount) VALUES (1005,101,'2024-05-22',120);
INSERT INTO TRANSACTION(transaction_id,customer_id,transaction_date,transaction_amount) VALUES (1006,101,'2024-05-22',165);
INSERT INTO TRANSACTION(transaction_id,customer_id,transaction_date,transaction_amount) VALUES (1007,102,'2024-04-26',113);
INSERT INTO TRANSACTION(transaction_id,customer_id,transaction_date,transaction_amount) VALUES (1008,102,'2024-03-27',80);
INSERT INTO TRANSACTION(transaction_id,customer_id,transaction_date,transaction_amount) VALUES (1009,102,'2024-03-27',102);
INSERT INTO TRANSACTION(transaction_id,customer_id,transaction_date,transaction_amount) VALUES (1010,102,'2024-03-27',210);
INSERT INTO TRANSACTION(transaction_id,customer_id,transaction_date,transaction_amount) VALUES (1011,102,'2024-05-27',130);
INSERT INTO TRANSACTION(transaction_id,customer_id,transaction_date,transaction_amount) VALUES (1012,102,'2024-04-22',88);
INSERT INTO TRANSACTION(transaction_id,customer_id,transaction_date,transaction_amount) VALUES (1013,103,'2024-04-26',102);
INSERT INTO TRANSACTION(transaction_id,customer_id,transaction_date,transaction_amount) VALUES (1014,103,'2024-03-27',84);
INSERT INTO TRANSACTION(transaction_id,customer_id,transaction_date,transaction_amount) VALUES (1005,103,'2024-03-27',200);
INSERT INTO TRANSACTION(transaction_id,customer_id,transaction_date,transaction_amount) VALUES (1016,103,'2024-03-27',103);
INSERT INTO TRANSACTION(transaction_id,customer_id,transaction_date,transaction_amount) VALUES (1017,103,'2024-03-27',500);
INSERT INTO TRANSACTION(transaction_id,customer_id,transaction_date,transaction_amount) VALUES (1018,103,'2024-04-26',585);
INSERT INTO TRANSACTION(transaction_id,customer_id,transaction_date,transaction_amount) VALUES (1019,103,'2024-05-26',102);
COMMIT;