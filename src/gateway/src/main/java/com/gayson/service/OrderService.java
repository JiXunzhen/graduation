package com.gayson.service;

import com.gayson.models.order.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jixunzhen on 2018/5/10.
 */
public interface OrderService {
    /**
     * get single order by gateway order id.
     * return null while order id not existed.
     * @param orderId
     * @return Order
     */
    Order get(String orderId);

    /**
     * get single order by origin platform order id.
     * return null while order id not existed.
     * @param originalOrderId
     * @return Order
     */
    Order getByOriginalId(String originalOrderId);

    /**
     * get multiple order by a list of gateway order id.
     * elem in result list would be null if order id not existed.
     * @param orderIds
     * @return
     */
    ArrayList<Order> mget(List<String> orderIds);

    /**
     * get multiple order by a list of platform order id.
     * elem in result list would be null if order id not existed.
     * @param originalOrderIds
     * @return
     */
    ArrayList<Order> mgetByOriginalOrderIds(List<String> originalOrderIds);
}
