package dml.domain;

import dml.data.CampaignRepo;
import dml.data.PlayerCharacterRepo;
import dml.data.UserRepo;
import dml.models.AppUser;
import dml.models.PlayerCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PlayerCharacterService {

    @Autowired
    private PlayerCharacterRepo repo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CampaignRepo campRepo;


    //TODO: ADD USER VALIDATION TO CHECK A USER CAN ACCESS THIS DATA

    public Result<PlayerCharacter> findById(Integer id, String username) {
        Result<PlayerCharacter> result = new Result<>();

        AppUser requester = userRepo.findByUsername(username);

        if (requester == null || requester.getRoles().isEmpty()){
            result.addMessage("Need to login", ResultType.INVALID);
            return result;
        }

        if (id == null || id <= 0){
            result.addMessage("Needs valid id", ResultType.INVALID);
            return result;
        }

        PlayerCharacter character = repo.findById(id);

        if (character == null){
            result.addMessage("Character not found", ResultType.NOT_FOUND);
            return result;
        }

        if (requester.getUserId().equals(character.getUserId()) ||
                (requester.getRoles().contains("DM") &&
                        campRepo.findByUserId(requester.getUserId()).stream().anyMatch(a -> a.getPlayerIds().contains(character.getUserId())) ) ||
            requester.getRoles().contains("ADMIN")){
            if (result.isSuccess()){
                result.setPayload(character);
            }
        }
        else{
            result.addMessage("ACCESS DENIED", ResultType.INVALID);
        }


        return result;
    }

    public Result<List<PlayerCharacter>> findByUser(Integer userId , String username){
        Result<List<PlayerCharacter>> result = new Result<>();

        AppUser requester = userRepo.findByUsername(username);

        if (requester == null || requester.getRoles().isEmpty()){
            result.addMessage("Need to login", ResultType.INVALID);
            return result;
        }

        if (userId == null || userId <= 0){
            result.addMessage("Needs valid id", ResultType.INVALID);
            return result;
        }

        if (!userId.equals(requester.getUserId()) || !requester.getRoles().contains("ADMIN")){
            result.addMessage("You cannot request to see this users details", ResultType.INVALID);
            return result;
        }

        List<PlayerCharacter> characters = repo.findByPlayer(userId);


        if (characters == null){
            result.addMessage("Character not found", ResultType.NOT_FOUND);
        }
        if (result.isSuccess()){
            result.setPayload(characters);
        }
        return result;
    }

    public Result<List<PlayerCharacter>> findByCampaign(Integer campaignId , String username){
        Result<List<PlayerCharacter>> result = new Result<>();

        AppUser requester = userRepo.findByUsername(username);

        if (requester == null || requester.getRoles().isEmpty()){
            result.addMessage("Need to login", ResultType.INVALID);
            return result;
        }

        if (campaignId == null || campaignId <= 0){
            result.addMessage("Needs valid id", ResultType.INVALID);
            return result;
        }

        if (!requester.getRoles().contains("DM") || !requester.getRoles().contains("ADMIN")){
            result.addMessage("Only DM and Admin Access", ResultType.INVALID);
        }

        List<PlayerCharacter> characters = repo.findByCampaign(campaignId);

        if (!campRepo.findById(campaignId).getUserId().equals(requester.getUserId())){
            result.addMessage("You do not have access to this campaign",ResultType.INVALID);
        }

        if (characters == null){
            result.addMessage("Character not found", ResultType.NOT_FOUND);
        }

        if (result.isSuccess()){
            result.setPayload(characters);
        }
        return result;
    }


    public Result<PlayerCharacter> addPC(PlayerCharacter pc, String username){
        Result<PlayerCharacter> result = new Result<>();
        if (pc == null) {
            result.addMessage("Requires data object", ResultType.INVALID);
            return result;
        }

        AppUser requester = userRepo.findByUsername(username);

        if (requester == null || requester.getRoles().isEmpty()){
            result.addMessage("Need to login", ResultType.INVALID);
            return result;
        }

        result = validate(pc);

        if (result.isSuccess()){
            result.setPayload(repo.add(pc));
        }

        return result;
    }

    public Result<PlayerCharacter> update(PlayerCharacter pc, String username) {
        Result<PlayerCharacter> result = new Result<>();

        if (pc == null) {
            result.addMessage("Requires data object", ResultType.INVALID);
            return result;
        }

        AppUser requester = userRepo.findByUsername(username);

        if (requester == null || requester.getRoles().isEmpty()){
            result.addMessage("Need to login", ResultType.INVALID);
            return result;
        }

        if (!requester.getUserId().equals(pc.getUserId()) ||
                !(requester.getRoles().contains("DM") &&
                        campRepo.findById(pc.getCampaignId()) != null &&
                        campRepo.findById(pc.getCampaignId()).getUserId().equals(requester.getUserId())) ||
                !requester.getRoles().contains("ADMIN")){
            result.addMessage("You do not have access", ResultType.INVALID);
        }

        if(repo.findById(pc.getId())==null){
            result.addMessage("Could not find character", ResultType.NOT_FOUND);
        }

        result = validate(pc);


        if (result.isSuccess()){
            repo.update(pc);
            result.setPayload(pc);
        }

        return result;
    }

    public Result<Boolean> delete(Integer id, String username){
        Result<Boolean> result = new Result<>();

        AppUser requester = userRepo.findByUsername(username);

        if (requester == null || requester.getRoles().isEmpty()){
            result.addMessage("Need to login", ResultType.INVALID);
            return result;
        }

        if (!requester.getRoles().contains("ADMIN") || !requester.getUserId().equals(repo.findById(id).getUserId())){
            result.addMessage("You don't have permission to delete", ResultType.INVALID);
        }
        if (result.isSuccess()){
            result.setPayload(repo.deleteById(id));
        }
        return result;
    }

    private Result<PlayerCharacter> validate(PlayerCharacter pc) {
        Result<PlayerCharacter> result = new Result<>();
        if (pc.getName() == null || pc.getName().isEmpty()){
            result.addMessage("Name is required", ResultType.INVALID);
        }

        if (pc.getUserId() == null || userRepo.findById(pc.getUserId()) == null){
            result.addMessage("Invalid user",ResultType.INVALID);
        }

        if (pc.getCampaignId() != null && campRepo.findById(pc.getCampaignId()) == null){
            result.addMessage("That campaign does not exist", ResultType.INVALID);
        }

        if (pc.getCurrentHealth() == null || pc.getCurrentHealth() <0 || pc.getCurrentHealth()>pc.getMaxHealth()){
            result.addMessage("Valid current health required", ResultType.INVALID);
        }

        if (pc.getMaxHealth() == null || pc.getMaxHealth() <= 0){
            result.addMessage("Valid max health required", ResultType.INVALID);
        }

        //TODO: add way to convert from string to enum type to check for class and race

        if (pc.getCharacterLevel() == null || pc.getCharacterLevel() < 1 || pc.getCharacterLevel() >20){
            result.addMessage("Valid character level required", ResultType.INVALID);
        }

        if (pc.getArmorClass() == null || pc.getArmorClass() < 5 || pc.getArmorClass() >50){
            result.addMessage("Valid armor class required", ResultType.INVALID);
        }

        if (pc.getGold() == null || pc.getGold().compareTo(BigDecimal.ZERO) < 0){
            result.addMessage("Valid gold is required", ResultType.INVALID);
        }

        if (pc.getSpeed() == null || pc.getSpeed() <0){
            result.addMessage("Valid speed is required", ResultType.INVALID);
        }

        if (pc.getStr() == null || pc.getStr() < 0 || pc.getStr() >40){
            result.addMessage("Valid strength score is required", ResultType.INVALID);
        }

        if (pc.getDex() == null || pc.getDex() < 0 || pc.getDex() >40){
            result.addMessage("Valid dexterity score is required", ResultType.INVALID);
        }

        if (pc.getCon() == null || pc.getCon() < 0 || pc.getCon() >40){
            result.addMessage("Valid constitution score is required", ResultType.INVALID);
        }

        if (pc.getIntel() == null || pc.getIntel() < 0 || pc.getIntel() >40){
            result.addMessage("Valid intelligence score is required", ResultType.INVALID);
        }

        if (pc.getWis() == null || pc.getWis() < 0 || pc.getWis() >40){
            result.addMessage("Valid wisdom score is required", ResultType.INVALID);
        }

        if (pc.getCha() == null || pc.getCha() < 0 || pc.getCha() >40) {
            result.addMessage("Valid charisma score is required", ResultType.INVALID);
        }

        //TODO: Check to make sure spells and weapons are in db

        return result;
    }

}

//    List<String> spells;
//    List<String> weapons;
//    private DndClass dndClass; not null
//    private Race race; not null
