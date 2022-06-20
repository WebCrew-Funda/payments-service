package fundamentosBackendTF.pe.edu.upc.payments_contracts.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Value
public class EditPayment {
    @TargetAggregateIdentifier
    private String paymentId;
    private BigDecimal number;
    private BigDecimal overdraftLimit;
}
