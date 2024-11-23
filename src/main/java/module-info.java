module lk.ijse.studentmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.j;
    requires org.hibernate.orm.core;
    requires com.jfoenix;
    requires jakarta.persistence;
    requires static lombok;
    requires java.naming;

    opens lk.ijse.studentmanagementsystem.entity to org.hibernate.orm.core;
    opens lk.ijse.studentmanagementsystem.controller to javafx.fxml;
    exports lk.ijse.studentmanagementsystem;
    exports lk.ijse.studentmanagementsystem.controller;

}