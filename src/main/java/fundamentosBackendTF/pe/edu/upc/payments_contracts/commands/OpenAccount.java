package fundamentosBackendTF.pe.edu.upc.payments_contracts.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import javax.persistence.Column;
import java.math.BigDecimal;

@Value
public class OpenAccount {
    @TargetAggregateIdentifier
    private String accountId;
    private String number;
    private BigDecimal overdraftLimit;
    private String customerId;
}