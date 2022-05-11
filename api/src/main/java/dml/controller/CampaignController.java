package dml.controller;


import dml.domain.CampaignService;
import dml.domain.Result;
import dml.domain.ResultType;
import dml.models.Campaign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/campaign")
public class CampaignController {

    @Autowired
    CampaignService service;

    @GetMapping
    public List<Campaign> findAll() { return service.findAll(); }
    
    @GetMapping("/{id}")
    public ResponseEntity getCampaignById(@PathVariable Integer id, Principal user){
        Result<Campaign> result = service.getCampaignById(id, user.getName());
        if (result.isSuccess()){
            return ResponseEntity.ok(result.getPayload());
        }
        if (result.getType() == ResultType.INVALID){
            return new ResponseEntity<>(result.getMessages(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(result.getMessages(),HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity getCampaignByUser(@PathVariable String username, Principal user){
        Result<List<Campaign>> result = service.getCampaignsByUser(username, user.getName());
        if (result.isSuccess()){
            return ResponseEntity.ok(result.getPayload());
        }
        if (result.getType() == ResultType.INVALID){
            return new ResponseEntity<>(result.getMessages(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(result.getMessages(),HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity AddCampaign(@RequestBody Campaign campaign, Principal user){
        if (campaign == null){
            return new ResponseEntity<>("Cannot have null campaign",HttpStatus.BAD_REQUEST);
        }
        Result<Campaign> result = service.addCampaign(campaign, user.getName());
        if (result.isSuccess()){
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        if (result.getType() == ResultType.INVALID){
            return new ResponseEntity<>(result.getMessages(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getMessages(),HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCampaign(@PathVariable Integer id,@RequestBody Campaign campaign ,Principal user){
        if (campaign == null){
            return new ResponseEntity<>("Cannot have null character",HttpStatus.BAD_REQUEST);
        }
        if (!campaign.getCampaignId().equals(id)){
            return new ResponseEntity<>("Id must match character Id",HttpStatus.BAD_REQUEST);
        }
        Result<Campaign> result = service.update(campaign, user.getName());
        if (result.isSuccess()){
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        if (result.getType() == ResultType.INVALID){
            return new ResponseEntity<>(result.getMessages(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getMessages(),HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeCampaign(@PathVariable Integer id, Principal user){
        Result<Boolean> result = service.deleteById(id, user.getName());
        if (result.isSuccess()){
            return ResponseEntity.ok(result.getPayload());
        }
        if (result.getType() == ResultType.INVALID){
            return new ResponseEntity<>(result.getMessages(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getMessages(),HttpStatus.NOT_FOUND);
    }

}
