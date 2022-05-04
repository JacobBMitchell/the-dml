package dml.domain;

import dml.data.CampaignRepo;
import dml.data.UserRepo;
import dml.models.AppUser;
import dml.models.Campaign;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CampaignServiceTest {

    @Autowired
    CampaignService service;

    @MockBean
    UserRepo userRepo;

    @MockBean
    CampaignRepo campRepo;

    @Test
    void getCampaignById() {
        //when dm look for campaign's character
        Set<String> dmRoles = new HashSet<>();
        dmRoles.add("DM");
        AppUser dm = makeUser();
        dm.setRoles(dmRoles);
        dm.setUserId(2);
        dm.setEmail("DM");
        when(userRepo.findByUsername(dm.getEmail())).thenReturn(dm);
        when(campRepo.findById(1)).thenReturn(makeCampaign());
        Result<Campaign> result = service.getCampaignById(1, dm.getEmail());
        assertTrue(result.isSuccess());

        //when requester is admin
        Set<String> adminRoles = new HashSet<>();
        adminRoles.add("ADMIN");
        AppUser admin = makeUser();
        admin.setRoles(adminRoles);
        admin.setUserId(3);
        admin.setEmail("admin");
        when(userRepo.findByUsername(admin.getEmail())).thenReturn(admin);
        result = service.getCampaignById(1, admin.getEmail());
        assertTrue(result.isSuccess());
    }

    @Test
    void getCampaignsByUser() {
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
        when(campRepo.findByUserId(2)).thenReturn(campaigns);
        Result<List<Campaign>> result = service.getCampaignsByUser(dm.getEmail(), dm.getEmail());
        assertTrue(result.isSuccess());

        //when requester is admin
        Set<String> adminRoles = new HashSet<>();
        adminRoles.add("ADMIN");
        AppUser admin = makeUser();
        admin.setRoles(adminRoles);
        admin.setUserId(3);
        admin.setEmail("admin");
        when(userRepo.findByUsername(admin.getEmail())).thenReturn(admin);
        result = service.getCampaignsByUser(dm.getEmail(), admin.getEmail());
        assertTrue(result.isSuccess());
    }

    @Test
    void addCampaign() {
        Set<String> dmRoles = new HashSet<>();
        dmRoles.add("DM");
        AppUser dm = makeUser();
        dm.setRoles(dmRoles);
        dm.setUserId(2);
        dm.setEmail("DM");
        Campaign campaign = makeCampaign();
        campaign.setCampaignId(null);
        when(userRepo.findByUsername(dm.getUsername())).thenReturn(dm);
        when(campRepo.add(makeCampaign())).thenReturn(campaign);
        Result<Campaign> result = service.addCampaign(campaign,dm.getUsername());
        assertTrue(result.isSuccess());
    }

    @Test
    void update() {
        Set<String> dmRoles = new HashSet<>();
        dmRoles.add("DM");
        AppUser dm = makeUser();
        dm.setRoles(dmRoles);
        dm.setUserId(2);
        dm.setEmail("DM");
        Campaign campaign = makeCampaign();
        campaign.setCampaignId(1);
        when(userRepo.findByUsername(dm.getUsername())).thenReturn(dm);
        when(campRepo.findById(campaign.getCampaignId())).thenReturn(campaign);
        when(campRepo.add(makeCampaign())).thenReturn(campaign);
        Result<Campaign> result = service.update(campaign,dm.getUsername());
        assertTrue(result.isSuccess());
    }

    @Test
    void deleteById() {
    }

    private AppUser makeUser() {
        Set<String> roles = new HashSet<>();
        roles.add("Player");
        return new AppUser("Player", "password", roles, "Player", 1,
                "First", "Last");
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