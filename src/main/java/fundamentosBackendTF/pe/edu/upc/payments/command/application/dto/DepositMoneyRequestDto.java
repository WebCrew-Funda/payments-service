package fundamentosBackendTF.pe.edu.upc.payments.command.application.dto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class DepositMoneyRequestDto {
    @NotNull
    private String postId;
    private BigDecimal amount;

    public String getPostId(){return postId;}
    public BigDecimal getAmount() {
        return amount;
    }
}
