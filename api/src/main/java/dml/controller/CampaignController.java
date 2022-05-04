package dml.controller;


import dml.domain.CampaignService;
import dml.domain.Result;
import dml.domain.ResultType;
import dml.models.Campaign;
import dml.models.PlayerCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/campaign")
public class CampaignController {

    @Autowired
    CampaignService service;

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

    @PostMapping
    public ResponseEntity AddCampaign(@RequestBody Campaign campaign, Principal user){
        if (campaign == null){
            return new ResponseEntity<>("Cannot have null character",HttpStatus.BAD_REQUEST);
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

    @PostMapping("/{id}")
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
