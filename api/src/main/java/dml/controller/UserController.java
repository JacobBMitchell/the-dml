package dml.controller;

import dml.App;
import dml.domain.Result;
import dml.domain.ResultType;
import dml.domain.UserService;
import dml.models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/{username}")
    public ResponseEntity loadUserByUsername(@PathVariable String username){
        return ResponseEntity.ok(service.loadUserByUsername(username));
    }

    @PostMapping("/register")
    public ResponseEntity createNewUser(@RequestBody AppUser user){
        //HashSet<String> roles = new HashSet<>(newUser.get("roles"));
        System.out.println("here");
//        AppUser user = new AppUser((String) newUser.get("username"), (String) newUser.get("password"), (Set<String>) newUser.get("roles"),
//                (String) newUser.get("email"), (Integer) newUser.get("userId"), (String) newUser.get("firstName"), (String) newUser.get("lastName"));
        Result result = service.create(user);
        if (result.getType() == ResultType.INVALID){
            return new ResponseEntity<>(result.getMessages(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }
}
