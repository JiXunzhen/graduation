package com.gayson.controllers;

import com.gayson.globals.Result;
import com.gayson.models.Restaurant;
import com.gayson.models.SuningOrder;
import com.gayson.repos.RestaurantRepository;
import com.gayson.services.OrderService;
import com.suning.api.exception.SuningApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jixunzhen on 2018/5/20.
 */

@RestController
@RequestMapping(path = "/order")
public class OrderController {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    OrderService orderService;

    @RequestMapping(path = "/get")
    public Result get(@RequestParam(name = "orderId") String orderId,
                      @RequestParam(name = "restaurantId") Long gatewayRestaurantId) {

        Restaurant restaurant = restaurantRepository.getByGatewayId(gatewayRestaurantId);
        if (restaurant == null) {
            return Result.createResult(Result.ResultStatus.NOT_FOUND, null, "餐厅不存在!");
        }

        try {
            SuningOrder suningOrder =  orderService.get(orderId, restaurant);
            return Result.createResult(Result.ResultStatus.OK, suningOrder);
        } catch (SuningApiException e) {
            return Result.createError(Result.ResultStatus.VENDOR_ERROR, e);
        } catch (NumberFormatException e) {
            return Result.createError(Result.ResultStatus.BAD_PARAMS, e);
        }
    }
}
