package lk.ijse.studentmanagementsystem.dao.custom.daoImpl;

import lk.ijse.studentmanagementsystem.dao.custom.PaymentDAO;
import lk.ijse.studentmanagementsystem.entity.Payment;

import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public String getLastId() throws Exception {
        return "";
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
