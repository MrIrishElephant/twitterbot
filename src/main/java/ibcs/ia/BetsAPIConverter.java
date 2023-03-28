package ibcs.ia;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.betsAPIModels.BetsAPIEntity;

import java.io.File;
import java.io.IOException;

public class BetsAPIConverter {
    
    public static BetsAPIEntity readJSON() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            BetsAPIEntity inPlay = objectMapper.readValue(new File("betsAPI.json"), BetsAPIEntity.class);
            return inPlay;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

