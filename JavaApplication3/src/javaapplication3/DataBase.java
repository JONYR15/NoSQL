/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import java.util.function.Consumer;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import org.bson.Document;

/**
 *
 * @author Laboratorios
 */
public class DataBase {

    private MongoClient mongoClient;

    private MongoDatabase database;

    public DataBase() {
        mongoClient = new MongoClient("localhost", 27017);
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public void setMongoClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void setDatabase(MongoDatabase database) {
        this.database = database;
    }

    public static boolean doesCollectionExist(MongoDatabase database, String collectionName) {
        boolean result = false;
        MongoIterable<String> collections = database.listCollectionNames();

        Iterator<String> it = collections.iterator();
        while (!result && it.hasNext()) {
            if (it.next().equalsIgnoreCase(collectionName)) {
                result = true;
            }
        }
        return result;
    }

    public void createcolletion() {
        // connect to database
        database = mongoClient.getDatabase("veterinaria");
        // make a Collection if it doesn't exist
        if (!doesCollectionExist(database, "customers")) {
            database.createCollection("customers");
        }
    }

//    public String[] printDataBaseNames() {
//        String[] listModel;
//        Iterator<String> it = mongoClient.listDatabaseNames().iterator();
//        System.out.println(it.toString());
//        while (it.hasNext()) {
//            listModel.
//        }
//        return
//    }
    public void printColletion() {
        // display all existing collections for current database
        Consumer<String> action = System.out::println;
        database.listCollectionNames().forEach(action);
    }

    public ArrayList<String> getDataBaseList() {
       ArrayList<String> model = new ArrayList<>();
        Iterator<String> it = mongoClient.listDatabaseNames().iterator();
        while (it.hasNext()) {
            model.add(it.next());
        }
        return model;
    }

    public void insertCustomer() {
        // Insert customer
        MongoCollection<Document> collection = database.getCollection("customers");
        Document document = new Document();
        document.put("name", "New Shubham");
        document.put("company", "Baeldung");
        collection.insertOne(document);
    }

}
