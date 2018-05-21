package com.gayson.controllers;

import com.gayson.models.JDOrder;
import com.gayson.models.Order;
import com.jd.open.api.sdk.domain.order.OrderQueryJsfService.OrderSearchInfo;
import com.jd.open.api.sdk.domain.order.OrderQueryJsfService.UserInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {
    @RequestMapping(path = "/ping")
    public String ping() {
        return "pong";
    }

    @RequestMapping(path = "/ping_order")
    public Order pingOrder() {
        OrderSearchInfo info = new OrderSearchInfo();
        info.setOrderId("test_order_id_13000001");
        info.setVenderId("test_vendor_id_13000001");
        info.setPayType("已支付");
        info.setOrderState("商品已出仓");
        info.setOrderPayment("213.20");
        info.setOrderStartTime("2017-09-11T11:04:23.0000Z");

        UserInfo userInfo = new UserInfo();
        userInfo.setFullAddress("上海市杨浦区同济大学四平路校区");
        userInfo.setTelephone("18221026671");
        info.setConsigneeInfo(userInfo);

        JDOrder order = new JDOrder(info);
        return order.serialize();
    }
}
