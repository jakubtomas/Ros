package project;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class RestaurantController extends EntityController {

    /**
     * @param data ,  String nameRestaurant,
     *                String login ,
     *                String token,
     *                String name food
     *                price Double
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
        if (!db.matchToken(token, inputJson.getString("login")) && !db.existValue("nameCompany" , inputJson.getString("companyName"))) {
            result.put("message", "Unauthorized.");

            return ResponseEntity.status(401).contentType(MediaType.APPLICATION_JSON).body(result.toString());
        }

        // check exist restaurant
        if (!db.existRestaurant(inputJson.getString("nameRestaurant"), inputJson.getString("loginOwnerRestaurant"))) {
            result.put("message", "Unauthorized.");
            return ResponseEntity.status(401).contentType(MediaType.APPLICATION_JSON).body(result.toString());
        }
        //objectforDb.put("nameRes", inputJson.getString("fname"));




        //todo create the objectFoddb json for database



        return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(result.toString());

    }

    // select one  food
    @RequestMapping(method = RequestMethod.POST, value = "/restaurant/food/read")
    public ResponseEntity<String> readFood(@RequestBody String data, @RequestHeader(name = "Authorization") String token) throws JSONException {


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
