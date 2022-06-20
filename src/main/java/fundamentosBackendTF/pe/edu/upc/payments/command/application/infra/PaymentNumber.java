package fundamentosBackendTF.pe.edu.upc.payments.command.application.infra;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class PaymentNumber {
    @Id
    @Column(length=25)
    public BigDecimal number;
    public String postId;

    public PaymentNumber(){

    }
    public PaymentNumber(BigDecimal number, String postId){
        this.number = number;
        this.postId = postId;
    }

}
