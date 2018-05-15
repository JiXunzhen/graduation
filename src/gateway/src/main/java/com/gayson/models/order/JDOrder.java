package com.gayson.models.order;

import com.gayson.models.PlatformType;
import com.jd.open.api.sdk.domain.order.OrderQueryJsfService.OrderSearchInfo;

public class JDOrder implements PlatformOrder{
    private OrderSearchInfo rawOrder;
    private static String PlaceHolder = "该订单不支持此数据";
    @Override
    public Order serialize() {
        Order order = new Order();
        order.setType(PlatformType.JING_DONG);
        order.setPlatformOrder(this);

        order.setOrderId(rawOrder.getOrderId());
        order.setShopId(rawOrder.getStoreId());
        order.setPayType(rawOrder.getPayType());
        order.setOrderStatus(rawOrder.getOrderState());
        order.setPayment(rawOrder.getOrderPayment());
        order.setOrderStartTime(rawOrder.getOrderStartTime());
        order.setAddress(rawOrder.getConsigneeInfo().getFullAddress());
        order.setTelephone(rawOrder.getConsigneeInfo().getTelephone());
        order.setEmail(PlaceHolder);
        return null;
    }

    JDOrder(OrderSearchInfo rawOrder) {
        this.rawOrder = rawOrder;
    }
}
