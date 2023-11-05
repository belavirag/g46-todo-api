package se.lexicon.g46todoapi.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.g46todoapi.domain.dto.RoleDTOForm;
import se.lexicon.g46todoapi.domain.dto.UserDTOForm;
import se.lexicon.g46todoapi.domain.dto.UserDTOView;
import se.lexicon.g46todoapi.domain.entity.Role;
import se.lexicon.g46todoapi.exception.DataDuplicateException;
import se.lexicon.g46todoapi.exception.DataNotFoundException;
import se.lexicon.g46todoapi.repository.UserRepository;
import static org.junit.jupiter.api.Assertions.*;


import java.util.HashSet;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {
    private static final String EMAIL = "test.email@host.com";

    @Autowired
    private UserService testObject;
    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    void setUp() {
        testObject.register(UserDTOForm.builder()
                        .email(EMAIL)
                        .password("TestPW")
                        .roles(new HashSet<>())
                .build());
    }


    @Test
    void registerTest() {
        assertThrows(DataDuplicateException.class, () -> {
            testObject.register(UserDTOForm.builder()
                            .email(EMAIL)
                            .password("TestPW")
                            .roles(new HashSet<>())
                    .build());
        });

        assertThrows(IllegalArgumentException.class, () -> {
            testObject.register(null);
        });

        assertThrows(DataNotFoundException.class, () -> {
            HashSet<RoleDTOForm> roles = new HashSet<>();
            roles.add(RoleDTOForm.builder()
                        .id(999L)
                        .name("Test Role")
                    .build());
            testObject.register(UserDTOForm.builder()
                        .email("another.user@host.com")
                        .password("TestPW")
                        .roles(roles)
                    .build());
        });
    }

    @Test
    void getByEmailTest() {
        UserDTOView result = testObject.getByEmail(EMAIL);
        assertNotNull(result);
        assertThrows(DataNotFoundException.class, () -> {
            testObject.getByEmail("some.other@email.com");
        });
    }

    @Test
    void disableByEmailTest() {
        testObject.disableByEmail(EMAIL);
        UserDTOView byEmail = testObject.getByEmail(EMAIL);
        assertTrue(byEmail.isExpired());
    }

    @Test
    void enableByEmailTest() {
        testObject.enableByEmail(EMAIL);
        UserDTOView byEmail = testObject.getByEmail(EMAIL);
        assertFalse(byEmail.isExpired());
    }

}
