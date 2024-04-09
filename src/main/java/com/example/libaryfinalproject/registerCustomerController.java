package com.example.libaryfinalproject;

import com.example.libaryfinalproject.model.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class registerCustomerController {
    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;


 @FXML
    private void sendRegistrationRequest() throws IOException {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Username and password are required.");
            return;
        }


        Map<String, Object> userCredentials = new HashMap<>();
        userCredentials.put("username", username);
        userCredentials.put("password", password);

        Request request = new Request("register", userCredentials);
        boolean success = Client.sendRequestAndGetResponse3(request);
     if (success) {
         System.out.println("Registration successful.");

         FXMLLoader loader = new FXMLLoader(getClass().getResource("customer-view.fxml"));
         Parent root = loader.load();


         Stage stage = (Stage) usernameField.getScene().getWindow();
         Scene scene = new Scene(root);
         stage.setScene(scene);
         stage.show();


     } else {
         System.out.println("Registration failed. Username might be taken, or server error occurred.");
     }


    }

    @FXML
    public void onbackButtonClick(ActionEvent event) throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginCustomer-view.fxml"));
        Parent managerView = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(managerView));

        stage.show();


    }

}
