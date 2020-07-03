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
    private MongoCollection<Document> collectionEmployee = database.getCollection("employee");






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
        System.out.println("IinsertOwnerRestaurant successfully ");
        System.out.println("=================================");
    }


    public boolean existToken(String token, String login) throws JSONException {
        try (MongoCursor<Document> cursor = collectionOwnerRestaurant.find().iterator()) {

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



   /* public boolean existValue(String nameTable, String nameColumn,String searchValue ) {

        Document found;

        //collection ownerRestaurant
        if (nameTable.equals("ownerRestaurant")) {
             found = collectionOwnerRestaurant.find(new Document(nameColumn, searchValue)).first();

             // collection customer
        } else if (nameTable.equals("customer")) {
            found = collectionCustomer.find(new Document(nameColumn, searchValue)).first();

        } else if (nameTable.equals("employee")) {
            found = collectionEmployee.find(new Document(nameColumn, searchValue)).first();

        }else {

            found = collectionCustomer.find(new Document(nameColumn, searchValue)).first();
        }


        System.out.println("found is " + found);


        JSONObject objectResult = new JSONObject(found);

        //close Connection Database
        closeConnectionDb();

        if (found == null) {
            System.out.println("---------------------------");
            System.out.println(" WE DONT HAVE VALUE");
            System.out.println("---------------------------");
            return false; //  dos not exist record
        } else {
            System.out.println("-----------------------");
           // System.out.println("get login from json  === " + object.getString("login") + " ===");
            System.out.println("we HAVE VALUE IN OUR DATABASE ");
            System.out.println("-----------------------");
            return true;
        }
    }*/



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


}
