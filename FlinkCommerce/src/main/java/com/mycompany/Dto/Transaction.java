/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Dto;

import java.security.Timestamp;

/**
 *
 * @author Bee
 * {'transactionId': 'd5102ff9-8ada-4a3a-b3f2-c7775911304d', 
 * 'productId': 'product1', 'productName': 'watch', 
 * 'productCategory': 'sports', 'productPrice': 429.58, 
 * 'productQuantity': 7, 'productBrand': 'samsung', 
 * 'currency': 'GBP', 'customerId': 'nlove', 
 * 'transactionDate': '2023-12-19T05:28:25.444784', 
'paymentMethod': 'debit_card', 'totalAmount': 3007.06}
 */
public class Transaction {
    private String transactionId;
    private String productId;
    private String productName;
    private String productCategory;
    private double productPrice;
    private Integer productQuantity;
    private String productBrand;
    private String currency;
    private String customerId;
    private Timestamp transactionDate;
    private String paymentMethod;
}
