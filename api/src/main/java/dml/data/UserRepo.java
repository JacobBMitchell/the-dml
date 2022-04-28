package dml.data;

import dml.models.AppUser;

public interface UserRepo {
    AppUser findByUsername(String username);

    AppUser create(String username, String password, String email);
}
