package com.ztgateway.ratelimit;

public interface RateLimitStrategy {
    boolean allow(String key);
}
