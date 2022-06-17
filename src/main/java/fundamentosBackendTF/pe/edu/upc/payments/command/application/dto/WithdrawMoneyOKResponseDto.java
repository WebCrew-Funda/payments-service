package fundamentosBackendTF.pe.edu.upc.payments.command.application.dto;

public class WithdrawMoneyOKResponseDto {
    private String transactionId;
    public WithdrawMoneyOKResponseDto(String transactionId){this.transactionId=transactionId;}
    public String getTransactionId() {
        return transactionId;
    }
}
