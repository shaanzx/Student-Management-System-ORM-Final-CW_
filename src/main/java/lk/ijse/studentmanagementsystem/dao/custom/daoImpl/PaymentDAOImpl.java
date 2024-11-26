package lk.ijse.studentmanagementsystem.dao.custom.daoImpl;

import lk.ijse.studentmanagementsystem.config.SessionFactoryConfig;
import lk.ijse.studentmanagementsystem.dao.custom.PaymentDAO;
import lk.ijse.studentmanagementsystem.entity.Payment;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public String getLastId() throws Exception {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            String hql = "SELECT p.paymentId FROM Payment p ORDER BY p.paymentId DESC";
            Query<String> query = session.createQuery(hql, String.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean save(Payment entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(Payment entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public Payment search(String id) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Payment> getAll() throws Exception {
        return null;
    }

    @Override
    public ArrayList<String> loadIds() throws Exception {
        return null;
    }
}
