package dml.data;

import dml.models.DndClass;
import dml.models.PlayerCharacter;
import dml.models.Race;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
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

    @Test
    void shouldFindByCampaign() {
        List<PlayerCharacter> characters = repo.findByCampaign(1);
        assertNotNull(characters);
        assertEquals(4, characters.size());
    }

    @Test
    void shouldNotFindByCampaign() {
        //campaign doesn't exist
        List<PlayerCharacter> characters = repo.findByCampaign(100);
        assertEquals(0, characters.size());
    }

    @Test
    void shouldAddNewCharacter() {
        PlayerCharacter character = makeCharacter();
        PlayerCharacter result = repo.add(character);
        assertNotNull(result);
        assertEquals(6, result.getId());
    }

    private PlayerCharacter makeCharacter() {
        PlayerCharacter character = new PlayerCharacter();

        character.setName("New Character");
        character.setUserId(7);
        character.setCampaignId(2);
        character.setCurrentHealth(70);
        character.setMaxHealth(75);
        character.setDndClass(DndClass.BARD);
        character.setRace(Race.DRAGONBORN);
        character.setCharacterLevel(10);
        character.setArmorClass(16);
        character.setGold(new BigDecimal(5000));
        character.setSpeed(30);
        character.setStr(12);
        character.setDex(13);
        character.setCon(14);
        character.setIntel(12);
        character.setWis(12);
        character.setCha(16);
        character.setSavingStr(false);
        character.setSavingDex(true);
        character.setSavingCon(false);
        character.setSavingIntel(false);
        character.setSavingWis(false);
        character.setSavingCha(true);

        return character;
    }
}