package com.project.paymentgateway.Repository;

import com.project.paymentgateway.Entity.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentOrder, Long> {

    PaymentOrder findByOrderId(String orderId);
}
