package com.gayson.repos;

import com.gayson.models.Restaurant;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jixunzhen on 2018/5/20.
 */
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Restaurant getByGatewayId(@Param("gateway_id") Long gatewayId);
    Restaurant getByAppKey(@Param("app_key") String appKey);
}
