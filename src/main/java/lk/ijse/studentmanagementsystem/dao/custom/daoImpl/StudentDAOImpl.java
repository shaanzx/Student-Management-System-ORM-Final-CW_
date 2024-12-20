package lk.ijse.studentmanagementsystem.dao.custom.daoImpl;

import lk.ijse.studentmanagementsystem.config.SessionFactoryConfig;
import lk.ijse.studentmanagementsystem.dao.custom.StudentDAO;
import lk.ijse.studentmanagementsystem.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public String getLastId() throws Exception {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            String hql = "SELECT s.studentId FROM Student s ORDER BY s.studentId DESC";
            Query<String> query = session.createQuery(hql, String.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean save(Student student) throws Exception {
        try(Session session = SessionFactoryConfig.getInstance().getSession()){
            Transaction transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Student entity) throws Exception {
        try(Session session = SessionFactoryConfig.getInstance().getSession()){
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
        try(Session session = SessionFactoryConfig.getInstance().getSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(search(id));
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Student> getAll() throws Exception {
        ArrayList<Student> students = new ArrayList<>();
        try(Session session = SessionFactoryConfig.getInstance().getSession()){
            students = (ArrayList<Student>) session.createQuery("FROM Student").list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public ArrayList<String> loadIds() throws Exception {
        return null;
    }

    @Override
    public Student search(String id) throws Exception {
        try(Session session = SessionFactoryConfig.getInstance().getSession()){
            return session.get(Student.class, id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Student searchByNic(String studentNIC) throws Exception {
        try(Session session = SessionFactoryConfig.getInstance().getSession()){
            String hql = "FROM Student s WHERE s.studentNic = :studentNic";
            Query<Student> query = session.createQuery(hql, Student.class);
            query.setParameter("studentNic", studentNIC);
            return query.uniqueResult();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean checkStudent(String studentNIC) {
        try(Session session = SessionFactoryConfig.getInstance().getSession()){
            String hql = "FROM Student s WHERE s.studentNic = :studentNic";
            Query<Student> query = session.createQuery(hql, Student.class);
            query.setParameter("studentNic", studentNIC);
            return query.uniqueResult() != null;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
