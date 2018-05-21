package com.gayson.controller;

import com.gayson.beans.ServiceMap;
import com.gayson.model.Order;
import com.gayson.model.PlatformType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class PingController {

    @Autowired
    ServiceMap serviceMap;
    @RequestMapping(path = "/ping")
    public String ping() {
        return "pong";
    }

    @RequestMapping(path = "/ping_post", method = RequestMethod.POST)
    public String pingPost() {
        return "pong_post";
    }

    @RequestMapping(path = "/ping_auth")
    public String pingAuth() {
        return "ping_auth";
    }

    @RequestMapping(path = "/ping_order")
    public Order pingOrder(@RequestParam(name = "platformType")PlatformType type) {
        return serviceMap.getService(type).pingOrder();
    }
}
