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






    public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(8));
    }

}
