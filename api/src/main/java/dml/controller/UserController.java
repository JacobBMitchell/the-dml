package dml.controller;

import dml.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
}