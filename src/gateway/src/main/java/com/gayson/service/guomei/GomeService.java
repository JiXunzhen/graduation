package com.gayson.service.guomei;

import com.gayson.model.PlatformType;
import com.gayson.model.Order;
import com.gayson.service.PlatformService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GomeService implements PlatformService {
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
        return PlatformType.GOME;
    }
}
