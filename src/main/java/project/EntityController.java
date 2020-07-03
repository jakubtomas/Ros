package project;

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


    public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(8));
    }

}
