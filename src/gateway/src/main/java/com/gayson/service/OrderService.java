package com.gayson.service;

import com.gayson.exception.FallbackException;
import com.gayson.model.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jixunzhen on 2018/5/10.
 */
public interface OrderService {
    /**
     * ping order.
     * @return
     */
    Order pingOrder();

    /**
     * get single order by origin platform order id.
     * return null while order id not existed.
     * @param originalOrderId
     * @param shopId
     * @return Order
     */
    Order getByOriginalId(String originalOrderId, String shopId) throws FallbackException;

    /**
     * get fallback default order while circuit breaker open.
     * @param originalOrderId
     * @param shopId
     * @return
     */
    Order fallbackGetByOriginalId(String originalOrderId, String shopId) throws FallbackException;


    /**
     * get multiple order by a list of platform order id.
     * elem in result list would be null if order id not existed.
     * @param originalOrderIds
     * @param shopId
     * @return
     */
    ArrayList<Order> getByOriginalOrderIds(List<String> originalOrderIds, String shopId) throws FallbackException;

    /**
     * get multiple fallback order while circuit breaker open.
     * @param originalOrderIds
     * @param shopId
     * @return
     */
    ArrayList<Order> fallbackGetByOriginalOrderIds(List<String> originalOrderIds, String shopId) throws FallbackException;
}
