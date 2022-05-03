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
        assertTrue(result.isSuccess());

        //when dm look for campaign's character
        Set<String> dmRoles = new HashSet<>();
        dmRoles.add("DM");
        AppUser dm = makeUser();
        dm.setRoles(dmRoles);
        dm.setUserId(2);
        dm.setEmail("DM");
        when(userRepo.findByUsername(dm.getEmail())).thenReturn(dm);
        List<Campaign> campaigns = new ArrayList<>();
        campaigns.add(makeCampaign());
        when(campRepo.findByUserId(dm.getUserId())).thenReturn(campaigns);
        result = service.findById(1, dm.getEmail());
        assertTrue(result.isSuccess());

        //when requester is admin
        Set<String> adminRoles = new HashSet<>();
        adminRoles.add("ADMIN");
        AppUser admin = makeUser();
        admin.setRoles(adminRoles);
        admin.setUserId(3);
        admin.setEmail("admin");
        when(userRepo.findByUsername(admin.getEmail())).thenReturn(admin);
        result = service.findById(1, admin.getEmail());
        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotFindById() {
        //when not logged in
        Result<PlayerCharacter> result = service.findById(1, null);
        assertFalse(result.isSuccess());
        assertEquals("Need to login", result.getMessages().get(0));

        //invalid id
        AppUser user = makeUser();
        when(userRepo.findByUsername(user.getUsername())).thenReturn(user);
        result = service.findById(0, user.getUsername());
        assertFalse(result.isSuccess());
        assertEquals("Needs valid id", result.getMessages().get(0));

        //character not found
        when(charRepo.findById(100)).thenReturn(null);
        result = service.findById(100, user.getUsername());
        assertFalse(result.isSuccess());
        assertEquals("Character not found", result.getMessages().get(0));

        //access denied
        PlayerCharacter character = makeCharacter();
        character.setUserId(2);
        when(charRepo.findById(2)).thenReturn(character);
        result = service.findById(2, user.getUsername());
        assertFalse(result.isSuccess());
        assertEquals("ACCESS DENIED", result.getMessages().get(0));

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
        playerIds.add(1);
        Campaign campaign = new Campaign();
        campaign.setCampaignId(1);
        campaign.setDmId(2);
        campaign.setPlayerIds(playerIds);
        return campaign;
    }
}