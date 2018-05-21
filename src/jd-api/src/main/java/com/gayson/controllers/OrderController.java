package com.gayson.controllers;

import com.gayson.exception.ApplicationException;
import com.gayson.exception.ErrorCode;
import com.gayson.models.JDOrder;
import com.gayson.models.Order;
import com.gayson.models.Restaurant;
import com.gayson.repos.RestaurantRepository;
import com.gayson.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/order")
public class OrderController {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    OrderService orderService;

    @RequestMapping(path = "/get")
    public Order get(@RequestParam(name = "orderId") String orderId,
                     @RequestParam(name = "restaurantId") Long gatewayRestaurantId) throws Exception {
        Restaurant restaurant = restaurantRepository.getByGatewayId(gatewayRestaurantId);
        if (restaurant == null) {
            throw new ApplicationException(ErrorCode.NOT_FOUND, "订单不存在");
        }

        JDOrder jdOrder = orderService.get(orderId, restaurant);
        return jdOrder.serialize();
    }
}
