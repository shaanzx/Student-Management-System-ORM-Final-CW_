package lk.ijse.studentmanagementsystem.service.custom.boImpl;

import lk.ijse.studentmanagementsystem.dao.DAOFactory;
import lk.ijse.studentmanagementsystem.dao.custom.CourseDAO;
import lk.ijse.studentmanagementsystem.dto.CourseDTO;
import lk.ijse.studentmanagementsystem.entity.Course;
import lk.ijse.studentmanagementsystem.service.custom.CourseBO;

import java.time.Year;
import java.util.ArrayList;

public class CourseBOImpl implements CourseBO {

    CourseDAO courseDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.COURSE);

    @Override
    public boolean saveCourse(CourseDTO courseDTO) throws Exception {
        return courseDAO.save(new Course(
                courseDTO.getCourseId(),
                courseDTO.getCourseName(),
                courseDTO.getCourseSeats(),
                courseDTO.getCourseDescription(),
                courseDTO.getCourseDuration(),
                courseDTO.getCourseFee()
        ));
    }

    @Override
    public boolean updateCourse(CourseDTO courseDTO) throws Exception {
        return courseDAO.update(new Course(
                courseDTO.getCourseId(),
                courseDTO.getCourseName(),
                courseDTO.getCourseSeats(),
                courseDTO.getCourseDescription(),
                courseDTO.getCourseDuration(),
                courseDTO.getCourseFee()
        ));
    }

    @Override
    public boolean deleteCourse(String courseId) throws Exception {
        return courseDAO.delete(courseId);
    }

    @Override
    public CourseDTO searchCourse(String courseId) throws Exception {
        Course course = courseDAO.search(courseId);
        return new CourseDTO(
                course.getCourseId(),
                course.getCourseName(),
                course.getCourseSeats(),
                course.getCourseDescription(),
                course.getCourseDuration(),
                course.getCourseFee()
        );
    }

    @Override
    public ArrayList<CourseDTO> getAllCourses() throws Exception {
        ArrayList<CourseDTO> courseDTOS = new ArrayList<>();
        ArrayList<Course> courses = courseDAO.getAll();
        for (Course course : courses) {
            courseDTOS.add(new CourseDTO(
                    course.getCourseId(),
                    course.getCourseName(),
                    course.getCourseSeats(),
                    course.getCourseDescription(),
                    course.getCourseDuration(),
                    course.getCourseFee()
            ));
        }
        return courseDTOS;
    }

    @Override
    public boolean loadIds(String id) throws Exception {
        return false;
    }

    @Override
    public String generateNextCourseId() throws Exception {
        String lastId = courseDAO.getLastId();
        return IncrementCourseId(lastId);
    }

    @Override
    public boolean isCourseExists(String CourseId) throws Exception {
        return courseDAO.isCourseExists(CourseId);
    }

    private String IncrementCourseId(String lastId) {
        // Get the current year dynamically
        String currentYear = String.valueOf(Year.now().getValue());

        // If there's no last ID, return the first course ID
        if (lastId == null || lastId.isEmpty()) {
            return String.format("REG-COOK-%s-0001", currentYear);
        }

        // Split the last ID to extract the year and sequence number
        String[] parts = lastId.split("-");
        String lastYear = parts[2]; // The year part in the ID
        int lastSequence = Integer.parseInt(parts[3]); // The sequence number

        // Check if the year has changed
        if (!lastYear.equals(currentYear)) {
            // Reset the sequence if the year has changed
            return String.format("REG-COOK-%s-0001", currentYear);
        }

        // Increment the sequence number for the current year
        lastSequence++;
        return String.format("REG-COOK-%s-%04d", currentYear, lastSequence);
    }
}
