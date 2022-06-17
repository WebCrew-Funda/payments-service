package fundamentosBackendTF.pe.edu.upc.payments.command.domain;

import fundamentosBackendTF.pe.edu.upc.payments_contracts.commands.CreateMoneyTransfer;
import fundamentosBackendTF.pe.edu.upc.payments_contracts.commands.MarkTransferAsCompleted;
import fundamentosBackendTF.pe.edu.upc.payments_contracts.commands.MarkTransferAsFailed;
import fundamentosBackendTF.pe.edu.upc.payments_contracts.events.MoneyTransferCompleted;
import fundamentosBackendTF.pe.edu.upc.payments_contracts.events.MoneyTransferCreated;
import fundamentosBackendTF.pe.edu.upc.payments_contracts.events.MoneyTransferFailed;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import java.math.BigDecimal;
import java.time.Instant;

@Aggregate
public class MoneyTransfer {
    @AggregateIdentifier
    private String transactionId;
    private String fromAccountId;
    private String toAccountId;
    private BigDecimal amount;
    private TransactionStatus status;

    public MoneyTransfer(){}
    @CommandHandler
    public MoneyTransfer(CreateMoneyTransfer command){
        apply(
                new MoneyTransferCreated(
                        command.getTransactionId(),
                        command.getFromAccountId(),
                        command.getToAccountId(),
                        command.getAmount(),
                        Instant.now()
                )
        );
    }
    @CommandHandler
    public void handle(MarkTransferAsCompleted command){
        Instant now= Instant.now();
        apply(new MoneyTransferCompleted(command.getTransactionId(), now));
    }
    @CommandHandler
    public void handle(MarkTransferAsFailed command){
        Instant now= Instant.now();
        apply(new MoneyTransferFailed(command.getTransactionId(), now));
    }
   @EventSourcingHandler
    protected void on(MoneyTransferCreated event){
        this.transactionId = event.getTransactionId();
       this.fromAccountId = event.getFromAccountId();
       this.toAccountId = event.getToAccountId();
       this.amount = event.getAmount();
       this.status = TransactionStatus.CREATED;
   }
    @EventSourcingHandler
    public void on(MoneyTransferFailed event) {
        this.status = TransactionStatus.FAILED;
    }

    @EventSourcingHandler
    public void on(MoneyTransferCompleted event) {
        this.status = TransactionStatus.COMPLETED;
    }
}
