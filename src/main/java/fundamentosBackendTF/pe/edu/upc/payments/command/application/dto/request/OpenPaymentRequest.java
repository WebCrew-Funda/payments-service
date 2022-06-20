package fundamentosBackendTF.pe.edu.upc.payments.command.application.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class OpenPaymentRequest {
    @Getter @NotNull
    private BigDecimal number;
    private @Getter BigDecimal overdraftLimit;
    @Getter @NotNull
    private String postId;
}
