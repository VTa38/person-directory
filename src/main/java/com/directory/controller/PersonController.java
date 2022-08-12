package com.directory.controller;

import com.directory.exception.BadRequestException;
import com.directory.exception.NotFoundException;
import com.directory.model.Person;
import com.directory.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.directory.util.ApplicationUtils.isEmpty;
import static com.directory.util.Constants.BAD_REQUEST_MESSAGE;
import static com.directory.util.Constants.NOT_FOUND_MESSAGE;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/persons")
@Tag(name = "Person Controller", description = "Working with person directory")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    // Получение всех записей из БД
    @GetMapping
    @Operation(summary = "Get all persons")
    public ResponseEntity<List<Person>> list() {
        return new ResponseEntity<>(personService.list(), OK);
    }

    // Получение всех записей из БД с заданной фамилией
    @GetMapping("/name")
    @Operation(summary = "Get all persons with some name")
    public ResponseEntity<List<Person>> personByName(@Parameter(description = "person's name")
                                                     @RequestParam("name") String name) {
        return new ResponseEntity<>(personService.personByName(name), OK);
    }

    // Получение всех записей из БД с заданной фамилией
    @GetMapping("/surname")
    @Operation(summary = "Get all persons with some surname")
    public ResponseEntity<List<Person>> personBySurname(@Parameter(description = "person's surname")
                                                        @RequestParam("surname") String surname) {
        return new ResponseEntity<>(personService.personBySurname(surname), OK);
    }

    // Получение записи из БД по id
    @GetMapping("/id")
    @Operation(summary = "Get person by id")
    public ResponseEntity<Person> findById(@Parameter(description = "person's id")
                                           @RequestParam("id") Long id) {
        return new ResponseEntity<>(personService.findById(id).
                orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE, NOT_FOUND)), OK);
    }

    // Получение записи из БД по email
    @GetMapping("/email")
    @Operation(summary = "Get person by email")
    public ResponseEntity<Person> findByEmail(@Parameter(description = "person's email")
                                              @RequestParam("email") String email) {
        return new ResponseEntity<>(personService.findByEmail(email).
                orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE, NOT_FOUND)), OK);
    }


    // Добавление записи в БД
    @PostMapping
    @Operation(summary = "Create new person in directory")
    public ResponseEntity<Person> addPerson(@Parameter(description = "Json of person")
                                            @RequestBody Person person) {
        Optional<Person> optionalPersonByEmail = personService.findByEmail(person.getEmail());
        if (optionalPersonByEmail.isPresent())
            throw new BadRequestException(BAD_REQUEST_MESSAGE, BAD_REQUEST);
        Person personToSave = new Person(person.getName(), person.getSurname(), person.getMiddle_name(), person.getPhone_number(), person.getEmail());
        return new ResponseEntity<>(personService.save(person), CREATED);
    }

    // Удаляет запись из БД
    @DeleteMapping
    @Operation(summary = "Delete person from directory")
    public ResponseEntity<Person> deletePerson(@Parameter(description = "person's id")
                                               @RequestParam("id") Long id) {
        Optional<Person> deletedCar = personService.deleteById(id);
        if (deletedCar.isPresent())
            throw new BadRequestException(BAD_REQUEST_MESSAGE, BAD_REQUEST);

        return new ResponseEntity<>(OK);
    }

    // Изменение записи в БД
    @PutMapping
    @Operation(summary = "Update some information about person")
    public ResponseEntity<Person> setPerson(@Parameter(description = "person's id")
                                            @RequestParam("id") Long id,
                                            @Parameter(description = "Json of new person's information")
                                            @RequestBody Person person) {
        return personService.findById(id).map(oldPerson -> {
            oldPerson.setName(isEmpty(person.getName()) ? oldPerson.getName() : person.getName());
            oldPerson.setSurname(isEmpty(person.getSurname()) ? oldPerson.getSurname() : person.getSurname());
            oldPerson.setMiddle_name(isEmpty(person.getMiddle_name()) ? oldPerson.getMiddle_name() : person.getMiddle_name());
            oldPerson.setEmail(isEmpty(person.getEmail()) ? oldPerson.getEmail() : person.getEmail());
            oldPerson.setPhone_number(isEmpty(person.getPhone_number()) ? oldPerson.getPhone_number() : person.getPhone_number());
            return personService.save(oldPerson);
        }).map(newPerson -> new ResponseEntity<>(newPerson, OK)).orElse(new ResponseEntity<>(person, BAD_REQUEST));
    }
}
