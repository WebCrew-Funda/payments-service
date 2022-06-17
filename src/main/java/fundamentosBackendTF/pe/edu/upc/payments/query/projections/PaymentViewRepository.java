package fundamentosBackendTF.pe.edu.upc.payments.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentViewRepository extends JpaRepository<PaymentView, String> { }
