package com.gayson.models.order;

import com.gayson.models.PlatformType;

import java.io.Serializable;

/**
 *
 * The Order class integrate order info of all eCommerce platform
 */
public class Order implements Serializable {
    private PlatformType type;
    private String platformOrderId;
    private String platformShopId;
    private String platformUserId;
    private PlatformOrder platformOrder;

    private String orderId;
    private String shopId;
    private String payType;
    private String orderStatus;
    private String payment;
    private String orderStartTime;
    private String address;
    private String telephone;
    private String email;


    public PlatformType getType() {
        return type;
    }

    public void setType(PlatformType type) {
        this.type = type;
    }

    public String getPlatformOrderId() {
        return platformOrderId;
    }

    public void setPlatformOrderId(String platformOrderId) {
        this.platformOrderId = platformOrderId;
    }

    public String getPlatformShopId() {
        return platformShopId;
    }

    public void setPlatformShopId(String platformShopId) {
        this.platformShopId = platformShopId;
    }

    public String getPlatformUserId() {
        return platformUserId;
    }

    public void setPlatformUserId(String platformUserId) {
        this.platformUserId = platformUserId;
    }

    public PlatformOrder getPlatformOrder() {
        return platformOrder;
    }

    public void setPlatformOrder(PlatformOrder platformOrder) {
        this.platformOrder = platformOrder;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getOrderStartTime() {
        return orderStartTime;
    }

    public void setOrderStartTime(String orderStartTime) {
        this.orderStartTime = orderStartTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
