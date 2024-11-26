package lk.ijse.studentmanagementsystem.service;

import lk.ijse.studentmanagementsystem.service.custom.PaymentBo;
import lk.ijse.studentmanagementsystem.service.custom.boImpl.*;

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
                return (T) new Student_CourseBOImpl();
            case PAYMENT:
                return (T) new PaymentBOImpl();
            default:
                return null;
        }
    }
}
