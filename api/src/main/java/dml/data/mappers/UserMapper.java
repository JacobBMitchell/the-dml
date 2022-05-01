package dml.data.mappers;

import dml.models.AppUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class UserMapper implements RowMapper<AppUser> {
    Set<String> roles;

    public UserMapper(Set<String> roles) {
        this.roles = roles;
    }

    @Override
    public AppUser mapRow(ResultSet resultSet, int i) throws SQLException {
        AppUser toBuild = new AppUser(resultSet.getString("username"),
                resultSet.getString("password"),
                roles,
                resultSet.getString("email"),
                resultSet.getInt("userId"));
        return toBuild;
    }
}
