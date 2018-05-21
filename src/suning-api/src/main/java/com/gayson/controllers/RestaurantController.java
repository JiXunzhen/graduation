package com.gayson.controllers;

import com.gayson.globals.Result;
import com.gayson.models.Restaurant;
import com.gayson.repos.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by jixunzhen on 2018/5/20.
 */
public class RestaurantController {
    @Autowired
    RestaurantRepository restaurantRepository;

    @RequestMapping(path = "/add")
    public Result addRestaurant(@RequestBody @Validated Restaurant restaurant) {
        Restaurant existedRestaurant = restaurantRepository.getByGatewayId(restaurant.getGatewayId());
        if (existedRestaurant != null) {
            return Result.createResult(Result.ResultStatus.DATA_EXIST, existedRestaurant);
        }
        existedRestaurant = restaurantRepository.getByAppKey(restaurant.getAppKey());
        if (existedRestaurant != null) {
            return Result.createResult(Result.ResultStatus.DATA_EXIST, existedRestaurant);
        }

        restaurantRepository.save(restaurant);
        return Result.createResult(Result.ResultStatus.OK, restaurant);
    }

    @RequestMapping(path = "/get")
    public Result getRestaurant(@RequestParam("restaurantId") Long gatewayRestaurantId) {
        return Result.createResult(restaurantRepository.getByGatewayId(gatewayRestaurantId));
    }

    @RequestMapping(path = "/get_by_app_key")
    public Result getResult(@RequestParam("appKey") String appKey) {
        return Result.createResult(restaurantRepository.getByAppKey(appKey));
    }
}
