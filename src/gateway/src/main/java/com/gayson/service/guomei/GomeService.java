package com.gayson.service.guomei;

import cn.com.gome.cloud.openplatform.bridge.core.client.DefaultGmosClient;
import cn.com.gome.cloud.openplatform.bridge.core.client.GmosClient;
import com.gayson.models.order.Order;
import com.gayson.models.PlatformType;
import com.gayson.service.PlatformService;
import gome.open.api.sdk.cloud.client.domain.bridge.client.request.GomeOrderOrderGetRequest;
import gome.open.api.sdk.cloud.client.domain.bridge.client.response.GomeOrderOrderGetResponse;
import gome.open.api.sdk.cloud.client.domain.bridge.client.vo.OrderVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GomeService implements PlatformService {
    public void orderGet() {
        String url = "url";
        String appKey = "appKey";
        String secret = "secret";
        GmosClient client = new DefaultGmosClient(url, appKey, secret);
        GomeOrderOrderGetRequest request = new GomeOrderOrderGetRequest();
        //详细参数信息请参考API详情页面。
        request.setOrderId("1901154342");
        request.setFields("mainItemId,count,point,price,itemId,itemType,itemName,itemDiscount,outId,size,color,partDiscountPrice,couponValue,salesProps");
        GomeOrderOrderGetResponse response = client.execute(request);
        System.out.println(response.getBody());
        OrderVO orderVO = response.getResult();
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
        return PlatformType.GUOMEI;
    }
}
