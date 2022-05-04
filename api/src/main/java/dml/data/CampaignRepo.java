package dml.data;

import dml.models.Campaign;

import java.util.List;

public interface CampaignRepo {

    List<Campaign> findAll();

    Campaign findById(Integer id);

    List<Campaign> findByUserId(Integer id);

    Campaign add(Campaign campaign);

    boolean updateNotes(Campaign campaign);
}
