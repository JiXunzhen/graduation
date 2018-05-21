package com.gayson.models;

import com.gayson.models.Order;
import com.gayson.models.PlatformOrder;
import com.gayson.models.PlatformType;
import com.gayson.utils.DateUtils;
import gome.open.api.sdk.cloud.client.domain.bridge.client.vo.OrderVO;

import java.io.Serializable;

public class GomeOrder implements PlatformOrder, Serializable {
    private OrderVO rawOrder;

    @Override
    public Order serialize() {
        Order order = new Order();
        order.setType(PlatformType.GOME);
        order.setPlatformOrder(this);

        order.setOrderId(rawOrder.getOrderId());
        order.setShopId(rawOrder.getVendorId());
        order.setPayType(rawOrder.getPayType());
        order.setOrderStatus(rawOrder.getStatus());
        order.setPayment(rawOrder.getPayment().toString());
        order.setOrderStartTime(DateUtils.dateToISOFormat(rawOrder.getOrderTime()));
        order.setAddress(rawOrder.getAddressShortName());
        order.setTelephone(rawOrder.getConsignee().getTelephone());
        order.setEmail(rawOrder.getConsignee().getEmail());
        return order;
    }

    public GomeOrder(OrderVO rawOrder) {
        this.rawOrder = rawOrder;
    }
}

