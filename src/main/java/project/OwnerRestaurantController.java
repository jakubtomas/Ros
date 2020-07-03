package project;

import org.json.JSONException;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//todo change crossOrigin address
// this address achieve permit connection from address , from Vue application
@CrossOrigin(origins = {"http://localhost:8081"}) // or 8080
@RestController
public class OwnerRestaurantController extends EntityController {



    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity<String> login(@RequestBody String data) throws JSONException {

        JSONObject inputJson = new JSONObject(data);
        JSONObject result = new JSONObject();
        JSONObject objectforDb = new JSONObject();


        if (inputJson.has("login") && inputJson.has("password")) {

            //check password and login that are empty
            if (inputJson.getString("password").isEmpty() || inputJson.getString("login").isEmpty()) {
                result.put("error", "Password and login are mandatory fields");
                return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(result.toString());

            }

            String hashInputPassword = hash(inputJson.getString("password")); // create hash


            Database db = new Database();
            JSONObject userObject = db.getUser(inputJson.getString("login"));

            if (BCrypt.checkpw(inputJson.getString("password"), userObject.getString("password"))) {

                System.out.println("password match okey ");
                // todo generate token and save into database
                // update value token in database

                String token = generateToken();



            } else {
                System.out.println(" password dosnt match");
            }


            System.out.println("userobject " + userObject.getString("password"));
            System.out.println("userobject " + userObject.getString("login"));
            System.out.println("=====================================");
            System.out.println("userobject " + inputJson.getString("login"));
            System.out.println("userobject " + inputJson.getString("password"));


        }







        return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(result.toString());
    }


        @RequestMapping(method = RequestMethod.POST, value = "/createOwnerRestaurant")
    public ResponseEntity<String> createOwnerRestaurant(@RequestBody String data) throws JSONException {

        // todo dont forget input also city , state, street, zip to address

        JSONObject inputJson = new JSONObject(data);
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


            // todo open connection database  somewhere here

            // create database
            Database database = new Database();

            // check exist login
            if (database.existValue("login", inputJson.getString("login"))) {
                result.put("login", "Login exist, Please change login  ");
            }

           // check exist email
            if (database.existValue("email", inputJson.getString("email"))) {
                result.put("email", "Email exist  ");
            }

            // check exist ico
            if (database.existValue("ico", inputJson.getString("ico"))) {
                result.put("ico", "Ico exist  ");
            }

            // check exist dic
            if (database.existValue("dic", inputJson.getString("dic"))) {
                result.put("dic", "dic exist  ");
            }

            // check exist icDph
            if (database.existValue("icDph", inputJson.getString("icDph"))) {
                result.put("icDph", "icDph exist  ");
            }

            // check exist companyName
            if (database.existValue("companyName", inputJson.getString("companyName"))) {
                result.put("companyName", "companyName exist  ");
            }

            database.closeConnectionDb();


            // todo write close database connection
            //todo write condition when is empty result continue when no return 400
//            return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(result.toString());


            // hash password
            String hashPass = hash(inputJson.getString("password"));


            // create json object+
            objectforDb.put("fname", inputJson.getString("fname"));  // put message
            objectforDb.put("lname", inputJson.getString("lname"));
            objectforDb.put("login", inputJson.getString("login"));
            objectforDb.put("password", hashPass);

            //contact
            objectforDb.put("address", inputJson.getString("address"));
            objectforDb.put("email", inputJson.getString("email"));
            objectforDb.put("phoneNumber", inputJson.getString("phoneNumber"));

            //invoice information about company
            objectforDb.put("ico", inputJson.getString("ico"));
            objectforDb.put("dic", inputJson.getString("dic"));
            objectforDb.put("icDph", inputJson.getString("icDph"));
            objectforDb.put("companyName", inputJson.getString("companyName"));
            objectforDb.put("invoiceStreet", inputJson.getString("invoiceStreet"));
            objectforDb.put("invoiceZipcode", inputJson.getString("invoiceZipcode"));
            objectforDb.put("invoiceCity", inputJson.getString("invoiceCity"));
            ; // default empty token


            //database
            Database db = new Database();
            db.insertOwnerRestaurant(objectforDb);


            result.put("message", "Successfully create account ");
            return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON).body(result.toString());


        } else {

            result.put("error", "Empty value from input ");
            return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(result.toString());
        }


    }


    /**
     * @name Jakub Tomas
     * @date 5.7.2020
     * @param data    , String nameRestaurant, String login , String token, String name food
     *                price Double
     * @return String
     * @throws JSONException
     */
    /**/

    @RequestMapping(method = RequestMethod.POST, value = "/restaurant/food/create")
    public ResponseEntity<String> createFood(@RequestBody String data) throws JSONException {


        JSONObject inputJson = new JSONObject(data);
        JSONObject result = new JSONObject();
        JSONObject objectforDb = new JSONObject();

        // check exist valid Token
        Database db = new Database();
        if (db.matchToken(inputJson.getString("token"),inputJson.getString("login"))) {
            result.put("message", "Unauthorized.");

            return ResponseEntity.status(401).contentType(MediaType.APPLICATION_JSON).body(result.toString());
        }


        //check exist nameRestaurant




        return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(result.toString());

    }


}
