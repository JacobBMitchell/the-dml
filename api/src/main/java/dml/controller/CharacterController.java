package dml.controller;

import dml.domain.PlayerCharacterService;
import dml.domain.Result;
import dml.domain.ResultType;
import dml.models.PlayerCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/character")
public class CharacterController {

    @Autowired
    PlayerCharacterService service;

    @GetMapping("/{id}")
    public ResponseEntity getCharacterById(@PathVariable Integer id, Principal user){
        Result<PlayerCharacter> result = service.findById(id, user.getName());
        if (result.isSuccess()){
            return ResponseEntity.ok(result.getPayload());
        }
        if (result.getType() == ResultType.INVALID){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity getCharactersByUser(@PathVariable String username, Principal user){
        Result<List<PlayerCharacter>> result = service.findByUser(username, user.getName());
        if (result.isSuccess()){
            return ResponseEntity.ok(result.getPayload());
        }
        if (result.getType() == ResultType.INVALID){
            return new ResponseEntity<>(result.getMessages(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getMessages(),HttpStatus.NOT_FOUND);
    }

    @GetMapping("/campaign/{id}")
    public ResponseEntity getCharactersByCampaignId(@PathVariable Integer id, Principal user){
        Result<List<PlayerCharacter>> result = service.findByCampaign(id, user.getName());
        if (result.isSuccess()){
            return ResponseEntity.ok(result.getPayload());
        }
        if (result.getType() == ResultType.INVALID){
            return new ResponseEntity<>(result.getMessages(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getMessages(),HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity addCharacter(@RequestBody PlayerCharacter character, Principal user){
        if (character == null){
            return new ResponseEntity<>("Cannot have null character",HttpStatus.BAD_REQUEST);
        }
        Result<PlayerCharacter> result = service.addPC(character, user.getName());
        if (result.isSuccess()){
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        if (result.getType() == ResultType.INVALID){
            return new ResponseEntity<>(result.getMessages(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getMessages(),HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity updateCharacter(@RequestBody PlayerCharacter character, @PathVariable Integer id, Principal user){
        if (character == null){
            return new ResponseEntity<>("Cannot have null character",HttpStatus.BAD_REQUEST);
        }
        if (!character.getId().equals(id)){
            return new ResponseEntity<>("Id must match character Id",HttpStatus.BAD_REQUEST);
        }
        Result<PlayerCharacter> result = service.update(character, user.getName());
        if (result.isSuccess()){
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        if (result.getType() == ResultType.INVALID){
            return new ResponseEntity<>(result.getMessages(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getMessages(),HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteCharacter(@PathVariable Integer id, Principal user){
        Result<Boolean> result = service.delete(id, user.getName());
        if (result.isSuccess()){
            return ResponseEntity.ok(result.getPayload());
        }
        if (result.getType() == ResultType.INVALID){
            return new ResponseEntity<>(result.getMessages(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getMessages(),HttpStatus.NOT_FOUND);
    }

}
