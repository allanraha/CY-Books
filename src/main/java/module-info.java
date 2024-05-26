module com.example.cybooks {
    requires javafx.controls;
    requires javafx.fxml;
                            
    opens com.example.cybooks to javafx.fxml;
    exports com.example.cybooks;
    exports com.example.cybooks.controllers;
    opens com.example.cybooks.controllers to javafx.fxml;
    opens com.example.cybooks.views to javafx.fxml;
    opens com.example.cybooks.models to javafx.base;

    requires java.sql;
    requires java.net.http;
    requires com.jfoenix;
}
