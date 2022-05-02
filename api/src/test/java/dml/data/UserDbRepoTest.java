package dml.data;

import dml.models.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDbRepoTest {

    @Autowired
    UserRepo repo;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindByUsername() {
        AppUser user = repo.findByUsername("matthew@mercer.com");
        assertNotNull(user);
        assertEquals(1, user.getUserId());
    }

    @Test
    void shouldNotFindNonExistingUsername() {
        AppUser user = repo.findByUsername("fake@email.com");
        assertNull(user);
    }

    @Test
    void shouldFindById() {
        AppUser user = repo.findById(1);
        assertNotNull(user);
        assertEquals("matthew@mercer.com", user.getEmail());
    }

    @Test
    void shouldNotFindNonExistingId() {
        AppUser user = repo.findById(100);
        assertNull(user);
    }
}