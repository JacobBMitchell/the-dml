package dml.domain;

import dml.data.CampaignRepo;
import dml.data.UserRepo;
import dml.models.AppUser;
import dml.models.Campaign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignService {

    @Autowired
    CampaignRepo repo;
    @Autowired
    UserRepo userRepo;

    public Result<Campaign> getCampaignById(Integer id, String username){
        Result<Campaign> result = new Result<>();

        AppUser requester = userRepo.findByUsername(username);

        if (requester == null || requester.getRoles().isEmpty()){
            result.addMessage("Need to login", ResultType.INVALID);
            return result;
        }

        Campaign campaign = repo.findById(id);
        if (!campaign.getUserId().equals(requester.getUserId())){
            result.addMessage("You cannot see this campaign", ResultType.INVALID);
        }

        return result;
    }

    public Result<List<Campaign>> getCampaignsByUser(Integer uId, String Username){
        Result<List<Campaign>> result = new Result<>();

        return result;
    }

    public Result<Campaign> addCampaign(Campaign campaign,  String username){
        Result<Campaign> result = new Result<>();

        return result;
    }

    public Result<Campaign> update(Campaign campaign,  String username){
        Result<Campaign> result = new Result<>();

        return result;
    }

    public Result<Boolean> deleteById(Integer id,  String username){
        Result<Boolean> result = new Result<>();

        return result;
    }
}
