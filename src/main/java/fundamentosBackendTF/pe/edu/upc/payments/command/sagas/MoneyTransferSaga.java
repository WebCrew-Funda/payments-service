package fundamentosBackendTF.pe.edu.upc.payments.command.sagas;


import fundamentosBackendTF.pe.edu.upc.payments_contracts.commands.CreditAccount;
import fundamentosBackendTF.pe.edu.upc.payments_contracts.commands.DebitFromAccount;
import fundamentosBackendTF.pe.edu.upc.payments_contracts.commands.MarkTransferAsCompleted;
import fundamentosBackendTF.pe.edu.upc.payments_contracts.commands.MarkTransferAsFailed;
import fundamentosBackendTF.pe.edu.upc.payments_contracts.events.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import javax.inject.Inject;
import java.math.BigDecimal;

@Saga
public class MoneyTransferSaga {
    private String fromAccountId;
    private String toAccountId;
    private BigDecimal amount;

    @Inject
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "paymentId")
    public void on(MoneyTransferCreated event){
        this.fromAccountId = event.getFromAccountId();
        this.toAccountId = event.getToAccountId();
        this.amount = event.getAmount();
        DebitFromAccount debitFromAccount = new DebitFromAccount(
                event.getFromAccountId(),
                event.getTransactionId(),
                event.getAmount()
        );
        commandGateway.send(debitFromAccount);
    }
    @SagaEventHandler(associationProperty = "paymentId")
    @EndSaga
    public void on(FromAccountNotFound event) {
        MarkTransferAsFailed markTransferAsFailed = new MarkTransferAsFailed(event.getTransactionId());
        commandGateway.send(markTransferAsFailed);
    }

    @SagaEventHandler(associationProperty = "paymentId")
    @EndSaga
    public void on(ToAccountNotFound event) {
        CreditAccount creditAccount = new CreditAccount(this.fromAccountId, event.getTransactionId(), this.amount);
        commandGateway.send(creditAccount);

        MarkTransferAsFailed markTransferAsFailed = new MarkTransferAsFailed(event.getTransactionId());
        commandGateway.send(markTransferAsFailed);
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "paymentId")
    public void on(ToAccountCredited event) {
        MarkTransferAsCompleted command = new MarkTransferAsCompleted(event.getTransactionId());
        commandGateway.send(command);
    }
}
