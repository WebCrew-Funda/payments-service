package fundamentosBackendTF.pe.edu.upc.payments.command.application.services;

import fundamentosBackendTF.pe.edu.upc.common.application.Notification;
import fundamentosBackendTF.pe.edu.upc.common.application.Result;
import fundamentosBackendTF.pe.edu.upc.common.application.ResultType;
import fundamentosBackendTF.pe.edu.upc.payments.command.application.dto.request.EditPaymentRequest;
import fundamentosBackendTF.pe.edu.upc.payments.command.application.dto.request.OpenPaymentRequest;
import fundamentosBackendTF.pe.edu.upc.payments.command.application.dto.response.EditPaymentResponse;
import fundamentosBackendTF.pe.edu.upc.payments.command.application.dto.response.OpenPaymentResponse;
import fundamentosBackendTF.pe.edu.upc.payments.command.application.infra.PaymentNumberRepository;
//import fundamentosBackendTF.pe.edu.upc.payments.command.application.validators.EditPaymentValidator;
import fundamentosBackendTF.pe.edu.upc.payments.command.application.validators.OpenPaymentValidator;
import fundamentosBackendTF.pe.edu.upc.payments_contracts.commands.CreateMoneyTransfer;
import fundamentosBackendTF.pe.edu.upc.payments_contracts.commands.EditPayment;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class PaymentApplicationService {
    private final CommandGateway commandGateway;
    private final PaymentNumberRepository paymentNumberRepository;
    // private final EditPaymentValidator editPaymentValidator;
    private final OpenPaymentValidator openPaymentValidator;

    public PaymentApplicationService(CommandGateway commandGateway, PaymentNumberRepository paymentNumberRepository, /*EditPaymentValidator editPaymentValidator,*/ OpenPaymentValidator openPaymentValidator){
        this.commandGateway = commandGateway;
        this.paymentNumberRepository = paymentNumberRepository;
        // this.editPaymentValidator = editPaymentValidator;
        this.openPaymentValidator = openPaymentValidator;
    }
    public Result<OpenPaymentResponse, Notification> open(OpenPaymentRequest openPaymentRequest) throws Exception {
        Notification notification = this.openPaymentValidator.validate(openPaymentRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        String paymentId = UUID.randomUUID().toString();
        CreateMoneyTransfer openPayment = new CreateMoneyTransfer(
                paymentId,
                openPaymentRequest.getPostId(),
                openPaymentRequest.getNumber(),
                openPaymentRequest.getOverdraftLimit()
        );
        CompletableFuture<Object> future = commandGateway.send(openPayment);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        OpenPaymentResponse openPaymentResponse = new OpenPaymentResponse(
                openPayment.getPaymentId(),
                openPayment.getNumber(),
                BigDecimal.ZERO,
                openPayment.getOverdraftLimit(),
                openPayment.getPostId()
        );
        return Result.success(openPaymentResponse);
    }

    public Result<EditPaymentResponse, Notification> edit(EditPaymentRequest editPaymentRequest) throws Exception{

        /*Notification notification = this.editPaymentValidator.validate(editPaymentRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }*/
        EditPayment editPayment = new EditPayment(
                editPaymentRequest.getPaymentId().trim(),
                editPaymentRequest.getNumber(),
                editPaymentRequest.getOverdraftLimit()
        );
        CompletableFuture<Object> future= commandGateway.send(editPayment);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if(resultType == ResultType.FAILURE){
            throw new Exception();
        }
        EditPaymentResponse editPaymentResponse = new EditPaymentResponse(
            editPayment.getPaymentId(),
            editPayment.getNumber(),
            editPayment.getOverdraftLimit()
        );
        return Result.success(editPaymentResponse);
    }

}
