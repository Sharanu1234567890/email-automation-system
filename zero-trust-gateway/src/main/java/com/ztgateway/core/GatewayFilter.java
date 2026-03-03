package com.ztgateway.core;

import com.ztgateway.model.FilterResult;
import com.ztgateway.model.RequestContext;

public interface GatewayFilter {
    FilterResult apply(RequestContext context);
}
