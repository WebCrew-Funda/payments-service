package fundamentosBackendTF.pe.edu.upc.payments.command.application.dto.response;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class EditPaymentResponse {
    private String paymentId;
    private BigDecimal number;
    private BigDecimal overdraftLimit;
}
