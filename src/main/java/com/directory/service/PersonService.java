package com.directory.service;

import com.directory.model.Person;
import com.directory.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // Метод для получения всех записей
    public List<Person> list() {
        return personRepository.findAll();
    }

    // Метод для получения всех записей с заданным именем
    public List<Person> personByName(String name) {
        return personRepository.findByName(name);
    }

    // Метод для получения всех записей с заданной фамилией
    public List<Person> personBySurname(String surname) {
        return personRepository.findBySurname(surname);
    }

    // Метод для получения записи по id
    public Optional<Person> findById(Long id) {
        return personRepository.findById(id);
    }

    // Метод для получения записи по email
    public Optional<Person> findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    // Метод для сохранения записи
    public Person save(Person Person) {
        return personRepository.save(Person);
    }

    // Метод для удаления записи
    public Optional<Person> deleteById(Long id) {
        personRepository.deleteById(id);
        return personRepository.findById(id);
    }
}