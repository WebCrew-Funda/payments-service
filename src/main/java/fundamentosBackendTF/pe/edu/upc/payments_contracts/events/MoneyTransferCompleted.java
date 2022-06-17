package fundamentosBackendTF.pe.edu.upc.payments_contracts.events;

import lombok.Value;

import java.time.Instant;

@Value
public class MoneyTransferCompleted {
    private String transactionId;
    private Instant occurredOn;
}
