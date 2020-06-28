package project;


import org.json.JSONException;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//todo change crossOrigin address
// this address achieve permit connection from address , from Vue application
@CrossOrigin(origins = {"http://localhost:8081"}) // or 8080
@RestController
public class Router {

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


    @RequestMapping(method = RequestMethod.POST, value = "/registrationOwnerRestaurant")
    public ResponseEntity<String> registrationOwnerRestaurant(@RequestBody String data) throws JSONException {

        JSONObject inputJson = new JSONObject(data);

        List<String> errors = new ArrayList<>();
        JSONObject result = new JSONObject();
        JSONObject objectforDb = new JSONObject();

        // valid token if false return 401

        if (inputJson.has("fname") && inputJson.has("lname") &&
                inputJson.has("login") && inputJson.has("password") &&
                inputJson.has("address") && inputJson.has("email") &&
                inputJson.has("phoneNumber") && inputJson.has("ico") &&
                inputJson.has("dic") && inputJson.has("icDph") &&
                inputJson.has("companyName") && inputJson.has("invoiceStreet") &&
                inputJson.has("invoiceZipcode") && inputJson.has("invoiceCity")) {




            result.put("error", "Empty value from input ");
            return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(result.toString());

        } else {
            // hash password
            String hashPass = hash(inputJson.getString("password"));


            // create json object+
            objectforDb.put("fname", inputJson.getString("fname"));  // put message
            objectforDb.put("lname", inputJson.getString("lname"));
            objectforDb.put("login", inputJson.getString("login"));
            objectforDb.put("address", inputJson.getString("address"));
            objectforDb.put("password", hashPass);
            objectforDb.put("token", ""); // default empty token


            //database
            Database db = new Database();
            db.insertOwnerRestaurant(objectforDb);




            result.put("message", "Successfully create account ");
            return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON).body(result
                    .toString());
        }



    }


}
