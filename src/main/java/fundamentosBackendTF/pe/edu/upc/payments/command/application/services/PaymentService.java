package fundamentosBackendTF.pe.edu.upc.payments.command.application.services;

import fundamentosBackendTF.pe.edu.upc.common.application.Notification;
import fundamentosBackendTF.pe.edu.upc.common.application.Result;
import fundamentosBackendTF.pe.edu.upc.common.application.ResultType;
import fundamentosBackendTF.pe.edu.upc.payments.command.application.dto.DeleteMoneyDepositedRequestDto;
import fundamentosBackendTF.pe.edu.upc.payments.command.application.dto.response.DeletePaymentResponse;
import fundamentosBackendTF.pe.edu.upc.payments.command.application.validators.DeletePaymentValidator;

import fundamentosBackendTF.pe.edu.upc.payments.command.application.validators.exception.ResourceNotFoundException;
import fundamentosBackendTF.pe.edu.upc.payments.query.projections.PaymentViewRepository;
import fundamentosBackendTF.pe.edu.upc.payments_contracts.commands.DeleteMoneyDeposit;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class PaymentService {
    private final CommandGateway commandGateway;
    private final PaymentViewRepository paymentViewRepository;
    private final DeletePaymentValidator deletePaymentValidator;
    public PaymentService(CommandGateway commandGateway, PaymentViewRepository paymentViewRepository,DeletePaymentValidator deletePaymentValidator){
        this.commandGateway = commandGateway;
        this.paymentViewRepository = paymentViewRepository;
        this.deletePaymentValidator = deletePaymentValidator;
    }

    public ResponseEntity<?> delete(String paymentId) throws Exception{

        DeleteMoneyDeposit deleteMoneyDeposit = new DeleteMoneyDeposit(
                paymentId
        );
        CompletableFuture<Object> future = commandGateway.send(deleteMoneyDeposit);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE){
            throw new Exception();
        }
        DeletePaymentResponse deletePaymentResponse = new DeletePaymentResponse(
                deleteMoneyDeposit.getAccountId()
        );
        String deletePaymentId= deletePaymentResponse.getAccountId();
        return paymentViewRepository.findById(deletePaymentResponse.getAccountId().trim()).map(paymentClass ->{
            paymentViewRepository.delete(paymentClass);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Payment: ", deletePaymentId));
    }
}
