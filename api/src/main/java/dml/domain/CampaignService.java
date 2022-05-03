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

        if (!campaign.getDmId().equals(requester.getUserId()) || !requester.getRoles().contains("ADMIN")){
            result.addMessage("You cannot see this campaign", ResultType.INVALID);
        }

        if (result.isSuccess()){
            result.setPayload(campaign);
        }

        return result;
    }

    public Result<List<Campaign>> getCampaignsByUser(Integer uId, String username){
        Result<List<Campaign>> result = new Result<>();

        AppUser requester = userRepo.findByUsername(username);

        if (requester == null || requester.getRoles().isEmpty()){
            result.addMessage("Need to login", ResultType.INVALID);
            return result;
        }

        if (!requester.getUserId().equals(uId) || !requester.getRoles().contains("ADMIN")){
            result.addMessage("You do not have access to this data", ResultType.INVALID);
        }

        List<Campaign> campaigns = repo.findByUserId(uId);

        if (result.isSuccess()){
            result.setPayload(campaigns);
        }

        return result;
    }

    public Result<Campaign> addCampaign(Campaign campaign,  String username){
        AppUser requester = userRepo.findByUsername(username);
        Result<Campaign> result = validate(campaign, requester);

        if (requester == null || requester.getRoles().isEmpty()){
            result.addMessage("Need to login", ResultType.INVALID);
            return result;
        }
        if (result.isSuccess()){
            result.setPayload(repo.add(campaign));
        }

        return result;
    }


    public Result<Campaign> update(Campaign campaign,  String username){
        AppUser requester = userRepo.findByUsername(username);
        Result<Campaign> result = validate(campaign, requester);
        if (requester == null || requester.getRoles().isEmpty()){
            result.addMessage("Need to login", ResultType.INVALID);
            return result;
        }

        if (result.isSuccess()){
            repo.updateNotes(campaign);
        }


        return result;
    }

    public Result<Boolean> deleteById(Integer id,  String username){
        AppUser requester = userRepo.findByUsername(username);
        Result<Boolean> result = new Result<>();
        
        if (requester == null || requester.getRoles().isEmpty()){
            result.addMessage("Need to login", ResultType.INVALID);
            return result;
        }

        if (!requester.getUserId().equals(id) || !requester.getRoles().contains("ADMIN")){
            result.addMessage("You do not have permission for this action", ResultType.INVALID);
        }

        return result;
    }

    private Result<Campaign> validate(Campaign campaign, AppUser requester) {
        Result<Campaign> result = new Result<>();
        if (campaign == null){
            result.addMessage("Object Required", ResultType.INVALID);
            return result;
        }
        if (campaign.getDmId() == null || !requester.getUserId().equals(campaign.getDmId()) && !requester.getRoles().contains("ADMIN")){
            result.addMessage("You do not have access to this feature", ResultType.INVALID);
        }
        if (campaign.getPlayerIds() != null && campaign.getPlayerIds().stream().anyMatch(a -> userRepo.findById(a) == null)){
            result.addMessage("Invalid player",ResultType.INVALID);
        }
        if (campaign.getDmNotes() != null && campaign.getDmNotes().length() > 20000) {
            result.addMessage("Max character limit exceeded", ResultType.INVALID);
        }
        return result;
    }

}
