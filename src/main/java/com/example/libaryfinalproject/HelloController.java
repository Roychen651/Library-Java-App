package com.example.libaryfinalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {


    // Method called when the button is clicked
    @FXML
    private void onHelloButtonClick(ActionEvent event) throws IOException {

            // Load the second view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Parent managerView = loader.load();

            // Get the current stage from the event
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

            // Set the new scene on the current stage
            stage.setScene(new Scene(managerView));

            // Show the new view
            stage.show();


}
    @FXML
    private void onHelloButtonClickCustomer(ActionEvent event) throws IOException {

        // Load the second view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginCustomer-view.fxml"));
        Parent managerView = loader.load();

        // Get the current stage from the event
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        // Set the new scene on the current stage
        stage.setScene(new Scene(managerView));



        // Show the new view
        stage.show();


    }




}