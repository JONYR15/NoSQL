/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primermongoapp;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;

/**
 *
 * @author Laboratorios
 */
public class PrimerMongoApp {

    private final static String HOST = "localhost";
    private final static int PORT = 27017;

    public void createRow() {
        try {
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase db = mongoClient.getDatabase("local");
            MongoCollection<Document> coll = db.getCollection("users");
            Document doc = new Document("name", "john").append("age", 25).append("phone", "321-654-987");
            coll.insertOne(doc);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void mostrarDatos() {
        try {
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase db = mongoClient.getDatabase("local");
            MongoCollection<Document> coll = db.getCollection("users");
            MongoCursor<Document> cursor = coll.find().iterator();
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                System.out.println(doc.toJson());
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            // Now connect to the test database      
            MongoDatabase db = mongoClient.getDatabase("local");
            for (String name : mongoClient.listDatabaseNames()) {
                System.out.println("name: " + name);
            }
            System.out.println("Connect to database successfully ");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        PrimerMongoApp mongoApp = new PrimerMongoApp();
        mongoApp.createRow();
        mongoApp.mostrarDatos();
    }

}
