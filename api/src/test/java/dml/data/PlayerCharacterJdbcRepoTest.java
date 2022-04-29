package dml.data;

import dml.models.PlayerCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PlayerCharacterJdbcRepoTest {

    @Autowired
    PlayerCharacterRepo repo;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindById() {
        PlayerCharacter character = repo.findById(1);
        assertNotNull(character);
        assertEquals(1, character.getId());
    }

    @Test
    void shouldNotFindMissing() {
        PlayerCharacter character = repo.findById(10000);
        assertNull(character);
    }

    @Test
    void shouldFindByPlayer() {
        List<PlayerCharacter> characters = repo.findByPlayer(2);
        assertNotNull(characters);
        assertEquals(2, characters.size());
    }

    @Test
    void shouldNotFindByPlayer() {
        //Player exists but has not characters
        List<PlayerCharacter> characters = repo.findByPlayer(1);
        assertEquals(0, characters.size());

        //Player doesn't exist
        characters = repo.findByPlayer(100);
        assertEquals(0, characters.size());
    }
}