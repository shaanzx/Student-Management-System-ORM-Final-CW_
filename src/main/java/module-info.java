module lk.ijse.studentmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.j;
    requires org.hibernate.orm.core;


    opens lk.ijse.studentmanagementsystem to javafx.fxml;
    exports lk.ijse.studentmanagementsystem;
}