package dml.domain;

import dml.data.CampaignRepo;
import dml.models.Campaign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class CampaignService {

    @Autowired
    CampaignRepo repo;

    public Result<Campaign> getCampaignById(Integer id, Principal user){
        Result<Campaign> result = new Result<>();

        return result;
    }

    public Result<Campaign> addCampaign(Campaign campaign, Principal user){
        Result<Campaign> result = new Result<>();

        return result;
    }

    public Result<Campaign> update(Campaign campaign, Principal user){
        Result<Campaign> result = new Result<>();

        return result;
    }

    public Result<Boolean> deleteById(Integer id, Principal user){
        Result<Boolean> result = new Result<>();

        return result;
    }
}
