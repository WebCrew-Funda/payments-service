package fundamentosBackendTF.pe.edu.upc.payments.command.api;

import fundamentosBackendTF.pe.edu.upc.common.api.ApiController;
import fundamentosBackendTF.pe.edu.upc.common.application.Notification;
import fundamentosBackendTF.pe.edu.upc.common.application.Result;
import fundamentosBackendTF.pe.edu.upc.payments.command.application.dto.*;

import fundamentosBackendTF.pe.edu.upc.payments.command.application.dto.request.EditPaymentRequest;
import fundamentosBackendTF.pe.edu.upc.payments.command.application.dto.request.OpenPaymentRequest;
import fundamentosBackendTF.pe.edu.upc.payments.command.application.dto.response.EditPaymentResponse;

import fundamentosBackendTF.pe.edu.upc.payments.command.application.dto.response.OpenPaymentResponse;
import fundamentosBackendTF.pe.edu.upc.payments.command.application.services.PaymentApplicationService;
import fundamentosBackendTF.pe.edu.upc.payments.command.domain.OverdraftLimitExceededException;


import fundamentosBackendTF.pe.edu.upc.payments_contracts.commands.PostCommand;
import fundamentosBackendTF.pe.edu.upc.payments_contracts.commands.WithdrawPayment;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/payments")
@Tag(name="Payments")
public class PaymentCommandController {
    private final CommandGateway commandGateway;
    private final PaymentApplicationService paymentApplicationService;
    public PaymentCommandController(CommandGateway commandGateway,PaymentApplicationService paymentApplicationService){
        this.commandGateway=commandGateway;
        this.paymentApplicationService = paymentApplicationService;

    }
/*
    @PostMapping("/deposit")
    public ResponseEntity<Object>deposit(@Validated @RequestBody DepositMoneyRequestDto depositMoneyRequestDto){
        String transactionId = UUID.randomUUID().toString();
        PostCommand command = new PostCommand(
          depositMoneyRequestDto.getPostId(),
          transactionId,
          depositMoneyRequestDto.getAmount()
        );
        CompletableFuture<Object>future = commandGateway.send(command);
        CompletableFuture<Object>futureResponse= future.handle((ok,ex)->{
            if(ex!=null){
                return new DepositMoneyErrorResponseDto();
            }
            return new DepositMoneyOKResponseDto(transactionId);
        });
        try{
            Object response = (Object) futureResponse.get();
            if(response instanceof DepositMoneyOKResponseDto){
                return new ResponseEntity(response, HttpStatus.OK);
            }
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/withdraw")
    public ResponseEntity<Object> withdraw(@Validated @RequestBody WithdrawMoneyRequestDto withdrawMoneyRequestDto) {
        String transactionId = UUID.randomUUID().toString();
        WithdrawPayment command = new WithdrawPayment(
                withdrawMoneyRequestDto.getPostId(),
                transactionId,
                withdrawMoneyRequestDto.getAmount()
        );
        CompletableFuture<Object> future = commandGateway.send(command);
        CompletableFuture<Object> futureResponse = future.handle((ok, ex) -> {
            if (ex != null) {
                if (ex instanceof OverdraftLimitExceededException) {
                    return new WithdrawMoneyErrorResponseDto("The account exceeded the overdraft Limit");
                }
                return new WithdrawMoneyErrorResponseDto();
            }
            return new WithdrawMoneyOKResponseDto(transactionId);
        });
        try {
            Object response = (Object)futureResponse.get();
            if (response instanceof WithdrawMoneyOKResponseDto) {
                return new ResponseEntity(response, HttpStatus.OK);
            }
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
*/
   @PostMapping("")
    public ResponseEntity<Object> open(@Validated @RequestBody OpenPaymentRequest openPaymentRequest) {
     try {
        Result<OpenPaymentResponse, Notification> result = paymentApplicationService.open(openPaymentRequest);
        if (result.isSuccess()) {
            return ApiController.created(result.getSuccess());
         }
        return ApiController.error(result.getFailure().getErrors());
      } catch(Exception e) {
          return ApiController.serverError();
      }
   }
    @PutMapping("/{paymentId}")
    public ResponseEntity<Object> edit(@PathVariable("paymentId") String paymentId, @RequestBody EditPaymentRequest editPaymentRequest) {
        try {
            editPaymentRequest.setPaymentId(paymentId);
            Result<EditPaymentResponse, Notification> result = paymentApplicationService.edit(editPaymentRequest);
            if (result.isSuccess()) {
                return ApiController.ok(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (AggregateNotFoundException exception) {
            return ApiController.notFound();
        } catch(Exception e) {
            return ApiController.serverError();
        }
    }

}
