package com.gayson.services;

import cn.com.gome.cloud.openplatform.bridge.core.client.DefaultGmosClient;
import cn.com.gome.cloud.openplatform.bridge.core.client.GmosClient;
import com.gayson.models.GomeOrder;
import com.gayson.models.Order;
import com.gayson.models.Restaurant;
import gome.open.api.sdk.cloud.client.domain.bridge.client.request.GomeOrderOrderGetRequest;
import gome.open.api.sdk.cloud.client.domain.bridge.client.response.GomeOrderOrderGetResponse;
import gome.open.api.sdk.cloud.client.domain.bridge.client.vo.OrderVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Value("application.const.jd_server_url")
    String SERVER_URL;

    public GomeOrder get(String orderId, Restaurant restaurant) {
        GmosClient client = new DefaultGmosClient(SERVER_URL, restaurant.getAppKey(), restaurant.getAppSecret());
        GomeOrderOrderGetRequest request = new GomeOrderOrderGetRequest();
        request.setOrderId(orderId);
        request.setFields("mainItemId,count,point,price,itemId,itemType,itemName,itemDiscount,outId,size,color,partDiscountPrice,couponValue,salesProps");
        GomeOrderOrderGetResponse response = client.execute(request);
        System.out.println(response.getBody());
        OrderVO orderVO = response.getResult();

        return new GomeOrder(orderVO);
    }
}
