package com.gayson.models.order;

import com.gayson.models.PlatformType;
import com.gayson.utils.DateUtils;
import gome.open.api.sdk.cloud.client.domain.bridge.client.vo.OrderVO;

public class GomeOrder implements PlatformOrder {
    private OrderVO rawOrder;

    @Override
    public Order serialize() {
        Order order = new Order();
        order.setType(PlatformType.GUOMEI);
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

    GomeOrder(OrderVO rawOrder) {
        this.rawOrder = rawOrder;
    }
}
