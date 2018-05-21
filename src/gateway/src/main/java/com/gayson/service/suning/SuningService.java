package com.gayson.service.suning;

import com.gayson.model.PlatformType;
import com.gayson.model.Order;
import com.gayson.service.PlatformService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jixunzhen on 2018/3/17.
 */

@Service
public class SuningService implements PlatformService{
    @Override
    public Order getByOriginalId(String originalOrderId, String shopId) {
        return null;
    }

    @Override
    public ArrayList<Order> mgetByOriginalOrderIds(List<String> originalOrderIds, String shopId) {
        return null;
    }

    @Override
    public PlatformType getType() {
        return PlatformType.SUNING;
    }
}
