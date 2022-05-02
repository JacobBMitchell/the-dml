package dml.data;

import dml.models.Campaign;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}