package com.gayson.models;

import javax.persistence.*;

/**
 * Created by jixunzhen on 2018/5/20.
 */

@Entity
@Table(name = "t_restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "gateway_id", unique = true)
    private Long gatewayId;

    @Column(name = "app_key", unique = true)
    private String appKey;

    @Column(name = "app_secret")
    private String appSecret;

    public Long getId() {
        return id;
    }

    public Long getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(Long gatewayId) {
        this.gatewayId = gatewayId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
