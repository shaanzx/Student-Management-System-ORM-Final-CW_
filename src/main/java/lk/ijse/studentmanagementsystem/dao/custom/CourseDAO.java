package lk.ijse.studentmanagementsystem.dao.custom;

import lk.ijse.studentmanagementsystem.dao.CrudDAO;
import lk.ijse.studentmanagementsystem.entity.Course;
import org.hibernate.Session;

public interface CourseDAO extends CrudDAO<Course,String> {
    boolean isCourseExists(String courseId) throws Exception;

    boolean updateCourseSeats(String courseId, Session session);
}
