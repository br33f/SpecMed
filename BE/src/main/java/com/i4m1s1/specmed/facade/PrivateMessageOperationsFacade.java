package com.i4m1s1.specmed.facade;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.core.dict.WarningMsg;
import com.i4m1s1.specmed.core.helper.DateHelper;
import com.i4m1s1.specmed.persistence.Customer;
import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.persistence.PrivateMessage;
import com.i4m1s1.specmed.repository.CustomerRepository;
import com.i4m1s1.specmed.repository.MedicalEmployeeRepository;
import com.i4m1s1.specmed.repository.PrivateMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@Component
public class PrivateMessageOperationsFacade {

    @Autowired
    private PrivateMessageRepository messageRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MedicalEmployeeRepository medicalEmployeeRepository;

    /**
     * @param customerId        id nadawcy wiadomości
     * @param medicalEmployeeId id odbiorcy wiadomości
     * @param content           zawartość wiadomości
     * @return zapisany obiekt typu {@link PrivateMessage}
     */
    public PrivateMessage saveMessageFromCustomer(String customerId, String medicalEmployeeId, String content) throws SMException {
        PrivateMessage pm = preparePrivateMessage(customerId, medicalEmployeeId, content);
        pm.setCustomerSender(true);
        messageRepository.save(pm);
        return pm;
    }

    /**
     * @param customerId        id odbiorcy wiadomości
     * @param medicalEmployeeId id nadawcy wiadomości
     * @param content           zawartość wiadomości
     * @return zapisany obiekt typu {@link PrivateMessage}
     * @throws SMException
     */
    public PrivateMessage saveMessageFromMedicalEmployee(String customerId, String medicalEmployeeId, String content) throws SMException {
        PrivateMessage pm = preparePrivateMessage(customerId, medicalEmployeeId, content);
        pm.setCustomerSender(true);
        messageRepository.save(pm);
        return pm;
    }

    /**
     * @param customerId        id pacjenta
     * @param medicalEmployeeId id lekarza
     * @param content           zawartość wiadomości
     * @return częsciowo utworzona wiadomość typu {@link PrivateMessage}
     * @throws SMException gdy brak pacjenta lub lekarza o podanych ID
     */
    private PrivateMessage preparePrivateMessage(String customerId, String medicalEmployeeId, String content) throws SMException {
        Customer whoSend = customerRepository.findById(customerId);
        if (whoSend == null) {
            throw new SMException("20171219124125", WarningMsg.DB_NO_RESULTS);
        }
        MedicalEmployee forWhom = medicalEmployeeRepository.findById(medicalEmployeeId);
        if (forWhom == null) {
            throw new SMException("20171219124305", WarningMsg.DB_NO_RESULTS);
        }
        PrivateMessage pm = new PrivateMessage();
        pm.setContent(content);
        pm.setCustomer(whoSend);
        pm.setMedicalEmployee(forWhom);
        pm.setSendTime(DateHelper.getCurrentDateAsLong());
        return pm;
    }
}
