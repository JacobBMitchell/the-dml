package dml.domain;

import dml.domain.mock_repo.MockCampaignRepo;
import dml.domain.mock_repo.MockCharacterRepo;
import dml.domain.mock_repo.MockUserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.*;

class PlayerCharacterServiceTest {

    @Mock
    PlayerCharacterService service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findById() {

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