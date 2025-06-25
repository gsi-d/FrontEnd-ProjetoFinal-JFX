module br.edu.unifebe.aulafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens Controllers to javafx.fxml;
    exports Controllers;
}