module lk.ijse.studentmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.j;
    requires org.hibernate.orm.core;
    requires com.jfoenix;


    opens lk.ijse.studentmanagementsystem.controller to javafx.fxml;
    exports lk.ijse.studentmanagementsystem;


}