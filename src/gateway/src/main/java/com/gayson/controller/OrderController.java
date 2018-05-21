package com.gayson.controller;

import com.gayson.beans.ServiceMap;
import com.gayson.exception.ApplicationException;
import com.gayson.exception.ErrorCode;
import com.gayson.exception.FallbackException;
import com.gayson.globals.Result;
import com.gayson.globals.ResultStatus;
import com.gayson.model.Order;
import com.gayson.model.PlatformType;
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
    public Result getOrder(@RequestParam(name = "id") String orderId) {
        try {
            return Result.createResult(ResultStatus.OK, gatewayService.get(orderId));
        } catch (Exception e) {
            return Result.createError(ResultStatus.USER_ERROR, e);
        }
    }

    @RequestMapping(path = "/get_by_type")
    public Result getOrderByOriginId(
            @RequestParam(name = "id") String orderId,
            @RequestParam(name = "type") String platformType,
            @RequestParam(name = "shopId") String shopId) {
        try {

            PlatformType type = PlatformType.valueOf(platformType);
            return Result.createResult(ResultStatus.OK, serviceMap.getService(type).getByOriginalId(orderId, shopId));
        } catch (IllegalArgumentException e) {
            return Result.createError(ResultStatus.USER_ERROR, new ApplicationException(ErrorCode.PLATFORM_TYPE_ERROR, platformType));
        } catch (FallbackException e) {
            return Result.createError(ResultStatus.FALLBACK_ERROR, e);
        } catch (Exception e) {
            return Result.createError(ResultStatus.VENDOR_ERROR, e);
        }
    }

    @RequestMapping(path = "/mget", method = RequestMethod.POST)
    public Result mgetOrder(@RequestBody List<String> ids) {
        try {
            return Result.createResult(ResultStatus.OK, gatewayService.mget(ids));
        } catch (Exception e) {
            return Result.createError(ResultStatus.USER_ERROR, e);
        }
    }

    @RequestMapping(path = "/mget_by_type")
    public Result mgetOrderByOriginId(
            @RequestParam(name = "type") String platformType,
            @RequestBody List<String> ids,
            @RequestParam(name = "shopId") String shopId) {
        try {
            PlatformType type = PlatformType.valueOf(platformType);
            return Result.createResult(ResultStatus.OK, serviceMap.getService(type).getByOriginalOrderIds(ids, shopId));
        } catch (IllegalArgumentException e) {
            return Result.createError(ResultStatus.USER_ERROR, new ApplicationException(ErrorCode.PLATFORM_TYPE_ERROR, platformType));
        } catch (FallbackException e) {
            return Result.createError(ResultStatus.FALLBACK_ERROR, e);
        } catch (Exception e) {
            return Result.createError(ResultStatus.VENDOR_ERROR, e);
        }
    }
}
