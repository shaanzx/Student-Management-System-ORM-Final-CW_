package lk.ijse.studentmanagementsystem.service;

import lk.ijse.studentmanagementsystem.service.custom.boImpl.CourseBOImpl;
import lk.ijse.studentmanagementsystem.service.custom.boImpl.StudentBOImpl;
import lk.ijse.studentmanagementsystem.service.custom.boImpl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public enum BOType {
        STUDENT, COURSE, USER, STUDENT_COURSE, PAYMENT
    }

    public <T> T getBO(BOType boType) {
        switch (boType) {
            case STUDENT:
                return (T) new StudentBOImpl();
            case COURSE:
                return (T) new CourseBOImpl();
            case USER:
                return (T) new UserBOImpl();
            case STUDENT_COURSE:
                return (T) new StudentBOImpl();
            case PAYMENT:
                return (T) new StudentBOImpl();
            default:
                return null;
        }
    }
}
