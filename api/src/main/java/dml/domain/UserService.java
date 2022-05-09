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
        Result<AppUser> result = new Result<>();
        if (user == null){
            result.addMessage("Need user details", ResultType.INVALID);
            return result;
        }
        if (user.getEmail().isEmpty() || user.getRoles().isEmpty() || user.getFirstName().isEmpty() || user.getLastName().isEmpty()){
            result.addMessage("Missing credentails", ResultType.INVALID);
            return result;
        }

        if (repo.findByUsername(user.getEmail()) != null){
            result.addMessage("User already exists", ResultType.INVALID);
        }
        if (result.isSuccess()){
            result.setPayload(repo.create(user));
        }
        return result;
    }
}
