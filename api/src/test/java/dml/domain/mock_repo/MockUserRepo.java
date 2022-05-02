package dml.domain.mock_repo;

import dml.data.UserRepo;
import dml.models.AppUser;
import org.springframework.boot.test.mock.mockito.MockBean;


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
    public AppUser create(AppUser user) {
        return null;
    }
}
