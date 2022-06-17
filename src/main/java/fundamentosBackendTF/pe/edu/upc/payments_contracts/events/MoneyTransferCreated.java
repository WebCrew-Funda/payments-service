package fundamentosBackendTF.pe.edu.upc.payments_contracts.events;

import lombok.Value;

import java.math.BigDecimal;
import java.time.Instant;

@Value
public class MoneyTransferCreated {
    private String transactionId;
    private String fromAccountId;
    private String toAccountId;
    private BigDecimal amount;
    private Instant occurredOn;
}
