package dml.data;

import dml.models.AppUser;
import org.springframework.stereotype.Repository;

@Repository
public class UserDbRepo implements UserRepo{

    @Override
    public AppUser findByUsername(String username) {
        return null;
    }
}
