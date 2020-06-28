package project;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

public class Database {

    // todo create Design Pattern for connection  Singleton
    private MongoClient mongo = new MongoClient("localhost", 27017);
    private MongoDatabase database = mongo.getDatabase("ros");

    //table ownerRestaurant
    private MongoCollection<Document> ownerRestaurant = database.getCollection("ownerRestaurant");


    public void closeConnectionDb() {
        this.mongo = null;
        this.database = null;
    }

    public void insertOwnerRestaurant(JSONObject jsonObject) throws JSONException {

        Document document = new Document()
                .append("fname", jsonObject.getString("fname"))
                .append("lname", jsonObject.getString("lname"))
                .append("login", jsonObject.getString("login"))
                .append("password", jsonObject.getString("password"))

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


        ownerRestaurant.insertOne(document);

        System.out.println("=================================");
        System.out.println("IinsertOwnerRestaurant successfully ");
        System.out.println("=================================");
    }


    public boolean existToken(String token, String login) throws JSONException {
        try (MongoCursor<Document> cursor = ownerRestaurant.find().iterator()) {

            System.out.println("input token " + token);
            System.out.println("input login " + login);

            while (cursor.hasNext()) {
                Document doc = cursor.next();
                JSONObject object = new JSONObject(doc.toJson());

                if (object.getString("login").equals(login) && object.getString("token").equals(token)) {
                    System.out.println("login from object " + object.getString("login"));
                    return true;
                }
            }
        }
        return false;
    }


}
