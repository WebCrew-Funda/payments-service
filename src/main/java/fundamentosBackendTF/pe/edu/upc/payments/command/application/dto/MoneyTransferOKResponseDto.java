package fundamentosBackendTF.pe.edu.upc.payments.command.application.dto;

public class MoneyTransferOKResponseDto {
    private String transactionId;
    public MoneyTransferOKResponseDto(String transactionId){this.transactionId=transactionId;}
    public String getTransactionId() {
        return transactionId;
    }
}
