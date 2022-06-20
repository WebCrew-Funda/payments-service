package fundamentosBackendTF.pe.edu.upc.payments.command.application.handlers;

import fundamentosBackendTF.pe.edu.upc.payments.command.application.infra.PaymentNumber;
import fundamentosBackendTF.pe.edu.upc.payments.command.application.infra.PaymentNumberRepository;
import fundamentosBackendTF.pe.edu.upc.payments_contracts.events.MoneyTransferCreated;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("paymentNumber")

public class PaymentEventHandler {
    private final PaymentNumberRepository paymentNumberRepository;
    public PaymentEventHandler(PaymentNumberRepository paymentNumberRepository){
        this.paymentNumberRepository = paymentNumberRepository;
    }
    @EventHandler
    public void on(MoneyTransferCreated event){
        paymentNumberRepository.save(new PaymentNumber(event.getNumber(),event.getPostId()));
    }
}
