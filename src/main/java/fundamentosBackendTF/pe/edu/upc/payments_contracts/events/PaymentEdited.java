package fundamentosBackendTF.pe.edu.upc.payments_contracts.events;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.time.Instant;

@Value
public class PaymentEdited {
    private String paymentId;
    private BigDecimal number;
    private BigDecimal overdraftLimit;
    private Instant occurredOn;
}
