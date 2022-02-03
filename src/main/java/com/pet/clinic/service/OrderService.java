package com.pet.clinic.service;

import com.pet.clinic.common.Payments;
import com.pet.clinic.common.TransactionRequest;
import com.pet.clinic.common.TransactionResponse;
import com.pet.clinic.entity.Order;
import com.pet.clinic.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate template;

    public TransactionResponse saveOrder(TransactionRequest transactionRequest)
    {
        String response = "abc";
        Order order = transactionRequest.getOrder();
        Payments payments = transactionRequest.getPayments();
        payments.setOrderId(order.getId());
        payments.setAmount(order.getPrice());
        //restApi
        Payments paymentsResponse =template.postForObject("http://localhost:8081/payment/initPayment",payments,Payments.class);
        response = paymentsResponse.getPaymentStatus().equals("sucess")?"Payment processing successful and order placed"
                :"there is failure in Gateway";

        orderRepository.save(order);
        return new TransactionResponse(order,paymentsResponse.getAmount(),paymentsResponse.getTransactionId(),response);
    }


}
