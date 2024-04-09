module com.example.libaryfinalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens com.example.libaryfinalproject.model to javafx.fxml,javafx.base ,com.google.gson;

    exports com.example.libaryfinalproject;
    opens com.example.libaryfinalproject to com.google.gson, javafx.base, javafx.fxml;

    // Other required modules

    // Opens the package containing Book class to JavaFX modules


}