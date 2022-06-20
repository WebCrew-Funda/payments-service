package fundamentosBackendTF.pe.edu.upc.payments.command.domain;

import fundamentosBackendTF.pe.edu.upc.payments_contracts.commands.CreateMoneyTransfer;
import fundamentosBackendTF.pe.edu.upc.payments_contracts.commands.EditPayment;
import fundamentosBackendTF.pe.edu.upc.payments_contracts.events.MoneyTransferCreated;
import fundamentosBackendTF.pe.edu.upc.payments_contracts.events.PaymentEdited;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import java.math.BigDecimal;
import java.time.Instant;

@Aggregate
public class Payment {
    @AggregateIdentifier
    private String paymentId;
    private String postId;
    private BigDecimal number;
    private TransactionStatus status;
    private BigDecimal overdraftLimit;
    public Payment(){}
    @CommandHandler
    public Payment(CreateMoneyTransfer command){
        apply(
                new MoneyTransferCreated(
                        command.getPaymentId(),
                        command.getPostId(),
                        command.getNumber(),
                        Instant.now()
                )
        );
    }

    @CommandHandler
    public void handle(EditPayment command) throws Exception{
        Instant now= Instant.now();
        apply(new PaymentEdited(command.getPaymentId(), command.getNumber(), command.getOverdraftLimit() ,now));
    }

   @EventSourcingHandler
    protected void on(MoneyTransferCreated event){
        this.paymentId = event.getPaymentId();
       this.postId = event.getPostId();
       this.number = event.getNumber();
   }
    @EventSourcingHandler
    public void handle(PaymentEdited event) {
        this.paymentId = event.getPaymentId();
        this.number = event.getNumber();
        this.overdraftLimit = event.getOverdraftLimit();
    }

}
