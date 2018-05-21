package com.gayson.repos;

import com.gayson.models.Restaurant;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by jixunzhen on 2018/5/20.
 */
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    public Restaurant getByGatewayId(@Param(value = "gateway_id") Long gatewayId);
    public Restaurant getByAppKey(@Param(value = "app_key") String appKey);
}
