package com.pet.clinic.common;

import com.pet.clinic.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

    private Order order;
    private Payments payments;

}
