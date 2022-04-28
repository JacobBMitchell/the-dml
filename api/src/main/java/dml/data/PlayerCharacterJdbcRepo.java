package dml.data;

import dml.models.PlayerCharacter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlayerCharacterJdbcRepo implements PlayerCharacterRepo{

    private final JdbcTemplate jdbcTemplate;

    public PlayerCharacterJdbcRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public PlayerCharacter findById(int characterId) {
        return null;
    }

    @Override
    public List<PlayerCharacter> findByPlayer(int userId) {
        return null;
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
