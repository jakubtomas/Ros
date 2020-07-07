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
                result.put("token", token);
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


    /**
     * @param data
     * @return
     * @throws JSONException
     */
    @RequestMapping(method = RequestMethod.POST, value = "/createOwnerRestaurant")
    public ResponseEntity<String> createOwnerRestaurant(@RequestBody String data) throws JSONException {

        // todo dont forget input also city , state, street, zip to address

        JSONObject inputJson = new JSONObject(data);
        JSONObject errorMessage = new JSONObject();
        JSONObject correctMessage = new JSONObject();
        JSONObject objectforDb = new JSONObject();

        // valid token if false return 401
        if (inputJson.has("fname") && inputJson.has("lname") &&
                inputJson.has("login") && inputJson.has("password") &&
                inputJson.has("address") && inputJson.has("email") &&
                inputJson.has("phoneNumber") && inputJson.has("ico") &&
                inputJson.has("dic") && inputJson.has("icDph") &&
                inputJson.has("companyName") && inputJson.has("invoiceStreet") &&
                inputJson.has("invoiceZipcode") && inputJson.has("invoiceCity")) {


            // create database
            Database database = new Database();

            // check exist login
            if (database.existValue("login", inputJson.getString("login"))) {
                errorMessage.put("login", "Login exist, Please change login  ");
            }

            // check exist email
            if (database.existValue("email", inputJson.getString("email"))) {
                errorMessage.put("email", "Email exist  ");
            }

            // check exist ico
            if (database.existValue("ico", inputJson.getString("ico"))) {
                errorMessage.put("ico", "Ico exist  ");
            }

            // check exist dic
            if (database.existValue("dic", inputJson.getString("dic"))) {
                errorMessage.put("dic", "dic exist  ");
            }

            // check exist icDph
            if (database.existValue("icDph", inputJson.getString("icDph"))) {
                errorMessage.put("icDph", "icDph exist  ");
            }

            // check exist companyName
            if (database.existValue("companyName", inputJson.getString("companyName"))) {
                errorMessage.put("companyName", "companyName exist  ");
            }


            // control  than we have value in object errorMessage
            if (errorMessage.has("login") || errorMessage.has("email") || errorMessage.has("ico")
                    || errorMessage.has("dic") || errorMessage.has("icDph") || errorMessage.has("companyName")) {

                database.closeConnectionDb();
                return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON).body(errorMessage.toString());

            } else {

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


                //database
                database.insertOwnerRestaurant(objectforDb);
                database.closeConnectionDb();
                correctMessage.put("message", "Successfully create account ");
                return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON).body(correctMessage.toString());

            }

        } else {

            errorMessage.put("error", "Empty value from input ");
            return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(errorMessage.toString());
        }


    }


    /**
     * @param data , String nameRestaurant, String login , String token, String name food
     *             price Double
     * @return String
     * @throws JSONException
     * @name Jakub Tomas
     * @date 5.7.2020
     */
    /**/
    @RequestMapping(method = RequestMethod.POST, value = "/restaurant/food/create")
    public ResponseEntity<String> createFood(@RequestBody String data, @RequestHeader(name = "Authorization") String token) throws JSONException {


        JSONObject inputJson = new JSONObject(data);
        JSONObject result = new JSONObject();
        JSONObject objectforDb = new JSONObject();


        Database db = new Database();

        // check token
        if (db.matchToken(token, inputJson.getString("login"))) {
            result.put("message", "Unauthorized.");

            return ResponseEntity.status(401).contentType(MediaType.APPLICATION_JSON).body(result.toString());
        }

        //todo create the objectFoddb json for database
        //check exist nameRestaurant


        return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(result.toString());

    }



    // update Food
    @RequestMapping(method = RequestMethod.POST, value = "/restaurant/food/update")
    public ResponseEntity<String> updateFood(@RequestBody String data, @RequestHeader(name = "Authorization") String token) throws JSONException {


        JSONObject inputJson = new JSONObject(data);
        JSONObject result = new JSONObject();
        JSONObject objectforDb = new JSONObject();

        // check exist valid Token
        Database db = new Database();
        if (db.matchToken(token, inputJson.getString("login"))) {
            result.put("message", "Unauthorized.");

            return ResponseEntity.status(401).contentType(MediaType.APPLICATION_JSON).body(result.toString());
        }


        //check exist nameRestaurant


        return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(result.toString());

    }




    // delete Food from database
    @RequestMapping(method = RequestMethod.POST, value = "/restaurant/food/delete")
    public ResponseEntity<String> deleteFood(@RequestBody String data, @RequestHeader(name = "Authorization") String token) throws JSONException {


        JSONObject inputJson = new JSONObject(data);
        JSONObject result = new JSONObject();
        JSONObject objectforDb = new JSONObject();

        // check exist valid Token
        Database db = new Database();
        if (db.matchToken(token, inputJson.getString("login"))) {
            result.put("message", "Unauthorized.");

            return ResponseEntity.status(401).contentType(MediaType.APPLICATION_JSON).body(result.toString());
        }


        //check exist nameRestaurant


        return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(result.toString());

    }

}
