package dml.data;

import dml.data.mappers.CampaignMapper;
import dml.data.mappers.PlayerCharacterMapper;
import dml.data.mappers.UserMapper;
import dml.models.Campaign;
import dml.models.PlayerCharacter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
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
    public List<Campaign> findAll() {
        List<Campaign> campaigns = jdbcTemplate.query("select * from campaigns;", new CampaignMapper());

        for(Campaign campaign : campaigns){
            campaign.setPlayerIds(findPlayersByCampaignId(campaign.getCampaignId()));
        }

        return campaigns;
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

        final String sql = "select * from campaigns where dmId = ?;";

        List<Campaign> campaigns = jdbcTemplate.query(sql,
                        new CampaignMapper(),
                        id)
                .stream().collect(Collectors.toList());

        //add players for each campaign
        for(Campaign campaign : campaigns){
            campaign.setPlayerIds(findPlayersByCampaignId(campaign.getCampaignId()));
        }

        return campaigns;
    }

    @Override
    public Campaign add(Campaign campaign) {

        final String sql = "insert into campaigns (dmId, notes) values (?, ?);";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, campaign.getDmId());
            ps.setString(2, campaign.getDmNotes());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        campaign.setCampaignId(keyHolder.getKey().intValue());

        return campaign;
    }

    @Override
    public boolean updateNotes(Campaign campaign) {

        final String sql = "update campaigns set " +
                "notes = ? " +
                "where campaignId = ?;";

        return jdbcTemplate.update( sql, campaign.getDmNotes(), campaign.getCampaignId()) > 0;
    }

    private List<Integer> findPlayersByCampaignId(int id) {

        final String sql = "select * from characters where campaignId = ?;";
        return jdbcTemplate.query(sql, new PlayerCharacterMapper(), id)
                .stream().map(PlayerCharacter::getId).collect(Collectors.toList());
    }

}
