package dml.data.mappers;

import dml.models.Campaign;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CampaignMapper implements RowMapper<Campaign> {

    @Override
    public Campaign mapRow(ResultSet resultSet, int i) throws SQLException {
        Campaign campaign = new Campaign();
        campaign.setUserId(resultSet.getInt("dmId"));
        campaign.setDmNotes(resultSet.getString("notes"));
        return campaign;
    }
}
