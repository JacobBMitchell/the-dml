package dml.controller;

import dml.domain.PlayerCharacterService;
import dml.domain.Result;
import dml.models.PlayerCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/character")
public class CharacterController {

    @Autowired
    PlayerCharacterService service;

    @GetMapping("/{id}")
    public ResponseEntity getCharacterById(@PathVariable Integer id, Principal user){
        return ResponseEntity.ok(service.findById(id, user.getName()));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity getCharactersByUserId(@PathVariable Integer id, Principal user){
        return ResponseEntity.ok(service.findByUser(id, user.getName()));
    }

    @GetMapping("/campaign/{id}")
    public ResponseEntity getCharactersByCampaignId(@PathVariable Integer id, Principal user){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping
    public ResponseEntity addCharacter(@RequestBody PlayerCharacter character, Principal user){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping("{id}")
    public ResponseEntity updateCharacter(@RequestBody PlayerCharacter character, @PathVariable Integer id, Principal user){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteCharacter(@PathVariable Integer id, Principal user){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
