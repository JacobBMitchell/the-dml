package dml.data;

import dml.data.mappers.CampaignMapper;
import dml.data.mappers.PlayerCharacterMapper;
import dml.data.mappers.UserMapper;
import dml.models.Campaign;
import dml.models.PlayerCharacter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CampaignJdbcRepo implements CampaignRepo{

    private final JdbcTemplate jdbcTemplate;

    public CampaignJdbcRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Campaign findById(Integer id) {

        final String sql = "select * from campaigns where campaignId = ?;";

        return jdbcTemplate.query(sql,
                        new CampaignMapper(findPlayersByCampaignId(id)),
                        id)
                .stream().findFirst().orElse(null);
    }

    @Override
    public List<Campaign> findByUserId(Integer id) {
        return null;
    }

    private List<Integer> findPlayersByCampaignId(int id) {

        final String sql = "select * from characters where campaignId = ?;";
        return jdbcTemplate.query(sql, new PlayerCharacterMapper(), id)
                .stream().map(PlayerCharacter::getId).collect(Collectors.toList());
    }
}
