package com.gayson.service.jd;

import com.gayson.models.order.Order;
import com.gayson.models.PlatformType;
import com.gayson.service.PlatformService;
import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.request.order.PopOrderSearchRequest;
import com.jd.open.api.sdk.response.order.PopOrderSearchResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by jixunzhen on 2018/5/7.
 */
@Service
public class JDService implements PlatformService{
    private static String SERVER_URL = "";
    private static String accessToken = "";
    private static String appKey = "";
    private static String appSecret = "";

    public Object queryOrder() throws JdException {
        JdClient client = new DefaultJdClient(SERVER_URL, accessToken, appKey, appSecret);

        PopOrderSearchRequest request = new PopOrderSearchRequest();

        request.setStartDate("jingdong");
        request.setEndDate("jingdong");
        request.setOrderState("jingdong");
        request.setOptionalFields("jingdong");
        request.setPage("jingdong");
        request.setPageSize("jingdong");
        request.setSortType(123);
        request.setDateType(123);

        PopOrderSearchResponse response = client.execute(request);
        return response.getSearchorderinfoResult();
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
    public ArrayList<Order> mgetByOriginalOrderIds(List<String> originalOrderIds) {
        return null;
    }

    @Override
    public ArrayList<Order> mget(List<String> orderIds) {
        return null;
    }

    @Override
    public PlatformType getType() {
        return PlatformType.JING_DONG;
    }
}
