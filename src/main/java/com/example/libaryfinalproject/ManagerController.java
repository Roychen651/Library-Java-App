package com.example.libaryfinalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerController {


    @FXML
    public void onAddBookButtonClick(ActionEvent event) throws IOException {

        // Load the second view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addbook-view.fxml"));
        Parent managerView = loader.load();

        // Get the current stage from the event
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new scene on the current stage
        stage.setScene(new Scene(managerView));



        // Show the new view
        stage.show();


    }

    @FXML
    public void onSearchBookButtonClick(ActionEvent event) throws IOException {

        // Load the second view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("searchbookManager-view.fxml"));
        Parent managerView = loader.load();

        // Get the current stage from the event
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new scene on the current stage
        stage.setScene(new Scene(managerView));


        // Show the new view
        stage.show();


    }

    @FXML
    public void onremoveBookButtonClick(ActionEvent event) throws IOException {

        // Load the second view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("removebook-view.fxml"));
        Parent managerView = loader.load();

        // Get the current stage from the event
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new scene on the current stage
        stage.setScene(new Scene(managerView));



        // Show the new view
        stage.show();
    }
    @FXML
    public void onreditBookButtonClick(ActionEvent event) throws IOException {

        // Load the second view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editbook-view.fxml"));
        Parent managerView = loader.load();

        // Get the current stage from the event
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new scene on the current stage
        stage.setScene(new Scene(managerView));


        // Show the new view
        stage.show();
    }
}