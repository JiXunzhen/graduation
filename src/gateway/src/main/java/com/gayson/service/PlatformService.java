package com.gayson.service;

import com.gayson.model.PlatformType;

/**
 * the PlatformService interface define and constraint the basic behaviors of platform service.
 * combined by:
 * OrderService
 *
 */
public interface PlatformService extends OrderService{
    public PlatformType getType();
}
