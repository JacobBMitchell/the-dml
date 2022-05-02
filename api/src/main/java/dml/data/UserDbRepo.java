package dml.data;

import dml.data.mappers.UserMapper;
import dml.models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class UserDbRepo implements UserRepo{

    @Autowired
    JdbcTemplate template;

    @Override
    public AppUser findByUsername(String username) {
        String sql = "select * from users where email = ?;";
        return template.query(sql,
                        new UserMapper(findRolesByUsername(username)),
                        username)
                .stream().findAny().orElse(null);
    }

    @Override
    public AppUser findById(Integer id) {

        final String sql = "select * from users where userId = ?;";


        return template.query(sql,
                        new UserMapper(findRolesById(id)),
                        id)
                .stream().findAny().orElse(null);
    }

    @Override
    public AppUser create(AppUser user) {

        final String sql = "insert into users (firstName, lastName, email, password_hash) values (?, ?, ?, ?);";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = template.update(connection -> {
           PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           ps.setString(1, user.getFirstName());
           ps.setString(2, user.getLastName());
           ps.setString(3, user.getEmail());
           ps.setString(4, user.getPassword());
           return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        user.setUserId(keyHolder.getKey().intValue());

        updateRoles(user);

        return user;
    }

    private void updateRoles(AppUser user) {
        
    }

    private Set<String> findRolesByUsername(String username) {
        String sql = "select roleName from users u " +
                "inner join user_role ur on ur.userId = u.userId " +
                "inner join roles r on ur.roleId = r.roleId " +
                "where email = ?;";
        return template.query(sql,
                        (rowData, RowNum) -> rowData.getString("roleName"),
                        username)
                .stream().collect(Collectors.toSet());
    }

    private Set<String> findRolesById(int id) {
        String sql = "select roleName from users u " +
                "inner join user_role ur on ur.userId = u.userId " +
                "inner join roles r on ur.roleId = r.roleId " +
                "where u.userId = ?;";
        return template.query(sql,
                        (rowData, RowNum) -> rowData.getString("roleName"),
                        id)
                .stream().collect(Collectors.toSet());
    }
}
