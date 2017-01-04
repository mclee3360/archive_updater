package com.archiveUpdater.controller;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Main Controller for the application.
 *
 * Created 01/03/2017
 */
public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        initDatabase();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void initDatabase() {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                .setServiceAccount(new FileInputStream("rewrite-archive-f7a8b-fireb"
                    + "ase-adminsdk-fqc5u-5d9e7b2d00.json"))
                .setDatabaseUrl("https://rewrite-archive-f7a8b.firebaseio.com/")
                .build();
            FirebaseApp.initializeApp(options);
        }
        catch (java.io.FileNotFoundException e) {
            Logger.getLogger(MainApplication.class.getName()).log(Level.SEVERE,
                null, e);
        }
    }
}
