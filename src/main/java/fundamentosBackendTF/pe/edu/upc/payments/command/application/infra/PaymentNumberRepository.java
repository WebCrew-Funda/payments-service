package fundamentosBackendTF.pe.edu.upc.payments.command.application.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentNumberRepository extends JpaRepository<PaymentNumber,String> {
}
