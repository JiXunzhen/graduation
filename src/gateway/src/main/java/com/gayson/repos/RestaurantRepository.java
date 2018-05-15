package com.gayson.repos;

import com.gayson.models.Restaurant;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by jixunzhen on 2018/5/13.
 */
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query(value = "select * from t_restaurant where user_id = :user_id", nativeQuery = true)
    Restaurant queryByUserId(@Param("user_id") String userId);
}
