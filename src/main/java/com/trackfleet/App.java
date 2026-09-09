package com.trackfleet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.trackfleet.database.Seeder;

public class App extends Application {
   
    public void start(Stage stage) throws Exception {
        // Initialize DB schema and seed data
        Seeder.initDatabase();
        
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/trackfleet/views/main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("TrackFleet");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}