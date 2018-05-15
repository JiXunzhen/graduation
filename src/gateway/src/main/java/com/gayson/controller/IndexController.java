package com.gayson.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class IndexController {

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
}
