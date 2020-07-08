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
                inputJson.has("invoiceZipCode") && inputJson.has("invoiceCity")) {


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


}
