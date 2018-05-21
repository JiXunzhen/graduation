package com.gayson.services;

import com.gayson.models.Restaurant;
import com.gayson.models.SuningOrder;
import com.suning.api.DefaultSuningClient;
import com.suning.api.entity.custom.orderGetGetRequest;
import com.suning.api.entity.custom.orderGetGetResponse;
import com.suning.api.exception.SuningApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by jixunzhen on 2018/5/20.
 */
@Service
public class OrderService {
    @Value("application.const.jd_server_url")
    String SERVER_URL;

    public SuningOrder get(String orderId, Restaurant restaurant) throws SuningApiException{
        orderGetGetRequest request = new orderGetGetRequest();
        request.setOrderCode(orderId);
        DefaultSuningClient client = new DefaultSuningClient(SERVER_URL, restaurant.getAppKey(), restaurant.getAppSecret(), "json");

        orderGetGetResponse response = client.excute(request);
        orderGetGetResponse.OrderGet orderGet = response.getSnbody().getOrderGet();

        return new SuningOrder(orderGet);
    }
}
