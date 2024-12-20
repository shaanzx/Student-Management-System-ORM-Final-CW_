package lk.ijse.studentmanagementsystem.dao.custom.daoImpl;

import lk.ijse.studentmanagementsystem.config.SessionFactoryConfig;
import lk.ijse.studentmanagementsystem.dao.custom.RegisterDAO;
import lk.ijse.studentmanagementsystem.entity.Register;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class RegisterDAOImpl implements RegisterDAO {
    @Override
    public String getLastId() throws Exception {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            String hql = "SELECT r.registerId FROM Register r ORDER BY r.registerId DESC";
            Query<String> query = session.createQuery(hql, String.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean save(Register entity) throws Exception {
        try {
            Session session = SessionFactoryConfig.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Register entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public Register search(String id) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Register> getAll() throws Exception {
        return null;
    }

    @Override
    public ArrayList<String> loadIds() throws Exception {
        return null;
    }

    @Override
    public boolean saveRegister(List<Register> registers, Session session) throws Exception {
        try {
            for (Register register : registers) {
                session.save(register);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Register searchByCourseId(String courseId, Session session) throws Exception {
        try {
            String hql = "SELECT r FROM Register r WHERE r.courseId = :courseId";
            Query<Register> query = session.createQuery(hql, Register.class);
            query.setParameter("courseId", courseId);
            return query.uniqueResult();
        } catch (Exception e) {
            return null;
        }
    }
}
