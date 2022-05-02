package dml.data;

import dml.models.Campaign;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CampaignJdbcRepo implements CampaignRepo{
    @Override
    public Campaign findById(Integer id) {
        return null;
    }

    @Override
    public List<Campaign> findByUserId(Integer id) {
        return null;
    }
}
