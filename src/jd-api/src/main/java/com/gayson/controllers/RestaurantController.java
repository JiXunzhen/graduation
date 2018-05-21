package com.gayson.controllers;

import com.gayson.exception.ApplicationException;
import com.gayson.exception.ErrorCode;
import com.gayson.models.Restaurant;
import com.gayson.repos.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/restaurant")
public class RestaurantController {

    @Autowired
    RestaurantRepository restaurantRepository;

    @RequestMapping(path = "/add")
    public Restaurant addRestaurant(@RequestBody @Validated Restaurant restaurant) throws Exception {
        Restaurant existedRestaurant = restaurantRepository.getByGatewayId(restaurant.getGatewayId());
        if (existedRestaurant != null) {
            ApplicationException e = new ApplicationException(ErrorCode.DATA_EXIST);
            e.setData(existedRestaurant);
            throw e;
        }
        existedRestaurant = restaurantRepository.getByAppKey(restaurant.getAppKey());
        if (existedRestaurant != null) {
            ApplicationException e = new ApplicationException(ErrorCode.DATA_EXIST);
            e.setData(existedRestaurant);
            throw e;
        }
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    @RequestMapping(path = "/get")
    public Restaurant getRestaurant(@RequestParam("restaurantId") Long gatewayRestaurantId) {
        return restaurantRepository.getByGatewayId(gatewayRestaurantId);
    }

    @RequestMapping(path = "/get_by_app_key")
    public Restaurant getRestaurantByAppKey(@RequestParam("appKey") String appKey) {
        return restaurantRepository.getByAppKey(appKey);
    }
}
