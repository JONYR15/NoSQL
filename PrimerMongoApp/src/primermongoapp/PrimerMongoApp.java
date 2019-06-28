/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primermongoapp;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

/**
 *
 * @author Laboratorios
 */
public class PrimerMongoApp {

    private final static String HOST = "localhost";
    private final static int PORT = 27017;

    public static void main(String[] args) {
        try {
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            // Now connect to the test database      
            MongoDatabase db = mongoClient.getDatabase("test");
            System.out.println("Connect to database successfully ");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

}
