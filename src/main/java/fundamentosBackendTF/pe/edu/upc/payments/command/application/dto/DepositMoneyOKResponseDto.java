package fundamentosBackendTF.pe.edu.upc.payments.command.application.dto;

public class DepositMoneyOKResponseDto {
    private String transactionId;

    public DepositMoneyOKResponseDto(String transactionId)
    {
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }
}
