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
public class EmployeeController extends EntityController {

    @RequestMapping(method = RequestMethod.POST, value = "/employeeRegistration")
    public ResponseEntity<String> employeeRegistration (@RequestBody String data) throws JSONException {


        JSONObject inputJson = new JSONObject(data);

//        List<String> errors = new ArrayList<>();
        JSONObject result = new JSONObject();
        JSONObject objectforDb = new JSONObject();


        //todo create registration check all value that we have in database and after this save or

        result.put("error", "Empty value from input ");
        return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(result.toString());

    }
}
