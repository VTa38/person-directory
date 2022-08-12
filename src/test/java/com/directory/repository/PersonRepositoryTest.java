package com.directory.repository;

import com.directory.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository underTestPersonRepository;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(underTestPersonRepository).build();
    }


    @Test
    void findById() {
        // given
        Person personTest = new Person("TestName", "TestSurname", "TestMiddle_name", 12334567870L, "testEmail@te.st");
        underTestPersonRepository.save(personTest);

        // when
        boolean isFind = underTestPersonRepository.findById(personTest.getId()).isPresent();

        // then
        assertThat(isFind).isTrue();
        underTestPersonRepository.delete(personTest);
    }

    @Test
    void findByNotExistId() {
        // given
        Long notExistTestId = 99999999L;

        // when
        boolean isFind = underTestPersonRepository.findById(notExistTestId).isPresent();

        // then
        assertThat(isFind).isFalse();
    }

    @Test
    void findByEmail() {
        // given
        Person personTest = new Person("TestName", "TestSurname", "TestMiddle_name", 12334567870L, "testExistEmail@te.st");
        underTestPersonRepository.save(personTest);

        // when
        boolean isFind = underTestPersonRepository.findByEmail(personTest.getEmail()).isPresent();

        // then
        assertThat(isFind).isTrue();
        underTestPersonRepository.delete(personTest);
    }

    @Test
    void findByNotExistEmail() {
        // given
        String notExistTestEmail = "notExistTestEmail@te.st";

        // when
        boolean isFind = underTestPersonRepository.findByEmail(notExistTestEmail).isPresent();

        // then
        assertThat(isFind).isFalse();
    }
}