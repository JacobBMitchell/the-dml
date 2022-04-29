package dml.data;

import dml.data.mappers.PlayerCharacterMapper;
import dml.models.PlayerCharacter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
        return null;
    }

    @Override
    public PlayerCharacter add(PlayerCharacter character) {
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
