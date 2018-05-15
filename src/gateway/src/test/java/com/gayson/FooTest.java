package com.gayson;

import com.gayson.models.order.Order;
import com.gayson.models.PlatformType;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jixunzhen on 2018/5/10.
 */
@SpringBootTest
public class FooTest {
    @Test
    public void TestPushNullToArray() {
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setOrderId("12000022");
        orders.add(null);
        orders.add(order);

        System.out.println(orders);
    }

    @Test
    public void TestStringToEnum() {
        try {
            System.out.println(PlatformType.valueOf("GUOMEI"));
            System.out.println(PlatformType.valueOf("GUOME"));
        }catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }
}
