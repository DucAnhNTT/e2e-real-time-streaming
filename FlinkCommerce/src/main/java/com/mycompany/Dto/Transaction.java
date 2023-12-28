/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Dto;

import java.sql.Timestamp;

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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }
}
