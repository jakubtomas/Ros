package project;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//todo change crossOrigin address
// this address achieve permit connection from address , from Vue application
@CrossOrigin(origins = {"http://localhost:8081"}) // or 8080
@RestController
public class OwnerRestaurantController extends EntityController {

    @RequestMapping(method = RequestMethod.POST, value = "/registrationOwnerRestaurant")
    public ResponseEntity<String> registrationOwnerRestaurant(@RequestBody String data) throws JSONException {

        JSONObject inputJson = new JSONObject(data);

//        List<String> errors = new ArrayList<>();
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

}
