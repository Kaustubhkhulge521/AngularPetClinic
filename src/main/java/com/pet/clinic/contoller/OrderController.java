package com.pet.clinic.contoller;

import com.pet.clinic.common.Payments;
import com.pet.clinic.common.TransactionRequest;
import com.pet.clinic.common.TransactionResponse;
import com.pet.clinic.entity.Order;
import com.pet.clinic.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/saveOrder")
    public TransactionResponse saveOrder(@RequestBody TransactionRequest transactionRequest)
    {
        return orderService.saveOrder(transactionRequest);
    }

}
