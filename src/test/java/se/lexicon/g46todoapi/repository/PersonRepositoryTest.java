package se.lexicon.g46todoapi.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PersonRepositoryTest {
    @Autowired
    private PersonRepository testObject;

    @Test
    void findIdlePeopleTest() {

    }
}
