package com.gayson.models;

import org.springframework.data.annotation.TypeAlias;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by jixunzhen on 2018/5/20.
 */

@Entity
@Table(name = "t_restaurant")
public class Restaurant implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "gateway_id", unique = true)
    @NotNull
    private Long gatewayId;

    @Column(name = "app_key", unique = true)
    @NotNull
    private String appKey;

    @Column(name = "app_secret")
    @NotNull
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


