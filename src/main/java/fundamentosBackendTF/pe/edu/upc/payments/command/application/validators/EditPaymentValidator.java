package fundamentosBackendTF.pe.edu.upc.payments.command.application.validators;

import fundamentosBackendTF.pe.edu.upc.common.application.Notification;
import fundamentosBackendTF.pe.edu.upc.payments.command.application.dto.request.EditPaymentRequest;
import fundamentosBackendTF.pe.edu.upc.payments.command.domain.Payment;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.messaging.unitofwork.DefaultUnitOfWork;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
/*
@Component
public class EditPaymentValidator {

    private final EventSourcingRepository<Payment> paymentRepository;

    public EditPaymentValidator(EventSourcingRepository<Payment> paymentRepository){
        this.paymentRepository = paymentRepository;
    }
    public Notification validate(EditPaymentRequest editPaymentRequest){
        Notification notification = new Notification();
        String paymentId = editPaymentRequest.getPaymentId().trim();
        if(paymentId.isEmpty()){
            notification.addError("Payment id is required");
            return notification;
        }
        loadAccountAggregate(paymentId);
        return notification;

    }
    private void loadAccountAggregate(String paymentId) {
        UnitOfWork unitOfWork = null;
        try {
            unitOfWork = DefaultUnitOfWork.startAndGet(null);
            paymentRepository.load(paymentId);
            unitOfWork.commit();
        } catch (AggregateNotFoundException ex) {
            unitOfWork.commit();
            throw ex;
        } catch(Exception ex) {
            if (unitOfWork != null) unitOfWork.rollback();
        }
    }

}

 */