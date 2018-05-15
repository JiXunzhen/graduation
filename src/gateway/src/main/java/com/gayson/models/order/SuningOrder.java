package com.gayson.models.order;

import com.gayson.models.PlatformType;
import com.suning.api.entity.custom.orderGetGetResponse;

/**
 * Created by jixunzhen on 2018/5/7.
 */
public class SuningOrder implements PlatformOrder {
    private orderGetGetResponse.OrderGet rawOrder;
    private static String PlaceHolder = "该类订单不支持此数据";
    @Override
    public Order serialize() {
        Order order = new Order();

        order.setType(PlatformType.SUNING);
        order.setPlatformOrder(this);

        order.setOrderId(rawOrder.getOrderCode());
        order.setShopId(PlaceHolder);
        order.setPayType(rawOrder.getPayType());
        order.setOrderStatus(rawOrder.getOrderTotalStatus());
        order.setPayment(rawOrder.getPayTotalAmount());
        order.setOrderStartTime(rawOrder.getOrderSaleTime());
        order.setAddress(rawOrder.getCustomerAddress());
        order.setTelephone(rawOrder.getRegisterPhone());
        order.setEmail(PlaceHolder);
        return order;
    }

    SuningOrder(orderGetGetResponse.OrderGet rawOrder) {
        this.rawOrder = rawOrder;
    }

}
