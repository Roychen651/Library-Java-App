package com.example.libaryfinalproject;

import com.example.libaryfinalproject.model.Book;
import com.example.libaryfinalproject.model.Request;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class editbookController {

    @FXML
    private TextField AuthorTextField;
    @FXML
    private TextField TitleTextField;
    @FXML
    private TableView<Book> tableViewBooks;
    @FXML
    private TableColumn<Book, Long> idColumn;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, Integer> yearColumn;


    private ObservableList<Book> books = FXCollections.observableArrayList();
    public void fetchAllBooks() {
        Platform.runLater(() -> {
            try {
                Request request = new Request("getAllBooks", new HashMap<>());
                List<Book> bookList = Client.sendRequestAndGetResponse(request);
                System.out.println(bookList);
                // Clear the current books and add all from bookList
                books.clear();
                books.addAll(bookList);

                // This operation updates the UI and is already wrapped in Platform.runLater
                tableViewBooks.setItems(books);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        fetchAllBooks();
    }

    @FXML
    private void onEditBookButtonClick() {
        Book selectedBook = tableViewBooks.getSelectionModel().getSelectedItem();
        if (selectedBook == null) {
            showAlert("שגיאת עריכה", "אנא בחר ספר לעריכה.");
            return;
        }

        String newTitle = TitleTextField.getText().trim();
        String newAuthor = AuthorTextField.getText().trim();
        if (newTitle.isEmpty() || newAuthor.isEmpty()) {
            showAlert("שגיאת עריכה", "אנא מלא את כל השדות.");
            return;
        }

        // Construct and send the update request to the server
        HashMap<String, Object> body = new HashMap<>();
        body.put("bookId", selectedBook.getId());
        body.put("newTitle", newTitle);
        body.put("newAuthor", newAuthor);

        Request request = new Request("editBook", body);
        boolean success = Client.sendRequestAndGetResponse2(request); // Assume this returns true on success

        if (success) {
            // Update the selected book's details
            selectedBook.setTitle(newTitle);
            selectedBook.setAuthor(newAuthor);

            // Refresh the TableView
            tableViewBooks.refresh();

            System.out.println("Successfully updated the book.");
        } else {
            System.out.println("Failed to update the book.");
        }
    }
    private void showAlert(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("שגיאה");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    @FXML
    public void onbackButtonClick(ActionEvent event) throws IOException {

        // Load the second view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("manager-view.fxml"));
        Parent managerView = loader.load();

        // Get the current stage from the event
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new scene on the current stage
        stage.setScene(new Scene(managerView));



        // Show the new view
        stage.show();


    }
}
