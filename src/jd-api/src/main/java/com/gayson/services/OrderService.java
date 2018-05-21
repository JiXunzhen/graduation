package com.gayson.services;

import com.gayson.models.JDOrder;
import com.gayson.models.Restaurant;
import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.domain.order.OrderQueryJsfService.OrderResult;
import com.jd.open.api.sdk.domain.order.OrderQueryJsfService.OrderSearchInfo;
import com.jd.open.api.sdk.request.order.PopOrderGetRequest;
import com.jd.open.api.sdk.response.order.PopOrderGetResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Value("application.const.jd_server_url")
    String SERVER_URL;

    public JDOrder get(String orderId, Restaurant restaurant) throws JdException {
        JdClient client = new DefaultJdClient(
                SERVER_URL,
                restaurant.getAccessToken(),
                restaurant.getAppKey(),
                restaurant.getAppSecret());

        PopOrderGetRequest request = new PopOrderGetRequest();
        request.setOrderId(Long.parseLong(orderId));

        PopOrderGetResponse response = client.execute(request);
        OrderResult result = response.getOrderDetailInfo();
        OrderSearchInfo searchInfo = result.getOrderInfo();
        return new JDOrder(searchInfo);
    }
}
