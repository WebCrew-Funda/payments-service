package fundamentosBackendTF.pe.edu.upc.payments.command.application.dto;

public class DepositMoneyErrorResponseDto {
    private String message;
    public DepositMoneyErrorResponseDto(){this.message= "Error with the deposit";}
    public String getMessage(){return message;}
}
