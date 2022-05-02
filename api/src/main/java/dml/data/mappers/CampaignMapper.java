package dml.data.mappers;

import dml.models.Campaign;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CampaignMapper implements RowMapper<Campaign> {

    List<Integer> playerIds;

    public CampaignMapper() {
        playerIds = null;
    }

    public CampaignMapper(List<Integer> playerIds) {
        this.playerIds = playerIds;
    }

    @Override
    public Campaign mapRow(ResultSet resultSet, int i) throws SQLException {
        Campaign campaign = new Campaign();
        campaign.setCampaignId(resultSet.getInt("campaignId"));
        campaign.setDmId(resultSet.getInt("dmId"));
        campaign.setDmNotes(resultSet.getString("notes"));
        campaign.setPlayerIds(playerIds);
        return campaign;
    }
}
