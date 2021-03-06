package fundamentosBackendTF.pe.edu.upc.payments_contracts.events;

import lombok.Value;

import java.math.BigDecimal;
import java.time.Instant;

@Value
public class MoneyTransferCreated {
    private String paymentId;
    private String postId;
    private BigDecimal number;
    private Instant occurredOn;
}
