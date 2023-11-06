package se.lexicon.g46todoapi.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.g46todoapi.domain.entity.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest {

    @Autowired
    private UserRepository testObject;

    private static final String EMAIL = "tester@test.com";

    @BeforeAll
    void setUp() {
        testObject.save(User.builder()
                        .email(EMAIL)
                        .password("TestPW")
                        .expired(false)
                .build());
    }

    @Test
    void updateExpiredByEmailTest() {
        Optional<User> foundByEmail = testObject.findByEmail(EMAIL);
        assertTrue(foundByEmail.isPresent());
        User user = foundByEmail.get();
        assertFalse(user.isExpired());

        testObject.updateExpiredByEmail(EMAIL, true);
        assertTrue(testObject.findByEmail(EMAIL).get().isExpired());
    }

    @Test
    void updatePasswordByEmailTest() {
        Optional<User> foundByEmail = testObject.findByEmail(EMAIL);
        assertTrue(foundByEmail.isPresent());
        User user = foundByEmail.get();
        assertEquals(user.getPassword(), "TestPW");

        testObject.updatePasswordByEmail(user.getEmail(), "NewPW");
        assertEquals(testObject.findByEmail(EMAIL).get().getPassword(), "NewPW");
    }
}
