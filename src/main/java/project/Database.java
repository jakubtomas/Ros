package project;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Database {

    // todo create Design Pattern for connection  Singleton
    private MongoClient mongo = new MongoClient("localhost", 27017);
    private MongoDatabase database = mongo.getDatabase("ros");

    //table ownerRestaurant
    private MongoCollection<Document> collectionOwnerRestaurant = database.getCollection("ownerRestaurant");
    private MongoCollection<Document> collectionCustomer = database.getCollection("customer");
    private MongoCollection<Document> collectionEmployee;



    public void closeConnectionDb() {
        this.mongo = null;
        this.database = null;
    }

    /**
     * @param jsonObject
     * @throws JSONException
     */
    public void insertOwnerRestaurant(JSONObject jsonObject) throws JSONException {

        Document document = new Document()
                .append("fname", jsonObject.getString("fname"))
                .append("lname", jsonObject.getString("lname"))
                .append("login", jsonObject.getString("login"))
                .append("password", jsonObject.getString("password"))
                .append("token", "")

                //contact
                .append("address", jsonObject.getString("address"))
                .append("email", jsonObject.getString("email"))
                .append("phoneNumber", jsonObject.getString("phoneNumber"))

                // invoice information about company
                .append("ico", jsonObject.getString("ico"))
                .append("dic", jsonObject.getString("dic"))
                .append("icDph", jsonObject.getString("icDph"))
                .append("companyName", jsonObject.getString("companyName"))
                .append("invoiceStreet", jsonObject.getString("invoiceStreet"))
                .append("invoiceZipcode", jsonObject.getString("invoiceZipcode"))
                .append("invoiceCity", jsonObject.getString("invoiceCity"));


        collectionOwnerRestaurant.insertOne(document);

        System.out.println("=================================");
        System.out.println("Insert OwnerRestaurant successfully ");
        System.out.println("=================================");
    }


    public void insertCustomer(JSONObject jsonObject) throws JSONException {

        Document document = new Document()
                .append("fname", jsonObject.getString("fname"))
                .append("lname", jsonObject.getString("lname"))
                .append("login", jsonObject.getString("login"))
                .append("password", jsonObject.getString("password"))
                .append("token", "")

                //contact
                .append("email", jsonObject.getString("email"))
                .append("phoneNumber", jsonObject.getString("phoneNumber"))
                .append("city", jsonObject.getString("city"))
                .append("state", jsonObject.getString("state"))
                .append("street", jsonObject.getString("street"))
                .append("zip", jsonObject.getString("zip"));



        collectionCustomer.insertOne(document);

        System.out.println("===" +
                "==============================");
        System.out.println("Insert Customer into database  successfully ");
        System.out.println("=================================");
    }




    /**
     * @param token
     * @param login
     * @return
     * @throws JSONException
     */

    public boolean matchToken(String token, String login) throws JSONException {

        ArrayList<MongoCollection<Document>> collections = new ArrayList<>();


        collections.add(collectionOwnerRestaurant);
        collections.add(collectionEmployee);
        collections.add(collectionCustomer);

        for (int collection = 0; collection < collections.size(); collection++) {
            try (MongoCursor<Document> cursor = collections.get(collection).find().iterator()) {


                while (cursor.hasNext()) {
                    Document doc = cursor.next();
                    JSONObject object = new JSONObject(doc.toJson());

                    if (object.getString("login").equals(login) && object.getString("token").equals(token)) {
                        System.out.println("login from object " + object.getString("login"));
                        return true;

                    }
                }
            }


        }

        return false;
    }



    public boolean existValue(String nameColumn, String searchValue) {

        // solve this with array or array list
        Document found ;

        boolean result = false;

        ArrayList<MongoCollection<Document>> collections = new ArrayList<>();

        collections.add(collectionOwnerRestaurant);
        collections.add(collectionEmployee);
        collections.add(collectionCustomer);


        for (int collection = 0; collection < collections.size(); collection++) {

            found = collections.get(collection).find(new Document(nameColumn, searchValue)).first();

            if (found == null) {
                System.out.println(" WE DONT HAVE VALUE");
                result = false;

            } else {
                System.out.println("we HAVE VALUE IN OUR DATABASE ");
                result = true;
                break;
            }
        }
        return result;
    }


    /** function for login  retur
     * @param login
     * @return
     * @throws JSONException
     */
    public JSONObject getUser(String login) throws JSONException {
        Document found = collectionOwnerRestaurant.find(new Document("login", login)).first();
        JSONObject object = new JSONObject(found);

        if (found == null) {
            System.out.println(" WE DONT HAVE VALUE");
            return null; //  dos not exist record
        } else {
            System.out.println("get login from json  === " + object.getString("login") + " ===");
            System.out.println("we HAVE VALUE IN OUR DATABASE ");
            return object;
        }



    }

}
