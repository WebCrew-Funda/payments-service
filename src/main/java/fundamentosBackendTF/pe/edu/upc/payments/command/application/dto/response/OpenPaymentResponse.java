package fundamentosBackendTF.pe.edu.upc.payments.command.application.dto.response;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class OpenPaymentResponse {
    private String paymentId;
    private BigDecimal number;
    private BigDecimal balance;
    private BigDecimal overdraftLimit;
    private String postId;
}
