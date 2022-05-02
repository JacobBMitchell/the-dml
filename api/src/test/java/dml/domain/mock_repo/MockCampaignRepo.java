package dml.domain.mock_repo;

import dml.data.CampaignRepo;
import dml.models.Campaign;

import java.util.List;

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
