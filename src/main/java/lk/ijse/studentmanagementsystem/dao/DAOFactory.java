package lk.ijse.studentmanagementsystem.dao;

import lk.ijse.studentmanagementsystem.dao.custom.daoImpl.StudentDAOImpl;

public class DAOFactory {

    private static DAOFactory daoFactory;

    public DAOFactory() {}

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOType {
        USER, STUDENT, COURSE, PAYMENT, PAYMENT_TYPE
    }

    public <T extends SuperDAO> T getDAO(DAOType daoType) {
        switch (daoType) {
//            case USER:
//                return (T) new UserDAOImpl();
            case STUDENT:
                return (T) new StudentDAOImpl();
//            case COURSE:
//                return (T) new CourseDAOImpl();
//            case PAYMENT:
//                return (T) new PaymentDAOImpl();
//            case PAYMENT_TYPE:
//                return (T) new PaymentTypeDAOImpl();
            default:
                return null;
        }
    }
}
