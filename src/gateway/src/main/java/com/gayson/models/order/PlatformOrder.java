package com.gayson.models.order;

/**
 * The PlatformOrder interface is entry for order in different platform.
 * All the order could be transform to com.gayson.Order by implement PlatformOrder interface.
 */
public interface PlatformOrder {
    Order serialize();
}
