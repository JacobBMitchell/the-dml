package dml.domain.mock_repo;

import dml.data.CampaignRepo;
import dml.models.Campaign;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@MockBean
public class MockCampaignRepo implements CampaignRepo {
    @Override
    public Campaign findById(Integer id) {
        return null;
    }

    @Override
    public List<Campaign> findByUserId(Integer id) {
        return null;
    }
}
