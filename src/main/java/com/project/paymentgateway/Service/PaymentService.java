package com.project.paymentgateway.Service;

import com.project.paymentgateway.Entity.PaymentOrder;
import com.project.paymentgateway.Repository.PaymentRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentService {

    @Value("{razorpay.key.id}")
    private String keyId;

    @Value("{razorpay.key.secret}")
    private String keySecret;

    @Autowired
    private PaymentRepository paymentRepository;

    public String createOrder(PaymentOrder orderDetails) throws RazorpayException {

        RazorpayClient client = new RazorpayClient(keyId, keySecret);

        //json
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount",(int)(orderDetails.getAmount()*100));
        orderRequest.put("currency","INR");
        orderRequest.put("receipt", "txn_"+ UUID.randomUUID());

        Order razorpayOrder = client.orders.create(orderRequest);

        System.out.println(razorpayOrder.toString());
        orderDetails.setOrderId(razorpayOrder.get("id"));
        orderDetails.setStatus("CREATED");
        orderDetails.setCreatedAt(LocalDateTime.now());

        paymentRepository.save(orderDetails);
        return razorpayOrder.toString();
    }
}
