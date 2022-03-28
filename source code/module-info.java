module com.sklep.sklepinternetowy {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens login to javafx.fxml;
    exports login;
    exports alerts;
    opens alerts to javafx.fxml;
    exports screenchanger;
    opens screenchanger to javafx.fxml;
    exports inputchecker;
    opens inputchecker to javafx.fxml;
    exports sqlmanagement;
    opens sqlmanagement to javafx.fxml;
    exports createaccount;
    opens createaccount to javafx.fxml;
    exports clientdashboard;
    opens clientdashboard to javafx.fxml;
    exports products;
    opens products to javafx.base;
    exports users;
    opens users to javafx.base;
    exports admindashboard;
    opens admindashboard to javafx.fxml;
    exports logistics;
    opens logistics to javafx.base;
    exports orders;
    opens orders to javafx.base;
}