package com.ztgateway.ratelimit;

import org.springframework.stereotype.Component;

@Component
public class SlidingWindowRateLimiter implements RateLimitStrategy {
    @Override
    public boolean allow(String key) {
        return true;
    }
}
