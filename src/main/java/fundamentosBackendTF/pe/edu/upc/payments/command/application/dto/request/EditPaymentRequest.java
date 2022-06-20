package fundamentosBackendTF.pe.edu.upc.payments.command.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class EditPaymentRequest {
    private @Setter @Getter String paymentId;
    private @Setter @Getter BigDecimal number;
    private @Getter BigDecimal overdraftLimit;
}
