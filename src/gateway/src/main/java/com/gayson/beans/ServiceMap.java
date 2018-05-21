package com.gayson.beans;

import com.gayson.model.PlatformType;
import com.gayson.service.PlatformService;
import com.gayson.service.guomei.GomeService;
import com.gayson.service.jd.JDService;
import com.gayson.service.suning.SuningService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.HashMap;

public class ServiceMap {
    private static HashMap<PlatformType, PlatformService> serviceHashMap;

    @Autowired
    private GomeService gomeService;

    @Autowired
    private SuningService suningService;

    @Autowired
    private JDService jdService;
    /**
     * bean initialization.
     */
    public void init() {
        serviceHashMap =  new HashMap<>();

        serviceHashMap.put(gomeService.getType(), gomeService);
        serviceHashMap.put(suningService.getType(), suningService);
        serviceHashMap.put(jdService.getType(), jdService);
    }

    public PlatformService getService(PlatformType type) {
        return serviceHashMap.get(type);
    }
}
