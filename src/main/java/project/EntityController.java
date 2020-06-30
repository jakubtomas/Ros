package project;

import org.json.JSONException;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Random;


public abstract class EntityController {

    public static String generateToken() {
        Random rand = new Random();
        long longToken = Math.abs(rand.nextLong());
        String random = Long.toString(longToken, 16);
        return random;
    }


    // todo create function validToken
    public boolean validToken(String token, String login) {

        return true;
    }

    //todo create one function
    public boolean existValue(String nameTable, String nameColumn, String searchValue) throws JSONException {
        Database database = new Database();
        return database.existValue(nameTable, nameColumn, searchValue);
//todo poznamka potrebne zmenit funkciu pretože po každom zavolani sa otvori a zatvori connection Database

        // return true or false

    }

    public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(8));
    }

}
