package lk.ijse.studentmanagementsystem.service.custom.boImpl;

import lk.ijse.studentmanagementsystem.dao.DAOFactory;
import lk.ijse.studentmanagementsystem.dao.custom.PaymentDAO;
import lk.ijse.studentmanagementsystem.service.custom.PaymentBo;

import java.time.Year;

public class PaymentBOImpl implements PaymentBo {

    PaymentDAO paymentDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.PAYMENT);

    @Override
    public String generateNextPurchaseId() throws Exception {
        String lastId = paymentDAO.getLastId();
        return incrementPurchaseId(lastId);
    }

    private String incrementPurchaseId(String lastId) {
        // Get the current year dynamically
        String currentYear = String.valueOf(Year.now().getValue());

        // If there's no last ID, return the first course ID
        if (lastId == null || lastId.isEmpty()) {
            return String.format("PSCD-%s-0001", currentYear);
        }

        // Split the last ID to extract the year and sequence number
        String[] parts = lastId.split("-");
        String lastYear = parts[2]; // The year part in the ID
        int lastSequence = Integer.parseInt(parts[3]); // The sequence number

        // Check if the year has changed
        if (!lastYear.equals(currentYear)) {
            // Reset the sequence if the year has changed
            return String.format("PSCD-%s-0001", currentYear);
        }

        // Increment the sequence number for the current year
        lastSequence++;
        return String.format("PSCD-%s-%04d", currentYear, lastSequence);
    }
}
