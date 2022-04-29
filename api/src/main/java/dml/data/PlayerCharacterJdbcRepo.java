package dml.data;

import dml.data.mappers.PlayerCharacterMapper;
import dml.models.PlayerCharacter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PlayerCharacterJdbcRepo implements PlayerCharacterRepo{

    private final JdbcTemplate jdbcTemplate;

    public PlayerCharacterJdbcRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public PlayerCharacter findById(int characterId) {

        final String sql = "select * from characters where characterId = ?;";

        PlayerCharacter character = jdbcTemplate.query(sql, new PlayerCharacterMapper(), characterId).stream()
                .findFirst().orElse(null);

        return character;
    }

    @Override
    public List<PlayerCharacter> findByPlayer(int userId) {

        final String sql = "select * from characters where userId = ?;";

        List<PlayerCharacter> characters = jdbcTemplate.query(sql, new PlayerCharacterMapper(), userId)
                .stream().collect(Collectors.toList());

        return characters;
    }

    @Override
    public List<PlayerCharacter> findByCampaign(int campaignId) {
        //TODO: complete
        return null;
    }

    @Override
    public PlayerCharacter add(PlayerCharacter character) {

        final String sql = "insert into character (characterName, userId, campaignId, currentHealth, maxHealth, " +
                "dndClass, race, characterLevel, armorClass, gold, speed, str, dex, con, intel, wis, cha, " +
                "savingStr, savingDex, savingCon, savingIntel, savingWis, savingCha, acrobatics, animalHandling, " +
                "arcana, athletics, deception, history, insight, intimidation, investigation, medicine, nature, " +
                "perception, performance, persuasion, religion, sleightOfHand, stealth, survival) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //TODO: ps.set(character)
            return ps;
        }, keyHolder);

        //TODO: rest of add
        return null;
    }

    @Override
    public boolean update(PlayerCharacter character) {
        return false;
    }

    @Override
    public boolean deleteById(int characterId) {
        return false;
    }
}
