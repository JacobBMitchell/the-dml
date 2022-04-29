package dml.data;

import dml.models.PlayerCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PlayerCharacterJdbcRepoTest {

    @Autowired
    PlayerCharacterRepo repo;

    @Test
    void shouldFindById() {
        PlayerCharacter character = repo.findById(1);
        System.out.println(character);
        assertNotNull(character);
    }
}