package com.project.paymentgateway.Controller;

import com.project.paymentgateway.Entity.PaymentOrder;
import com.project.paymentgateway.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create-order")
    private ResponseEntity<String> createOrder(@RequestBody PaymentOrder order) {
        try {
            String response = paymentService.createOrder(order);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating order");
        }
    }

    @PostMapping("/update-order")
    public ResponseEntity<String> updateOrderStatus(@RequestParam String paymentId,
                                                    @RequestParam String orderId,
                                                    @RequestParam String status)
    {
        paymentService.updateOrderStatus(paymentId,orderId,status);
        System.out.println("order place successfully....");
        return ResponseEntity.ok("Order updated Successfully...");
    }
}
