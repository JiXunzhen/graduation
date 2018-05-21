package com.gayson.repos;

import com.gayson.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by jixunzhen on 2018/5/20.
 */
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Restaurant getByGatewayId(@Param("gateway_id") String gatewayId);
    Restaurant getByAppKey(@Param("app_key") String appKey);
}
