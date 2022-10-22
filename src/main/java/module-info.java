module exam.ism {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens exam.ism.controllers to javafx.fxml;
    exports exam.ism;
    exports exam.ism.entities;
}
