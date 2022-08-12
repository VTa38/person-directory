package com.directory.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Person {
    // Для простоты я взял за правило, что поля не могут быть пустыми
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String middle_name;
    @Column(nullable = false, unique = true)
    private Long phone_number;
    @Column(nullable = false, unique = true)
    private String email;

    public Person() {
    }

    public Person(String name, String surname, String middle_name, Long phone_number, String emil) {
        this.name = name;
        this.surname = surname;
        this.middle_name = middle_name;
        this.phone_number = phone_number;
        this.email = emil;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public Long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(Long phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return Objects.equals(name, person.name) &&
                Objects.equals(surname, person.surname) &&
                Objects.equals(middle_name, person.middle_name) &&
                Objects.equals(phone_number, person.phone_number) &&
                Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, middle_name, phone_number, email);
    }

}