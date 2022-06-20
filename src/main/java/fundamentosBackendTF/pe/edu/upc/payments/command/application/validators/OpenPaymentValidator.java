package fundamentosBackendTF.pe.edu.upc.payments.command.application.validators;

import fundamentosBackendTF.pe.edu.upc.common.application.Notification;
import fundamentosBackendTF.pe.edu.upc.payments.command.application.dto.request.OpenPaymentRequest;
import fundamentosBackendTF.pe.edu.upc.payments.command.application.infra.PaymentNumber;
import fundamentosBackendTF.pe.edu.upc.payments.command.application.infra.PaymentNumberRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class OpenPaymentValidator {
    private final PaymentNumberRepository paymentNumberRepository;
    public OpenPaymentValidator(PaymentNumberRepository paymentNumberRepository){
        this.paymentNumberRepository = paymentNumberRepository;
    }
    public Notification validate(OpenPaymentRequest openPaymentRequest){
        Notification notification = new Notification();
        String number= openPaymentRequest.getNumber().toString();
        if (number.isEmpty()) {
            notification.addError("Payment number is required");
        }
        String postId = openPaymentRequest.getPostId().trim();
        if(postId.isEmpty()){
            notification.addError("Post id is required");
        }
        BigDecimal overdraftLimit = openPaymentRequest.getOverdraftLimit();
        if (overdraftLimit.doubleValue() < 0) {
            notification.addError("Account overdraftLimit must be greater or equal than zero");
        }
        if (notification.hasErrors()) {
            return notification;
        }
        return notification;
    }
}
