package lk.ijse.studentmanagementsystem.service.custom.boImpl;

import lk.ijse.studentmanagementsystem.dao.DAOFactory;
import lk.ijse.studentmanagementsystem.dao.custom.CourseDAO;
import lk.ijse.studentmanagementsystem.dao.custom.PaymentDAO;
import lk.ijse.studentmanagementsystem.dao.custom.RegisterDAO;
import lk.ijse.studentmanagementsystem.dto.PaymentDTO;
import lk.ijse.studentmanagementsystem.dto.RegisterDTO;
import lk.ijse.studentmanagementsystem.entity.Payment;
import lk.ijse.studentmanagementsystem.entity.Register;
import lk.ijse.studentmanagementsystem.service.custom.RegisterBO;

import java.time.Year;
import java.util.List;

public class RegisterBOImpl implements RegisterBO {

    RegisterDAO registerDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.REGISTER);
    PaymentDAO paymentDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.PAYMENT);
    CourseDAO courseDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.COURSE);


    @Override
    public boolean savePayment(PaymentDTO paymentDTO) throws Exception {
        return paymentDAO.save(new Payment(
                paymentDTO.getPaymentId(),
                paymentDTO.getPaymentDate(),
                paymentDTO.getPaymentAmount(),
                paymentDTO.getAdvanceAmount(),
                paymentDTO.getBalanceAmount(),
                paymentDTO.getRegisterId()
        ));
    }

    @Override
    public String generateNextPurchaseId() throws Exception {
        String lastId = paymentDAO.getLastId();
        return incrementPurchaseId(lastId);
    }

    @Override
    public boolean saveRegister(RegisterDTO registerDTO) throws Exception {
        return registerDAO.save(new Register(
                registerDTO.getRegisterId(),
                registerDTO.getRegisterDate(),
                registerDTO.getExpiredDate(),
                registerDTO.getStudentId(),
                registerDTO.getCourseId()
        ));
    }

    @Override
    public boolean updateCourseSeats(String courseId) throws Exception {
        return courseDAO.updateSeats(courseId);
    }

    @Override
    public boolean addRegisterCourseDetails(List<RegisterDTO> registerDTOS, PaymentDTO paymentDTO, String courseId) {

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
    public String generateNextStudentCourseId() throws Exception {
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
