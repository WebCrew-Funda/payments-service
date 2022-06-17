package fundamentosBackendTF.pe.edu.upc.payments.command.application.validators;

import fundamentosBackendTF.pe.edu.upc.common.application.Notification;
import fundamentosBackendTF.pe.edu.upc.payments.command.application.dto.DeleteMoneyDepositedRequestDto;

import fundamentosBackendTF.pe.edu.upc.payments.command.domain.MoneyTransfer;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.messaging.unitofwork.DefaultUnitOfWork;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.springframework.stereotype.Component;



@Component
public class DeletePaymentValidator {
    private final EventSourcingRepository<MoneyTransfer> moneyPaymentRepository;
    public DeletePaymentValidator(EventSourcingRepository<MoneyTransfer> moneyPaymentRepository){
        this.moneyPaymentRepository = moneyPaymentRepository;
    }

    public Notification validate(DeleteMoneyDepositedRequestDto deleteMoneyDepositedRequestDto){
        Notification notification = new Notification();
        String accountId = deleteMoneyDepositedRequestDto.getTransactionId().trim();
        if(accountId.isEmpty()){
            notification.addError("Account id is required");
            return notification;
        }
        loadAccountAggregate(accountId);
        return notification;
    }

    private void loadAccountAggregate(String accountId){
        UnitOfWork unitOfWork = null;
        try {
            unitOfWork = DefaultUnitOfWork.startAndGet(null);
            moneyPaymentRepository.load(accountId);
            unitOfWork.commit();
        } catch (AggregateNotFoundException ex) {
            unitOfWork.commit();
            throw ex;
        } catch(Exception ex) {
            if (unitOfWork != null) unitOfWork.rollback();
        }
    }
}
