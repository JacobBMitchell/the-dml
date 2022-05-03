package dml.domain;

import dml.data.CampaignRepo;
import dml.data.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CampaignServiceTest {

    @Autowired
    CampaignService service;

    @MockBean
    UserRepo userRepo;

    @MockBean
    CampaignRepo campRepo;

    @Test
    void getCampaignById() {
    }

    @Test
    void getCampaignsByUser() {
    }

    @Test
    void addCampaign() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}