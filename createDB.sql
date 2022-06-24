create table owners
(
    id           serial
        constraint owners_pk
            primary key,
    "first_name" varchar(100) not null,
    "last_name"  varchar(100) not null,
    phone        varchar(10)  not null
);

create table cars
(
    id        serial
        constraint cars_pk
            primary key,
    model     varchar(100) not null,
    price     int          not null,
    owner     int
        constraint cars_owners_id_fk
            references owners,
    clients     int
        constraint cars_clients_id_fk
            references clients,
    available boolean      not null
);

create table clients
(
    id         serial
        constraint clients_pk
            primary key,
    first_name varchar(100) not null,
    last_name  varchar(100) not null,
    phone      varchar(10)  not null
);

INSERT INTO owners (first_name, last_name, phone)
VALUES ('Petr', 'Petrov', '0979711791');

INSERT INTO owners (first_name, last_name, phone)
VALUES ('Ivan', 'Ivanov', '0573212121');

INSERT INTO owners (first_name, last_name, phone)
VALUES ('Alex', 'Smith', '0939393931');

INSERT INTO owners (first_name, last_name, phone)
VALUES ('Valentin', 'Popov', '0667677667');

INSERT INTO cars (model, price, owner, clients, available)
VALUES ('Honda Civic', '200', 1, null, true);

INSERT INTO cars (model, price, owner, clients, available)
VALUES ('Daewoo Lanos', '55', 1, null, true);

INSERT INTO cars (model, price, owner, clients, available)
VALUES ('Toyota Corolla', '180', 2, null, true);

INSERT INTO cars (model, price, owner, clients, available)
VALUES ('Toyota Camry', '165', 3, null, true);

INSERT INTO cars (model, price, owner, clients, available)
VALUES ('Ram Pick-up', '250', 3, null, true);

INSERT INTO cars (model, price, owner, clients, available)
VALUES ('Ford F-Series', '135', 4, null, true);

INSERT INTO clients (first_name, last_name, phone)
VALUES ('Mary', 'Liskov', '0979711791');

INSERT INTO clients (first_name, last_name, phone)
VALUES ('Danil', 'Markov', '0573212121');

INSERT INTO clients (first_name, last_name, phone)
VALUES ('Mark', 'Avrelii', '0939393931');