/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import database.Database;
import database.DatabaseFactory;
import java.io.IOException;
import view.controllerView.LoginViewController;

/**
 *
 * @author User
 */
public class Main extends Application {

    private final Database database = DatabaseFactory.openDatabaseSQLServer();
    private final Connection connection = database.conectar();
    
    @Override
    public void start(Stage primaryStage) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/FXML/TelaLogin.fxml"));
//            loader.setController(new LoginViewController());
            Parent root = loader.load();
            
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Gabaritando");
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
