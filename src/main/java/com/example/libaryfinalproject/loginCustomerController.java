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
import javafx.scene.control.Alert;
import java.io.IOException;
import java.util.HashMap;

public class loginCustomerController {



    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private void onLoginButtonClick() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("שגיאת התחברות", "אנא מלא את כל השדות.");
            return;
        }

        // Create a map of credentials
        HashMap<String, Object> credentials = new HashMap<>();
        credentials.put("username", username);
        credentials.put("password", password);

        // Assuming sendLoginRequest returns true if login is successful, and false otherwise
        boolean loginSuccess = sendLoginRequest(credentials);

        if (loginSuccess) {
            moveToCustomerView();
        } else {
            showAlert("שגיאת התחברות", "שם משתמש או סיסמה שגויים. נסה שוב.");
        }
    }

    /**
     * Shows an alert dialog to the user.
     * @param headerText the header text of the alert.
     * @param contentText the content text (main message) of the alert.
     */
    private void showAlert(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    private void moveToCustomerView() {
        try {
            // Load the customer view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("customer-view.fxml"));
            Parent root = loader.load();

            // Get the current stage from the usernameField (which is part of the current scene)
            Stage stage = (Stage) usernameField.getScene().getWindow();

            // Set the customer view as the current scene
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean sendLoginRequest(HashMap<String, Object> credentials) {
        // Create a request object with the action and body
        Request request = new Request("login", credentials);

        // Send the request to the server and get the response
        // Implement this method to send the request and get the response
        return Client.sendRequestAndGetResponse4(request);
    }




    @FXML
    private void onRegisterButtonClick(ActionEvent event) throws IOException {

        // Load the second view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("registerCustomer-view.fxml"));
        Parent managerView = loader.load();

        // Get the current stage from the event
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        // Set the new scene on the current stage
        stage.setScene(new Scene(managerView));

        // Show the new view
        stage.show();


    }

    @FXML
    public void onbackButtonClick(ActionEvent event) throws IOException {

        // Load the second view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent managerView = loader.load();

        // Get the current stage from the event
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new scene on the current stage
        stage.setScene(new Scene(managerView));



        // Show the new view
        stage.show();


    }


}
