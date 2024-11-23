package lk.ijse.studentmanagementsystem.dao.custom.daoImpl;

import lk.ijse.studentmanagementsystem.config.SessionFactoryConfig;
import lk.ijse.studentmanagementsystem.dao.custom.UserDAO;
import lk.ijse.studentmanagementsystem.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(User user) throws Exception {
        try(Session session = SessionFactoryConfig.getInstance().getSession()){
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(User entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public User search(String id) throws Exception {
        return null;
    }

    @Override
    public ArrayList<User> getAll() throws Exception {
        return null;
    }

    @Override
    public ArrayList<String> loadIds() throws Exception {
        return null;
    }
}