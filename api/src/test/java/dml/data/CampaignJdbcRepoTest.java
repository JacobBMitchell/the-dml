package dml.data;

import dml.models.Campaign;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CampaignJdbcRepoTest {

    @Autowired
    CampaignRepo repo;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindById() {
        Campaign campaign = repo.findById(1);
        assertEquals(1, campaign.getCampaignId());
        assertEquals(1, campaign.getDmId());
        assertEquals(4, campaign.getPlayerIds().size());
    }

    @Test
    void shouldNotFindNonExistingCampaign() {
        assertNull(repo.findById(100));
    }

    @Test
    void shouldFindByDmId() {
        List<Campaign> campaigns = repo.findByUserId(1);
        assertEquals(1, campaigns.size());
        Campaign campaign = campaigns.get(0);
        assertEquals(1, campaign.getDmId());
        assertEquals(4, campaign.getPlayerIds().size());
    }

    @Test
    void shouldNotFindNonExistingDmId() {
        assertEquals(0, repo.findByUserId(100).size());
    }

    @Test
    void shouldAdd() {
        Campaign campaign = new Campaign();
        campaign.setDmId(5);
        campaign = repo.add(campaign);
        assertEquals(3, campaign.getCampaignId());
    }

    @Test
    void shouldUpdateNotes() {
        String notes = "updated";
        Campaign campaign = new Campaign();
        campaign.setCampaignId(2);
        campaign.setDmNotes(notes);
        assertTrue(repo.updateNotes(campaign));
        assertEquals(notes, repo.findById(2).getDmNotes());
    }

    @Test
    void shouldNotUpdateNotFound() {
        Campaign campaign = new Campaign();
        campaign.setCampaignId(100);
        assertFalse(repo.updateNotes(campaign));
    }
}