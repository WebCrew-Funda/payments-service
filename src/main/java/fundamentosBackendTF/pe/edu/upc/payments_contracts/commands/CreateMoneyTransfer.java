package fundamentosBackendTF.pe.edu.upc.payments_contracts.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Value
public class CreateMoneyTransfer {
    @TargetAggregateIdentifier
    private String paymentId;
    private String postId;
    private BigDecimal number;
    private BigDecimal overdraftLimit;
}
