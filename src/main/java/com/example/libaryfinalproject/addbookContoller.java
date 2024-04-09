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
import javafx.scene.control.Alert;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class addbookContoller {



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

                books.clear();
                books.addAll(bookList);

                tableViewBooks.setItems(books);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    @FXML
    private TextField bookNameTextField, authorTextField, yearTextField, idTextField;

    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        fetchAllBooks();
    }

    @FXML
    public void addBook() {
        String idStr = idTextField.getText().trim();
        String title = bookNameTextField.getText().trim();
        String author = authorTextField.getText().trim();
        String yearStr = yearTextField.getText().trim();

        if (idStr.isEmpty() || title.isEmpty() || author.isEmpty() || yearStr.isEmpty()) {
            showAlert("Missing Information", "All fields are required. Please enter all details.");
            return;
        }

        try {
            long id = Long.parseLong(idStr);
            int year = Integer.parseInt(yearStr);

            Book book = new Book(id, title, author, year);
            System.out.println("Book added: " + book);

            clearInputFields();

            books.add(book);
            Map<String, Object> reqMap = new HashMap<>();
            reqMap.put("book", book);
            Request request = new Request("addBook", reqMap);
            Client.saveToServer(request);

        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter valid numbers for ID and Year.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Clears the input fields.
     */
    private void clearInputFields() {
        idTextField.clear();
        bookNameTextField.clear();
        authorTextField.clear();
        yearTextField.clear();
    }

    /**
     * Shows an alert dialog to the user.
     * @param headerText The header text of the alert.
     * @param contentText The content text (main message) of the alert.
     */
    private void showAlert(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    @FXML
    public void onbackButtonClick(ActionEvent event) throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("manager-view.fxml"));
        Parent managerView = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(managerView));

        stage.show();

    }




}
