package dml.domain;

import dml.data.CampaignRepo;
import dml.data.PlayerCharacterRepo;
import dml.data.UserRepo;
import dml.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class PlayerCharacterServiceTest {

    @Autowired
    PlayerCharacterService service;

    @MockBean
    PlayerCharacterRepo charRepo;

    @MockBean
    UserRepo userRepo;

    @MockBean
    CampaignRepo campRepo;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findById() {
        //when requester looks at their character
        AppUser user = makeUser();
        PlayerCharacter character = makeCharacter();
        when(userRepo.findByUsername(user.getUsername())).thenReturn(user);
        when(charRepo.findById(character.getId())).thenReturn(character);
        Result<PlayerCharacter> result = service.findById(1, user.getUsername());

        //when dm look for campaign's character
        Set<String> dmRoles = new HashSet<>();
        dmRoles.add("DM");
        AppUser dm = makeUser();
        dm.setRoles(dmRoles);
        dm.setUserId(2);
        dm.setEmail("DM");
        when(userRepo.findByUsername(dm.getEmail())).thenReturn(dm);
        when(campRepo.findByUserId(dm.getUserId())).thenReturn(null);
    }

    @Test
    void findByUser() {
    }

    @Test
    void findByCampaign() {
    }

    @Test
    void addPC() {
    }

    private AppUser makeUser() {
        Set<String> roles = new HashSet<>();
        roles.add("Player");
        return new AppUser("Player", "password", roles, "Player", 1,
                "First", "Last");
    }

    private PlayerCharacter makeCharacter() {
        PlayerCharacter character = new PlayerCharacter();
        character.setId(1);
        character.setName("New Character");
        character.setUserId(1);
        character.setCampaignId(1);
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

    private Campaign makeCampaign() {
        List<Integer> playerIds = new ArrayList<>();

        return null;
    }
}