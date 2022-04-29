package dml.domain;

import dml.data.PlayerCharacterRepo;
import dml.models.DndClass;
import dml.models.PlayerCharacter;
import dml.models.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PlayerCharacterService {

    @Autowired
    PlayerCharacterRepo repo;

    //TODO: ADD OTHER REPOS TO CHECK FOR USER AND CAMPAIGN IDS
    //TODO: ADD USER VALIDATION TO CHECK A USER CAN ACCESS THIS DATA
    
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
        List<PlayerCharacter> characters = repo.findByPlayer(userId);
        if (characters == null){
            result.addMessage("Character not found", ResultType.NOT_FOUND);
        }
        result.setPayload(characters);
        return result;
    }

    public Result<List<PlayerCharacter>> findByCampaign(Integer campaignId){
        Result<List<PlayerCharacter>> result = new Result<>();

        if (campaignId == null || campaignId <= 0){
            result.addMessage("Needs valid id", ResultType.INVALID);
            return result;
        }
        List<PlayerCharacter> characters = repo.findByCampaign(campaignId);
        if (characters == null){
            result.addMessage("Character not found", ResultType.NOT_FOUND);
        }
        result.setPayload(characters);
        return result;
    }

    //TODO: Fill in the rest of the service layer components add update and delete

    public Result<PlayerCharacter> addPC(PlayerCharacter pc){
        Result<PlayerCharacter> result = new Result<>();
        if (pc == null) {
            result.addMessage("Requires data object", ResultType.INVALID);
            return result;
        }
        result = validate(pc);



        return result;
    }

    private Result<PlayerCharacter> validate(PlayerCharacter pc) {
        Result<PlayerCharacter> result = new Result<>();
        if (pc.getName() == null || pc.getName().isEmpty()){
            result.addMessage("Name is required", ResultType.INVALID);
        }

        //TODO: import user and dm repos to check if userId and CampaignId are valid

        if (pc.getCurrentHealth() == null || pc.getCurrentHealth() <0 || pc.getCurrentHealth()>pc.getMaxHealth()){
            result.addMessage("Valid current health required", ResultType.INVALID);
        }

        if (pc.getMaxHealth() == null || pc.getMaxHealth() <= 0){
            result.addMessage("Valid max health required", ResultType.INVALID);
        }


        return result;
    }

}

//    private DndClass dndClass;
//    private Race race;
//    private Integer characterLevel;
//    private Integer armorClass;
//    private BigDecimal gold;
//    private Integer speed;
//    private Integer str;
//    private Integer dex;
//    private Integer con;
//    private Integer intel;
//    private Integer wis;
//    private Integer cha;
//    boolean savingStr;
//    boolean savingDex;
//    boolean savingCon;
//    boolean savingIntel;
//    boolean savingWis;
//    boolean savingCha;
//    Boolean acrobatics; //be careful to never accidentally set this to true or false if null
//    Boolean animalHandling;
//    Boolean arcana;
//    Boolean athletics;
//    Boolean deception;
//    Boolean history;
//    Boolean insight;
//    Boolean intimidation;
//    Boolean investigation;
//    Boolean medicine;
//    Boolean nature;
//    Boolean perception;
//    Boolean performance;
//    Boolean persuasion;
//    Boolean religion;
//    Boolean sleightOfHand;
//    Boolean stealth;
//    Boolean survival;
//    List<String> spells;
//    List<String> weapons;
//    private Integer userId;
//    private Integer campaignId;
