package dml.controller;


import dml.domain.CampaignService;
import dml.models.Campaign;
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
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping
    public ResponseEntity AddCampaign(@RequestBody Campaign campaign, Principal user){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping("/{id}")
    public ResponseEntity updateCampaign(@PathVariable Integer id,@RequestBody Campaign campaign ,Principal user){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeCampaign(@PathVariable Integer id, Principal user){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
