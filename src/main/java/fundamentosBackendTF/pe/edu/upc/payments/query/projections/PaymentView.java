package fundamentosBackendTF.pe.edu.upc.payments.query.projections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
public class PaymentView {
    @Id
    @Column(length=36)
    private String transactionId;
    @Column(length=36)
    private String fromAccountId;
    @Column(length=36, nullable = true)
    private String toAccountId;
    private BigDecimal amount;
    @Column(length=15)
    private String transactionType;
    @Column(length=15)
    private String transactionStatus;
    private Instant createdAt;

    public PaymentView(){

    }
    public PaymentView(String transactionId, String fromAccountId, String toAccountId, BigDecimal amount, String transactionType, String transactionStatus, Instant createdAt) {
        this.transactionId = transactionId;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionStatus = transactionStatus;
        this.createdAt = createdAt;
    }
    public void setTransactionStatus(String transactionStatus) { this.transactionStatus = transactionStatus; }

    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

}
