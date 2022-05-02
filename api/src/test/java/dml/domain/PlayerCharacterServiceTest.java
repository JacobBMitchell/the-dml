package dml.domain;

import dml.models.PlayerCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class PlayerCharacterServiceTest {

    @Autowired
    PlayerCharacterService service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findById() {
        Set<String> roles = new HashSet<>();
        roles.add("ADMIN");
        Result<PlayerCharacter> result = service.findById(1, "jacob");
        assertTrue(result.isSuccess());
    }

    @Test
    void findByUser() {
    }

    @Test
    void findByCampaign() {
    }

    @Test
    void addPC() {
    }
}