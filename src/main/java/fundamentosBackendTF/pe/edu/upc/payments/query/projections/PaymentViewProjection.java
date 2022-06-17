package fundamentosBackendTF.pe.edu.upc.payments.query.projections;


import fundamentosBackendTF.pe.edu.upc.payments.command.domain.TransactionStatus;
import fundamentosBackendTF.pe.edu.upc.payments.command.domain.TransactionType;
import fundamentosBackendTF.pe.edu.upc.payments_contracts.events.MoneyTransferCompleted;
import fundamentosBackendTF.pe.edu.upc.payments_contracts.events.MoneyTransferFailed;
import fundamentosBackendTF.pe.edu.upc.payments_contracts.events.*;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class PaymentViewProjection {
    private final PaymentViewRepository paymentViewRepository;
    public PaymentViewProjection(PaymentViewRepository paymentViewRepository){
        this.paymentViewRepository =paymentViewRepository;
    }
    @EventHandler
    public void on(AccountCredited event) {
        String transactionId = event.getTransactionId();
        String accountId = event.getAccountId();
        BigDecimal amount = event.getAmount();
        String transactionType = TransactionType.DEPOSIT.toString();
        PaymentView transactionView = new PaymentView(transactionId, accountId, null, amount, transactionType, TransactionStatus.COMPLETED.toString(), event.getOccurredOn());
        paymentViewRepository.save(transactionView);
    }
    @EventHandler
    public void on(MoneyTransferFailed event) {
        Optional<PaymentView> transactionViewOptional = paymentViewRepository.findById(event.getTransactionId());
        if (transactionViewOptional.isPresent()) {
            PaymentView transactionView = transactionViewOptional.get();
            String transactionStatus = TransactionStatus.FAILED.toString();
            transactionView.setTransactionStatus(transactionStatus);
            transactionView.setCreatedAt(event.getOccurredOn());
            paymentViewRepository.save(transactionView);
        }
    }
    @EventHandler
    public void on(MoneyTransferCompleted event) {
        Optional<PaymentView> transactionViewOptional = paymentViewRepository.findById(event.getTransactionId());
        if (transactionViewOptional.isPresent()) {
            PaymentView transactionView = transactionViewOptional.get();
            String transactionStatus = TransactionStatus.COMPLETED.toString();
            transactionView.setTransactionStatus(transactionStatus);
            transactionView.setCreatedAt(event.getOccurredOn());
            paymentViewRepository.save(transactionView);
        }
    }

}
