package com.gayson.service;

import com.gayson.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jixunzhen on 2018/5/10.
 */

@Service
public class GatewayService {
    public Order get(String orderId) {
        Order order = new Order();
        order.setOrderId("10000001");
        order.setTelephone("18221026671");
        order.setPayment("123.45");
        order.setOrderStatus("ok");
        return order;
    }

    public ArrayList<Order> mget(List<String> orderIds) {
        ArrayList<Order> orders = new ArrayList<>(orderIds.size());
        for (String id : orderIds) {
            orders.add(get(id));
        }
        return orders;
    }
}
