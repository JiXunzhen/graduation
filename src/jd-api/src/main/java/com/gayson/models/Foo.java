package com.gayson.models;

import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.domain.order.OrderInfo;
import com.jd.open.api.sdk.domain.order.OrderQueryJsfService.ApiResult;
import com.jd.open.api.sdk.domain.order.OrderQueryJsfService.OrderResult;
import com.jd.open.api.sdk.domain.order.OrderQueryJsfService.OrderSearchInfo;
import com.jd.open.api.sdk.request.order.PopOrderGetRequest;
import com.jd.open.api.sdk.response.order.PopOrderGetResponse;

/**
 * Created by jixunzhen on 2018/3/10.
 */
public class Foo {
    private OrderInfo orderInfo;
    private String SERVER_URL;
    private String accessToken;
    private String appKey;
    private String appSecret;
    public Foo(OrderInfo orderInfo) throws JdException {
        this.orderInfo = orderInfo;
        JdClient client = new DefaultJdClient(this.SERVER_URL, this.accessToken, this.appKey, this.appSecret);

        PopOrderGetRequest request = new PopOrderGetRequest();
        request.setOrderState("jingdong");
        request.setOptionalFields("jingdong");
        request.setOrderId(123);


        PopOrderGetResponse response = client.execute(request);


        OrderResult result = response.getOrderDetailInfo();

        ApiResult apiResult = result.getApiResult();
        OrderSearchInfo searchInfo = result.getOrderInfo();
    }
}