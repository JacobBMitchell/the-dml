package dml.data;

import dml.models.AppUser;
import org.springframework.stereotype.Repository;

@Repository
public class UserDbRepo implements UserRepo{

    @Override
    public AppUser findByUsername(String username) {
        return null;
    }

    @Override
    public AppUser create(String username, String password, String email) {
        return null;
    }
}
