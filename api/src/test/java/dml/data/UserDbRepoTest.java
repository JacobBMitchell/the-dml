package dml.data;

import dml.models.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

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

    @Test
    void shouldAddUser() {
        Set<String> roles = new HashSet<>();
        roles.add("PLAYER");
        String email = "test@email.com";
        AppUser user = new AppUser(email, "password", roles, email, 0, "first", "last");

        user = repo.create(user);

        assertNotEquals(0, user.getUserId());
    }

    @Test
    void shouldUpdateUser() {
        String username = "matthew@mercer.com";
        AppUser user = repo.findByUsername(username);
        user.setFirstName("updated");
        Set<String> roles = user.getRoles();
        roles.add("PLAYER");
        user.setRoles(roles);
        repo.update(user);

        assertEquals("updated", repo.findByUsername(username).getFirstName());
    }

}