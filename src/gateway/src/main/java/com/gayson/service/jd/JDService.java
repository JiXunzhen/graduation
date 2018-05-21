package com.gayson.service.jd;

import com.gayson.exception.ApplicationException;
import com.gayson.exception.FallbackException;
import com.gayson.model.PlatformType;
import com.gayson.model.Order;
import com.gayson.service.PlatformService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sun.jndi.toolkit.url.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by jixunzhen on 2018/5/7.
 */
@Service
public class JDService implements PlatformService{
    private static final String BASE_URL = "http://jd-api";

    @Autowired
    HttpServletRequest request;

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallbackGetByOriginalId")
    @Override
    public Order getByOriginalId(String originalOrderId, String shopId) throws FallbackException{
        URI uri = UriComponentsBuilder.fromUriString(BASE_URL)
                .path("/order/get")
                .queryParam("orderId", originalOrderId)
                .queryParam("restaurantId", shopId)
                .build()
                .encode()
                .toUri();
        return restTemplate.getForObject(uri, Order.class);
    }

    @Override
    public Order fallbackGetByOriginalId(String originalOrderId, String shopId) throws FallbackException {
        throw new FallbackException(Order.class, "jd-api", request.getRequestURI());
    }

    @Override
    public ArrayList<Order> fallbackGetByOriginalOrderIds(List<String> originalOrderIds, String shopId) throws FallbackException{
        throw new FallbackException(ArrayList.class, "jd-api", request.getRequestURI());
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallbackGetByOriginalOrderIds")
    public ArrayList<Order> getByOriginalOrderIds(List<String> originalOrderIds, String shopId) throws FallbackException{
        return null;
    }

    @Override
    public Order pingOrder() {
        URI uri = UriComponentsBuilder.fromUriString(BASE_URL)
                .path("/ping_order")
                .build()
                .encode()
                .toUri();
        return restTemplate.getForObject(uri, Order.class);
    }

    @Override
    public PlatformType getType() {
        return PlatformType.JD;
    }

}
