package fundamentosBackendTF.pe.edu.upc.payments_contracts.events;

import lombok.Value;
import java.math.BigDecimal;
import java.time.Instant;

@Value
public class AccountOpened {
    private String accountId;
    private String number;
    private BigDecimal overdraftLimit;
    private String customerId;
    private Instant occurredOn;
}