package dml.data;

import dml.models.PlayerCharacter;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PlayerCharacterRepo {

    PlayerCharacter findById(int characterId);

    List<PlayerCharacter> findByPlayer(String username);

    List<PlayerCharacter> findByCampaign(int campaignId);

    PlayerCharacter add(PlayerCharacter character);

    boolean update(PlayerCharacter character);

    @Transactional
    boolean deleteById(int characterId);
}
