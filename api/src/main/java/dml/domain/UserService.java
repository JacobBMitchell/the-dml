package dml.domain;

import dml.data.UserRepo;
import dml.models.AppUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    UserRepo repo;
    PasswordEncoder encoder;

    public UserService(UserRepo repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null){
            throw new UsernameNotFoundException(username + " not found");
        }
        AppUser user =  repo.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException(username + " not found");
        }
        return user;
    }

    public Result<AppUser> create(AppUser user){
        return null;
    }
}
