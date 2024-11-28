package lk.ijse.studentmanagementsystem.dao.custom;

import lk.ijse.studentmanagementsystem.dao.CrudDAO;
import lk.ijse.studentmanagementsystem.entity.Payment;
import org.hibernate.Session;

public interface PaymentDAO extends CrudDAO<Payment,String> {
    boolean savePayment(Payment payment, Session session) throws Exception;
}
