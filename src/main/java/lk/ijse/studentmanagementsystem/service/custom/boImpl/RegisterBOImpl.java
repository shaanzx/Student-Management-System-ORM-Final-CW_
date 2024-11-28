package lk.ijse.studentmanagementsystem.service.custom.boImpl;

import lk.ijse.studentmanagementsystem.config.SessionFactoryConfig;
import lk.ijse.studentmanagementsystem.dao.DAOFactory;
import lk.ijse.studentmanagementsystem.dao.custom.CourseDAO;
import lk.ijse.studentmanagementsystem.dao.custom.PaymentDAO;
import lk.ijse.studentmanagementsystem.dao.custom.RegisterDAO;
import lk.ijse.studentmanagementsystem.dto.CourseDTO;
import lk.ijse.studentmanagementsystem.dto.PaymentDTO;
import lk.ijse.studentmanagementsystem.dto.RegisterDTO;
import lk.ijse.studentmanagementsystem.entity.Payment;
import lk.ijse.studentmanagementsystem.entity.Register;
import lk.ijse.studentmanagementsystem.service.custom.RegisterBO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class RegisterBOImpl implements RegisterBO {

    RegisterDAO registerDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.REGISTER);
    PaymentDAO paymentDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.PAYMENT);
    CourseDAO courseDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.COURSE);


    @Override
    public String generateNextPurchaseId() throws Exception {
        String lastId = paymentDAO.getLastId();
        return incrementPurchaseId(lastId);
    }

    @Override
    public boolean addRegisterDetails(List<RegisterDTO> registerDTOS) throws Exception {
        // Convert RegisterDTO list to Register entity list
        List<Register> registers = new ArrayList<>();
        for (RegisterDTO registerDTO : registerDTOS) {
            Register register = new Register(
                    registerDTO.getRegisterId(),
                    registerDTO.getRegisterDate(),
                    registerDTO.getExpiredDate(),
                    registerDTO.getStudentId(),
                    registerDTO.getCourseId()
            );
            registers.add(register);
        }

        // Initialize session and transaction
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                // Save all Register entities
                boolean isSaved = registerDAO.saveRegister(registers, session);
                if (!isSaved) {
                    transaction.rollback();
                    return false;
                }
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                throw new RuntimeException("Failed to save register details", e);
            }
        } catch (Exception e) {
            throw new RuntimeException("Session management error", e);
        }
    }

    @Override
    public boolean savePayment(PaymentDTO paymentDTO) throws Exception {
        return false;
    }

    @Override
    public RegisterDTO searchRegister(String courseId) {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Register register = registerDAO.searchByCourseId(courseId, session);
            if (register != null) {
                RegisterDTO registerDTO = new RegisterDTO(
                        register.getRegisterId(),
                        register.getRegisterDate(),
                        register.getExpiredDate(),
                        register.getStudent(),
                        register.getCourseId()
                );
                return registerDTO;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public boolean addTransaction(PaymentDTO paymentDTO, List<RegisterDTO> registerDTOS, List<CourseDTO> courseDTOS) throws Exception {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                boolean paymentAdded = paymentDAO.savePayment(
                        new Payment(
                                paymentDTO.getPaymentId(),
                                paymentDTO.getPaymentDate(),
                                paymentDTO.getPaymentAmount(),
                                paymentDTO.getAdvanceAmount(),
                                paymentDTO.getBalanceAmount(),
                                paymentDTO.getRegisterId()
                        )
                        , session);
                if (!paymentAdded) {
                    transaction.rollback();
                    return false;
                }
                for (CourseDTO courseDTO : courseDTOS) {
                    boolean isUpdated = courseDAO.updateCourseSeats(courseDTO.getCourseId(), session);
                    if (!isUpdated) {
                        transaction.rollback();
                        return false;
                    }
                }
                transaction.commit();
                return true;
            } catch (Exception e) {
                if (session.isOpen() && transaction != null) {
                    transaction.rollback();
                }
                throw new RuntimeException("Failed to add enrollment", e);
            }
        }
    }


    private String incrementPurchaseId(String lastId) {
        String currentYear = String.valueOf(Year.now().getValue());

        if (lastId == null || lastId.isEmpty()) {
            return String.format("PSCD-%s-0001", currentYear);
        }

        String[] parts = lastId.split("-");
        String lastYear = parts[2];
        int lastSequence = Integer.parseInt(parts[3]);

        if (!lastYear.equals(currentYear)) {
            return String.format("PSCD-%s-0001", currentYear);
        }

        lastSequence++;
        return String.format("PSCD-%s-%04d", currentYear, lastSequence);
    }
    @Override
    public String generateNextRegisterId() throws Exception {
        String lastId = registerDAO.getLastId();
        return IncrementCourseId(lastId);
    }

    private String IncrementCourseId(String lastId) {
        String currentYear = String.valueOf(Year.now().getValue());

        if (lastId == null || lastId.isEmpty()) {
            return String.format("STU-COU-%s-0001", currentYear);
        }

        String[] parts = lastId.split("-");
        String lastYear = parts[2];
        int lastSequence = Integer.parseInt(parts[3]);

        if (!lastYear.equals(currentYear)) {
            return String.format("STU-COU-%s-0001", currentYear);
        }

        lastSequence++;
        return String.format("STU-COU-%s-%04d", currentYear, lastSequence);
    }
}
