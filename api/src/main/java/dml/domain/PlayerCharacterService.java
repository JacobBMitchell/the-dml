package dml.domain;

import dml.data.PlayerCharacterRepo;
import dml.models.PlayerCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerCharacterService {

    @Autowired
    PlayerCharacterRepo repo;

    public Result findById(Integer id) {
        Result result = new Result();
        if (id == null || id <= 0){

        }
        return result;
    }

}
