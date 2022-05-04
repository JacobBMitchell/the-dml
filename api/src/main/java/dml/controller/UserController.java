package dml.controller;

import dml.domain.UserService;
import dml.models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping
    public ResponseEntity createNewUser(@RequestBody AppUser user){

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
