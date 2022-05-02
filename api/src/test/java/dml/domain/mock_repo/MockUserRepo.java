package dml.domain.mock_repo;

import dml.data.UserRepo;
import dml.models.AppUser;

public class MockUserRepo implements UserRepo {
    @Override
    public AppUser findByUsername(String username) {
        return null;
    }

    @Override
    public AppUser findById(Integer id) {
        return null;
    }

    @Override
    public AppUser create(String username, String password, String email) {
        return null;
    }
}
