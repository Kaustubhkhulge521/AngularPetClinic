package com.pet.clinic;

import com.pet.clinic.common.Payments;
import com.pet.clinic.common.TransactionRequest;
import com.pet.clinic.common.TransactionResponse;
import com.pet.clinic.entity.Order;
import com.pet.clinic.repository.OrderRepository;
import com.pet.clinic.service.OrderService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

public class PetClinicOrderServiceImplTest {

    @MockBean
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    @Test
    public void test_saveOrder(){
        String response = "abc";


    }
    TransactionRequest dummytransactionRequest(){
        TransactionRequest transactionRequest = new TransactionRequest();
        Order order = new Order();
        order.setId(1);
        order.setPrice(10);
        order.setQuantity(1);
        Payments payments = new Payments();
        payments.setOrderId(1);
        payments.setAmount(1122);
        payments.setTransactionId("111");
        payments.setPaymentStatus("success");
        payments.setPaymentId(1);
        transactionRequest.setOrder(order);
        transactionRequest.setPayments(payments);
        return transactionRequest;
    }

    TransactionResponse dummytransactionResponse(){
        TransactionResponse transactionResponse = new TransactionResponse();
        Order order = new Order();
        order.setId(1);
        order.setPrice(10);
        order.setQuantity(1);
        order.setVaccine("vaccine");
        transactionResponse.setTransactionId("id");
        transactionResponse.setOrder(order);
        transactionResponse.setAmount(10.2);
        transactionResponse.setMessage("message");
        return transactionResponse;
    }

}
