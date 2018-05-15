package com.gayson.service.suning;

import com.gayson.models.order.Order;
import com.gayson.models.PlatformType;
import com.gayson.service.PlatformService;
import com.suning.api.DefaultSuningClient;
import com.suning.api.entity.custom.orderGetGetRequest;
import com.suning.api.entity.custom.orderGetGetResponse;
import com.suning.api.exception.SuningApiException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jixunzhen on 2018/3/17.
 */

@Service
public class SuningService implements PlatformService{
    public void orderGet() {
        orderGetGetRequest request = new orderGetGetRequest();
        request.setOrderCode("4511680451");//api入参校验逻辑开关，当测试稳定之后建议设置为 false 或者删除该行
        request.setCheckParam(true);
        String serverUrl = "https://open.suning.com/api/http/sopRequest";
        String appKey = "你的appKey";
        String appSecret = "你的appSecret";
        DefaultSuningClient client = new DefaultSuningClient(serverUrl, appKey, appSecret, "json");
        try {
            orderGetGetResponse response = client.excute(request);
            System.out.println("返回json/xml格式数据 :" + response.getBody());
            orderGetGetResponse.OrderGet orderGet = response.getSnbody().getOrderGet();

        } catch (SuningApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order get(String orderId) {
        return null;
    }

    @Override
    public Order getByOriginalId(String originalOrderId) {
        return null;
    }

    @Override
    public ArrayList<Order> mget(List<String> orderIds) {
        return null;
    }

    @Override
    public ArrayList<Order> mgetByOriginalOrderIds(List<String> originalOrderIds) {
        return null;
    }

    @Override
    public PlatformType getType() {
        return PlatformType.SUNING;
    }
}
