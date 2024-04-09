package com.example.libaryfinalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.io.IOException;

public class loginController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private void onLoginButtonClick() throws Exception {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if ("admin".equals(username) && "admin".equals(password)) {
            // Load customer-view screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("manager-view.fxml"));
            Parent root = loader.load();

            // Assuming you're within a Stage context
            Stage stage = (Stage) usernameField.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            // Show an alert dialog for login failure
            showAlert("התחברות נכשלה", "שם משתמש או סיסמה שגויים. נסה שוב.");
        }
    }

    /**
     * Helper method to show an alert dialog.
     * @param header The header text of the alert.
     * @param content The content text (main message) of the alert.
     */
    private void showAlert(String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("שגיאה");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
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
