package fundamentosBackendTF.pe.edu.upc.payments_contracts.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import java.math.BigDecimal;

@Value
public class DeleteMoneyDeposit {
    @TargetAggregateIdentifier
    private String accountId;

}