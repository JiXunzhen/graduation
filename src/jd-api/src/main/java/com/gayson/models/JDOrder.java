package com.gayson.models;

import com.jd.open.api.sdk.domain.order.OrderQueryJsfService.OrderSearchInfo;

import java.io.Serializable;

public class JDOrder implements PlatformOrder, Serializable{
    private OrderSearchInfo rawOrder;
    private static String PlaceHolder = "该订单不支持此数据";
    @Override
    public Order serialize() {
        Order order = new Order();
        order.setType(PlatformType.JING_DONG);
        order.setPlatformOrder(this);

        order.setOrderId(rawOrder.getOrderId());
        order.setShopId(rawOrder.getVenderId());
        order.setPayType(rawOrder.getPayType());
        order.setOrderStatus(rawOrder.getOrderState());
        order.setPayment(rawOrder.getOrderPayment());
        order.setOrderStartTime(rawOrder.getOrderStartTime());
        order.setAddress(rawOrder.getConsigneeInfo().getFullAddress());
        order.setTelephone(rawOrder.getConsigneeInfo().getTelephone());
        order.setEmail(PlaceHolder);
        return order;
    }

    public JDOrder(OrderSearchInfo rawOrder) {
        this.rawOrder = rawOrder;
    }
}

