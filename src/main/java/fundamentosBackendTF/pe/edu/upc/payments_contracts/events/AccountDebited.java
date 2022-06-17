package fundamentosBackendTF.pe.edu.upc.payments_contracts.events;

import lombok.Value;
import java.math.BigDecimal;
import java.time.Instant;

@Value
public class AccountDebited {
    private String accountId;
    private String transactionId;
    private BigDecimal amount;
    private Instant occurredOn;
}