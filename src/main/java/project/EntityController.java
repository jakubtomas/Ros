package project;

import org.json.JSONException;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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


    /**
     * Login users
     *
     * @param data
     * @return
     * @throws JSONException
     */

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity<String> login(@RequestBody String data) throws JSONException {

        JSONObject inputJson = new JSONObject(data);
        JSONObject result = new JSONObject();

        if (inputJson.has("login") && inputJson.has("password")) {

            //check password and login that are empty
            if (inputJson.getString("password").isEmpty() || inputJson.getString("login").isEmpty()) {

                result.put("error", "Password and login are mandatory fields");
                return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(result.toString());
            }

            //create database connection
            Database db = new Database();
            JSONObject userObject = db.getLoginData(inputJson.getString("login")); //get data from database


            if (userObject == null) {
                result.put("login", "Bad name or password ");
                return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(result.toString());



            }/*else if (userObject.has("companyName")){
                // create object ownerRestaurant

                //OwnerRestaurant ownerRestaurant = new OwnerRestaurant()

            } else if (!userObject.has("companyName") && userObject.has("login") && !userObject.has("nameRestaurant") ) {
                //create object Customer


            }else if(!userObject.has("companyName") && userObject.has("login") && userObject.has("nameRestaurant")){
                //create object Employee

            }*/


            //check password compare password from database and from input form
            if (BCrypt.checkpw(inputJson.getString("password"), userObject.getString("password"))) {
                //correct password
                System.out.println("password match okey ");

                String token = generateToken();

                System.out.println("token " + token);

                db.saveToken(inputJson.getString("login"), token);
                db.closeConnectionDb();

                result.put("message", "successfully login ");
                result.put("login", userObject.getString("login"));
                result.put("token", token);//

                /// todo also write condition when is the ownerRestaurant return data also from Class Restaurant Name
                return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(result.toString());

                //bad password
            } else {
                System.out.println(" password dosnt match");
                System.out.println("userobject " + userObject.getString("password"));
                System.out.println("userobject " + userObject.getString("login"));
                System.out.println("=====================================");
                System.out.println("userobject " + inputJson.getString("login"));
                System.out.println("userobject " + inputJson.getString("password"));

                db.closeConnectionDb();
                result.put("message", "Bad password ");
                return ResponseEntity.status(401).contentType(MediaType.APPLICATION_JSON).body(result.toString());
            }
        }
        return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(result.toString());
    }




    /**
     * @param data
     * @param token
     * @return
     * @throws JSONException
     */
    @RequestMapping(method = RequestMethod.POST, value = "/logout")
    public ResponseEntity<String> logout(@RequestBody String data, @RequestHeader(name = "Authorization") String token) throws JSONException {


        JSONObject objectInput = new JSONObject(data);
        JSONObject result = new JSONObject();


        Database database = new Database();
        if (database.matchToken( token, objectInput.getString("login"))) {

            database.deleteToken(token, objectInput.getString("login"));
            database.closeConnectionDb();
            result.put("message", "successfully logout account ");
            return ResponseEntity.status(401).contentType(MediaType.APPLICATION_JSON).body(result.toString());

        } else {

            database.closeConnectionDb();
            result.put("message", "Unauthorized.");
            return ResponseEntity.status(401).contentType(MediaType.APPLICATION_JSON).body(result.toString());
        }


    }


}
