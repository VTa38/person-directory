create sequence hibernate_sequence start 1 increment 1;

create table if not exists person(id int8 not null,
                    name varchar(255) not null,
                    surname varchar(255) not null,
                    middle_name varchar(255) not null,
                    phone_number int8 not null,
                    email varchar(255) not null,
                    primary key (id)
                    );

alter table if exists person
        add constraint person_email_fk
        unique (email);

alter table if exists person
        add constraint person_phone_number_fk
        unique (phone_number);
