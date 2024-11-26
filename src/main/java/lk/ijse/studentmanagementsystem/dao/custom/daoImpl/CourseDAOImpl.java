package lk.ijse.studentmanagementsystem.dao.custom.daoImpl;

import lk.ijse.studentmanagementsystem.config.SessionFactoryConfig;
import lk.ijse.studentmanagementsystem.dao.custom.CourseDAO;
import lk.ijse.studentmanagementsystem.entity.Course;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class CourseDAOImpl implements CourseDAO {
    @Override
    public String getLastId() throws Exception {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            String hql = "SELECT c.courseId FROM Course c ORDER BY c.courseId DESC";
            Query<String> query = session.createQuery(hql, String.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean save(Course entity) throws Exception {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
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
    public boolean update(Course entity) throws Exception {
        try {
            Session session = SessionFactoryConfig.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public Course search(String id) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Course> getAll() throws Exception {
        ArrayList<Course> courses = new ArrayList<>();
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            courses = (ArrayList<Course>) session.createQuery("FROM Course").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }

    @Override
    public ArrayList<String> loadIds() throws Exception {
        return null;
    }
}
