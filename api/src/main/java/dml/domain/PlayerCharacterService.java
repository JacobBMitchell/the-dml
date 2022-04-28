package dml.domain;

import dml.data.PlayerCharacterRepo;
import dml.models.PlayerCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerCharacterService {

    @Autowired
    PlayerCharacterRepo repo;

    public Result<PlayerCharacter> findById(Integer id) {
        Result<PlayerCharacter> result = new Result<>();
        if (id == null || id <= 0){
            result.addMessage("Needs valid id", ResultType.INVALID);
            return result;
        }
        PlayerCharacter character = repo.findById(id);
        if (character == null){
            result.addMessage("Character not found", ResultType.NOT_FOUND);
        }
        result.setPayload(character);
        return result;
    }

    public Result<List<PlayerCharacter>> findByUser(Integer userId){
        Result<List<PlayerCharacter>> result = new Result<>();

        if (userId == null || userId <= 0){
            result.addMessage("Needs valid id", ResultType.INVALID);
            return result;
        }
        PlayerCharacter character = repo.findById(userId);
        if (character == null){
            result.addMessage("Character not found", ResultType.NOT_FOUND);
        }

        return result;
    }

    public Result<List<PlayerCharacter>> findByCampaign(Integer campaignId){
        Result<List<PlayerCharacter>> result = new Result<>();

        if (campaignId == null || campaignId <= 0){
            result.addMessage("Needs valid id", ResultType.INVALID);
            return result;
        }
        PlayerCharacter character = repo.findById(campaignId);
        if (character == null){
            result.addMessage("Character not found", ResultType.NOT_FOUND);
        }

        return result;
    }

}
