package fundamentosBackendTF.pe.edu.upc.payments_contracts.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Value
public class WithdrawPayment {
    @TargetAggregateIdentifier
    private String paymentId;
    private String transactionId;
    private BigDecimal amount;
}
