package fundamentosBackendTF.pe.edu.upc.payments.command.application.dto;

public class MoneyTransferErrorResponseDto {
    private String message;
    public MoneyTransferErrorResponseDto(){this.message="Error with the transfer";}
    public String getMessage() {
        return message;
    }
}
