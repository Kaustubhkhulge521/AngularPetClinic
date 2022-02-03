package com.pet.clinic.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payments {
    private Integer paymentId;
    private String paymentStatus;
    private String transactionId;
    private Integer orderId;
    private double amount;
}
