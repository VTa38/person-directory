package com.directory.repository;

import com.directory.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {

    Optional<Person> findById(Long id);

    Optional<Person> findByEmail(String email);

    List<Person> findAll();

    List<Person> findByName(String name);

    List<Person> findBySurname(String surname);
}