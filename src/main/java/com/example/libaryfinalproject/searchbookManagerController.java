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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class searchbookManagerController{

    @FXML
    private TextField bookTitleTextFieldManager;

    @FXML
    private TextField authorTextField;

    @FXML
    private TableView<Book> booksTableView;

// Your TableColumn definitions here

    @FXML
    public void searchBooks() {
        String title = bookTitleTextFieldManager.getText().trim();
        String author = authorTextField.getText().trim();

        // Construct your search request
        HashMap<String, Object> searchParams = new HashMap<>();
        searchParams.put("title", title);
        searchParams.put("author", author);
        Request request = new Request("searchBooks", searchParams);

        // Send the request and receive response (List<Book> or similar)
        List<Book> searchResults = Client.sendRequestAndGetResponse(request);

        // Update the TableView
        ObservableList<Book> booksObservableList = FXCollections.observableArrayList(searchResults);
        Platform.runLater(() -> booksTableView.setItems(booksObservableList));
    }

    @FXML
    private TableColumn<Book, String> bookTitleColumn;

    @FXML
    private TableColumn<Book, String> authorColumn;

    public void initialize() {
        bookTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        // Initialize your TableView with empty data or default data
        booksTableView.setItems(FXCollections.observableArrayList());
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