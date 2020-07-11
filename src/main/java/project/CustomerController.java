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
public class CustomerController extends EntityController {


    @RequestMapping(method = RequestMethod.POST, value = "/createAccountCustomer")
    public ResponseEntity<String> createAccountCustomer(@RequestBody String data) throws JSONException {

        // todo dont forget input also city , state, street, zip to address


        JSONObject inputJson = new JSONObject(data);


        JSONObject result = new JSONObject();
        JSONObject errorMessage = new JSONObject();
        JSONObject objectforDb = new JSONObject();


        if (inputJson.has("fname") && inputJson.has("lname") &&
                inputJson.has("login") && inputJson.has("password") &&
                inputJson.has("email") && inputJson.has("phoneNumber") &&
                inputJson.has("city") && inputJson.has("street") &&
                inputJson.has("zip") && inputJson.has("state")) {


            Database database = new Database();

            // check exist login
            if (database.existValue("login", inputJson.getString("login"))) {
                errorMessage.put("login", "Login exist, Please change login  ");
            }

            // check exist email
            if (database.existValue("email", inputJson.getString("email"))) {
                errorMessage.put("email", "Email exist  ");
            }

            //checkPhoneNumber
            if (database.existValue("phoneNumber", inputJson.getString("phoneNumber"))) {
                errorMessage.put("phoneNumber", "PhoneNumber exist  ");
            }

            if (errorMessage.has("login") && errorMessage.has("email") && errorMessage.has("phoneNumber")) {
                database.closeConnectionDb();
                return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON).body(errorMessage.toString());

            }


            // hash password
            String hashPass = hash(inputJson.getString("password"));


            // create json object
            objectforDb.put("fname", inputJson.getString("fname"));  // put message
            objectforDb.put("lname", inputJson.getString("lname"));
            objectforDb.put("login", inputJson.getString("login"));
            objectforDb.put("password", hashPass);

            //contact
            objectforDb.put("email", inputJson.getString("email"));
            objectforDb.put("phoneNumber", inputJson.getString("phoneNumber"));

            //address
            objectforDb.put("city", inputJson.getString("city"));
            objectforDb.put("street", inputJson.getString("street"));
            objectforDb.put("zip", inputJson.getString("zip"));
            objectforDb.put("state", inputJson.getString("state"));


            //database
            //Database db = new Database();
            database.insertCustomer(objectforDb);
            database.closeConnectionDb();


            result.put("message", "Successfully create account ");
            return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON).body(result.toString());


        } else {

            result.put("error", "Empty value from input ");
            return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(result.toString());
        }


    }



}


