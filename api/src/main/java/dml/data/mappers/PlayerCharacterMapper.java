package dml.data.mappers;

import dml.models.DndClass;
import dml.models.PlayerCharacter;
import dml.models.Race;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerCharacterMapper implements RowMapper<PlayerCharacter> {

    @Override
    public PlayerCharacter mapRow(ResultSet resultSet, int i) throws SQLException {
        PlayerCharacter character = new PlayerCharacter();
        character.setId(resultSet.getInt("characterId"));
        character.setName(resultSet.getString("characterName"));
        character.setUserId(resultSet.getInt("userId"));
        character.setCampaignId(resultSet.getInt("campaignId"));
        character.setCurrentHealth(resultSet.getInt("currentHealth"));
        character.setMaxHealth(resultSet.getInt("maxHealth"));
        character.setDndClass(DndClass.readIndex(resultSet.getString("dndClass")));
        character.setRace(Race.readIndex(resultSet.getString("race")));
        character.setCharacterLevel(resultSet.getInt("characterLevel"));
        character.setArmorClass(resultSet.getInt("armorClass"));
        character.setSpeed(resultSet.getInt("speed"));
        character.setStr(resultSet.getInt("str"));
        character.setDex(resultSet.getInt("dex"));
        character.setCon(resultSet.getInt("con"));
        character.setIntel(resultSet.getInt("intel"));
        character.setWis(resultSet.getInt("wis"));
        character.setCha(resultSet.getInt("cha"));
        character.setSavingStr(resultSet.getBoolean("savingStr"));
        character.setSavingDex(resultSet.getBoolean("savingDex"));
        character.setSavingCon(resultSet.getBoolean("savingCon"));
        character.setSavingIntel(resultSet.getBoolean("savingIntel"));
        character.setSavingWis(resultSet.getBoolean("savingWis"));
        character.setSavingCha(resultSet.getBoolean("savingCha"));
        character.setAcrobatics(resultSet.getBoolean("acrobatics"));
        character.setAnimalHandling(resultSet.getBoolean("animalHandling"));
        character.setArcana(resultSet.getBoolean("arcana"));
        character.setAthletics(resultSet.getBoolean("athletics"));
        character.setDeception(resultSet.getBoolean("deception"));
        character.setHistory(resultSet.getBoolean("history"));
        character.setInsight(resultSet.getBoolean("insight"));
        character.setIntimidation(resultSet.getBoolean("intimidation"));
        character.setInvestigation(resultSet.getBoolean("investigation"));
        character.setMedicine(resultSet.getBoolean("medicine"));
        character.setNature(resultSet.getBoolean("nature"));
        character.setPerception(resultSet.getBoolean("perception"));
        character.setPerformance(resultSet.getBoolean("performance"));
        character.setPersuasion(resultSet.getBoolean("persuasion"));
        character.setReligion(resultSet.getBoolean("religion"));
        character.setSleightOfHand(resultSet.getBoolean("sleightOfHand"));
        character.setStealth(resultSet.getBoolean("stealth"));
        character.setSurvival(resultSet.getBoolean("survival"));
        return character;
    }
}
