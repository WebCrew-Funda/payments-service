package fundamentosBackendTF.pe.edu.upc.payments.command.api;

import fundamentosBackendTF.pe.edu.upc.payments.command.application.dto.*;

import fundamentosBackendTF.pe.edu.upc.payments.command.domain.OverdraftLimitExceededException;
import fundamentosBackendTF.pe.edu.upc.payments_contracts.commands.CreditAccount;
import fundamentosBackendTF.pe.edu.upc.payments_contracts.commands.DebitAccount;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.commandhandling.gateway.CommandGateway;
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

    public PaymentCommandController(CommandGateway commandGateway){this.commandGateway=commandGateway;}

    @PostMapping("/deposit")
    public ResponseEntity<Object>deposit(@Validated @RequestBody DepositMoneyRequestDto depositMoneyRequestDto){
        String transactionId = UUID.randomUUID().toString();
        CreditAccount command = new CreditAccount(
          depositMoneyRequestDto.getAccountId(),
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
        DebitAccount command = new DebitAccount(
                withdrawMoneyRequestDto.getAccountId(),
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
    /*
    @DeleteMapping("/{paymentId}")
    public ResponseEntity<?>deletePayment(@PathVariable("paymentId") String paymentId) throws Exception {
            return paymentService.delete(paymentId);
    }

     */
}
