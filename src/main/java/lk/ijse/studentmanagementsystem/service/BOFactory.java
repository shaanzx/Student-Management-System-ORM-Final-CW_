package lk.ijse.studentmanagementsystem.service;

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
        STUDENT, COURSE, USER, REGISTRATION
    }

    public <T> T getBO(BOType boType) {
        switch (boType) {
            case STUDENT:
                return (T) new StudentBOImpl();
//            case COURSE:
//                return (T) new CourseBOImpl();
            case USER:
                return (T) new UserBOImpl();
//            case REGISTRATION:
//                return (T) new RegistrationBOImpl();
            default:
                return null;
        }
    }
}
