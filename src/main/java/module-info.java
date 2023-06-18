module com.example.projects {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.projects to javafx.fxml;
    exports com.example.projects;
}