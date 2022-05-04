package dml.data;

import dml.data.mappers.PlayerCharacterMapper;
import dml.models.DndClass;
import dml.models.PlayerCharacter;
import dml.models.Race;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PlayerCharacterJdbcRepo implements PlayerCharacterRepo{

    private final JdbcTemplate jdbcTemplate;

    public PlayerCharacterJdbcRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PlayerCharacter> findAll() {
        return jdbcTemplate.query("select * from characters;", new PlayerCharacterMapper());
    }

    @Override
    public PlayerCharacter findById(int characterId) {

        final String sql = "select * from characters where characterId = ?;";

        PlayerCharacter character = jdbcTemplate.query(sql, new PlayerCharacterMapper(), characterId).stream()
                .findFirst().orElse(null);

        return character;
    }

    @Override
    public List<PlayerCharacter> findByPlayer(String username) {

        final String sql = "select c.* from characters c " +
                            "inner join users u on c.userId = u.userId " +
                            "where u.email = ?;";

        List<PlayerCharacter> characters = jdbcTemplate.query(sql, new PlayerCharacterMapper(), username)
                .stream().collect(Collectors.toList());

        return characters;
    }

    @Override
    public List<PlayerCharacter> findByCampaign(int campaignId) {

        final String sql = "select * from characters where campaignId = ?;";

        List<PlayerCharacter> characters = jdbcTemplate.query(sql, new PlayerCharacterMapper(), campaignId)
                .stream().collect(Collectors.toList());

        return characters;
    }

    @Override
    public PlayerCharacter add(PlayerCharacter character) {

        final String sql = "insert into characters (characterName, userId, campaignId, currentHealth, maxHealth, " +
                "dndClass, race, characterLevel, armorClass, gold, speed, str, dex, con, intel, wis, cha, " +
                "savingStr, savingDex, savingCon, savingIntel, savingWis, savingCha, acrobatics, animalHandling, " +
                "arcana, athletics, deception, history, insight, intimidation, investigation, medicine, nature, " +
                "perception, performance, persuasion, religion, sleightOfHand, stealth, survival) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, character.getName());
            ps.setInt(2, character.getUserId());
            ps.setInt(3, character.getCampaignId());
            ps.setInt(4, character.getCurrentHealth());
            ps.setInt(5, character.getMaxHealth());
            ps.setString(6, character.getDndClass().index);
            ps.setString(7, character.getRace().index);
            ps.setInt(8, character.getCharacterLevel());
            ps.setInt(9, character.getArmorClass());
            ps.setInt(10, character.getGold().intValue());
            ps.setInt(11, character.getSpeed());
            ps.setInt(12, character.getStr());
            ps.setInt(13, character.getDex());
            ps.setInt(14, character.getCon());
            ps.setInt(15, character.getIntel());
            ps.setInt(16, character.getWis());
            ps.setInt(17, character.getCha());
            ps.setBoolean(18, character.isSavingStr());
            ps.setBoolean(19, character.isSavingDex());
            ps.setBoolean(20, character.isSavingCon());
            ps.setBoolean(21, character.isSavingIntel());
            ps.setBoolean(22, character.isSavingWis());
            ps.setBoolean(23, character.isSavingCha());
            if(character.getAcrobatics() == null) {
                ps.setNull(24, Types.BIT);
            } else {
                ps.setBoolean(24, character.getAcrobatics());
            }
            if(character.getAnimalHandling() == null) {
                ps.setNull(25, Types.BIT);
            } else {
                ps.setBoolean(25, character.getAnimalHandling());
            }
            if(character.getArcana() == null) {
                ps.setNull(26, Types.BIT);
            } else {
                ps.setBoolean(26, character.getArcana());
            }
            if (character.getAthletics() == null) {
                ps.setNull(27, Types.BIT);
            } else {
                ps.setBoolean(27, character.getAthletics());
            }
            if(character.getDeception() == null) {
                ps.setNull(28, Types.BIT);
            } else {
                ps.setBoolean(28, character.getDeception());
            }
            if(character.getHistory() == null) {
                ps.setNull(29, Types.BIT);
            } else {
                ps.setBoolean(29, character.getHistory());
            }
            if(character.getInsight() == null) {
                ps.setNull(30, Types.BIT);
            } else {
                ps.setBoolean(30, character.getInsight());
            }
            if(character.getIntimidation() == null) {
                ps.setNull(31, Types.BIT);
            } else {
                ps.setBoolean(31, character.getIntimidation());
            }
            if(character.getInvestigation() == null) {
                ps.setNull(32, Types.BIT);
            } else {
                ps.setBoolean(32, character.getInvestigation());
            }
            if(character.getMedicine() == null) {
                ps.setNull(33, Types.BIT);
            } else {
                ps.setBoolean(33, character.getMedicine());
            }
            if(character.getNature() == null) {
                ps.setNull(34, Types.BIT);
            } else {
                ps.setBoolean(34, character.getNature());
            }
            if(character.getPerception() == null) {
                ps.setNull(35, Types.BIT);
            } else {
                ps.setBoolean(35, character.getPerception());
            }
            if(character.getPerformance() == null) {
                ps.setNull(36, Types.BIT);
            } else {
                ps.setBoolean(36, character.getPerformance());
            }
            if(character.getPersuasion() == null) {
                ps.setNull(37, Types.BIT);
            } else {
                ps.setBoolean(37, character.getPersuasion());
            }
            if(character.getReligion() == null) {
                ps.setNull(38, Types.BIT);
            } else {
                ps.setBoolean(38, character.getReligion());
            }
            if(character.getSleightOfHand() == null) {
                ps.setNull(39, Types.BIT);
            } else {
                ps.setBoolean(39, character.getSleightOfHand());
            }
            if(character.getStealth() == null) {
                ps.setNull(40, Types.BIT);
            } else {
                ps.setBoolean(40, character.getStealth());
            }
            if(character.getSurvival() == null) {
                ps.setNull(41, Types.BIT);
            } else {
                ps.setBoolean(41, character.getSurvival());
            }
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        character.setId(keyHolder.getKey().intValue());
        return character;
    }

    @Override
    public boolean update(PlayerCharacter character) {
        
        final String sql = "update characters set " +
                "characterName = ?, " +
                "userId = ?, " +
                "campaignId = ?, " +
                "currentHealth = ?, " +
                "maxHealth = ?, " +
                "dndClass = ?, " +
                "race = ?, " +
                "characterLevel = ?, " +
                "armorClass = ?, " +
                "gold = ?, " +
                "speed = ?, " +
                "str = ?, " +
                "dex = ?, " +
                "con = ?, " +
                "intel = ?, " +
                "wis = ?, " +
                "cha = ?, " +
                "savingStr = ?, " +
                "savingDex = ?, " +
                "savingCon = ?, " +
                "savingIntel = ?, " +
                "savingWis = ?, " +
                "savingCha = ?, " +
                "acrobatics = ?, " +
                "animalHandling = ?, " +
                "arcana = ?, " +
                "athletics = ?, " +
                "deception = ?, " +
                "history = ?, " +
                "insight = ?, " +
                "intimidation = ?, " +
                "investigation = ?, " +
                "medicine = ?, " +
                "nature = ?, " +
                "perception = ?, " +
                "performance = ?, " +
                "persuasion = ?, " +
                "religion = ?, " +
                "sleightOfHand = ?, " +
                "stealth = ?, " +
                "survival = ? " +
                "where characterId = ?;";

        return jdbcTemplate.update(sql,
                character.getName(),
                character.getUserId(),
                character.getCampaignId(),
                character.getCurrentHealth(),
                character.getMaxHealth(),
                character.getDndClass().index,
                character.getRace().index,
                character.getCharacterLevel(),
                character.getArmorClass(),
                character.getGold(),
                character.getSpeed(),
                character.getStr(),
                character.getDex(),
                character.getCon(),
                character.getIntel(),
                character.getWis(),
                character.getCha(),
                character.isSavingStr(),
                character.isSavingDex(),
                character.isSavingCon(),
                character.isSavingIntel(),
                character.isSavingWis(),
                character.isSavingCha(),
                character.getAcrobatics(),
                character.getAnimalHandling(),
                character.getArcana(),
                character.getAthletics(),
                character.getDeception(),
                character.getHistory(),
                character.getInsight(),
                character.getIntimidation(),
                character.getInvestigation(),
                character.getMedicine(),
                character.getNature(),
                character.getPerception(),
                character.getPerformance(),
                character.getPersuasion(),
                character.getReligion(),
                character.getSleightOfHand(),
                character.getStealth(),
                character.getSurvival(),
                character.getId()) > 0;
    }

    @Override
    public boolean deleteById(int characterId) {
        return jdbcTemplate.update("delete from characters where characterId = ?;", characterId) > 0;
    }
}
