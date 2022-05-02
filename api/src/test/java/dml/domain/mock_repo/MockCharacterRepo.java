package dml.domain.mock_repo;

import dml.data.PlayerCharacterRepo;
import dml.models.PlayerCharacter;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


public class MockCharacterRepo implements PlayerCharacterRepo {
    @Override
    public PlayerCharacter findById(int characterId) {
        return buildCha();
    }

    @Override
    public List<PlayerCharacter> findByPlayer(int userId) {
        List<PlayerCharacter> characters = new ArrayList<>();
        characters.add(buildCha());
        PlayerCharacter ch2 = new PlayerCharacter();
        ch2.setUserId(3);
        ch2.setId(1);
        ch2.setCampaignId(2);
        characters.add(ch2);
        return characters;
    }

    @Override
    public List<PlayerCharacter> findByCampaign(int campaignId) {
        List<PlayerCharacter> characters = new ArrayList<>();
        characters.add(buildCha());
        PlayerCharacter ch2 = new PlayerCharacter();
        ch2.setUserId(4);
        ch2.setId(1);
        ch2.setCampaignId(1);
        characters.add(ch2);
        return characters;
    }

    @Override
    public PlayerCharacter add(PlayerCharacter character) {
        character.setId(2);
        return character;
    }

    @Override
    public boolean update(PlayerCharacter character) {
        return true;
    }

    @Override
    public boolean deleteById(int characterId) {
        return true;
    }

    private PlayerCharacter buildCha(){
        PlayerCharacter character = new PlayerCharacter();
        character.setId(2);
        character.setCharacterLevel(3);
        character.setCha(14);
        character.setUserId(3);
        character.setCampaignId(1);
        return character;
    }
}
