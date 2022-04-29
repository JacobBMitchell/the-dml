package dml.data;

import dml.models.Campaign;

import java.util.List;

public interface CampaignRepo {
    Campaign findById(Integer id);

    List<Campaign> findByUserId(Integer id);
}
