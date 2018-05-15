package com.gayson.controller;

import com.gayson.beans.ServiceMap;
import com.gayson.models.PlatformType;
import com.gayson.service.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by jixunzhen on 2018/5/10.
 */
@RestController
@PreAuthorize("hasRole('USER')")
@RequestMapping(path = "/order")
public class OrderController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    ServiceMap serviceMap;

    @Autowired
    GatewayService gatewayService;

    @RequestMapping(path = "/get")
    public Result getOrder(@RequestParam(name = "id")String orderId) {
        return Result.createResult(gatewayService.get(orderId));
    }

    @RequestMapping(path = "/get_by_type")
    public Result getOrderByOriginId(@RequestParam(name = "id")String orderId, @PathVariable(name = "type")String platformType) {
        try {
            PlatformType type = PlatformType.valueOf(platformType);
            return Result.createResult(serviceMap.getService(type).getByOriginalId(orderId));
        }catch (IllegalArgumentException e) {
            return Result.createResult(Result.ResultStatus.PLATFORM_TYPE_ERROR, null);
        }
    }

    @RequestMapping(path = "/mget", method = RequestMethod.POST)
    public Result mgetOrder(@RequestBody List<String> ids) {
        return Result.createResult(gatewayService.mget(ids));
    }

    @RequestMapping(path = "/mget_by_type")
    public Result mgetOrderByOriginId(@RequestParam(name = "type")String platformType, @RequestBody List<String> ids) {
        try {
            PlatformType type = PlatformType.valueOf(platformType);
            return Result.createResult(serviceMap.getService(type).mgetByOriginalOrderIds(ids));
        }catch (IllegalArgumentException e) {
            return Result.createResult(Result.ResultStatus.PLATFORM_TYPE_ERROR, null);
        }
    }
}
