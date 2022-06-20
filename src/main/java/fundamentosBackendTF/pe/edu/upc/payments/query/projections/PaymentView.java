package fundamentosBackendTF.pe.edu.upc.payments.query.projections;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
public class PaymentView {
    @Id
    @Column(length=36)
    @Getter @Setter
    private String paymentId;
    @Column(length=36)
    @Getter @Setter
    private String postId;
    @Column(nullable = true)
    @Getter @Setter
    private BigDecimal number;
    @Column(nullable = true)
    @Getter @Setter
    private Instant createdAt;

    public PaymentView(){

    }
    public PaymentView(String paymentId, String postId, BigDecimal number, Instant createdAt) {
        this.paymentId = paymentId;
        this.postId = postId;
        this.number = number;
        this.createdAt = createdAt;
    }

    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

}
