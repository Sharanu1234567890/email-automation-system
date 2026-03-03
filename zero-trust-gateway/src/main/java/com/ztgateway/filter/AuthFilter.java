package com.ztgateway.filter;

import com.ztgateway.core.GatewayFilter;
import com.ztgateway.model.FilterResult;
import com.ztgateway.model.RequestContext;
import org.springframework.stereotype.Component;

@Component
public class AuthFilter implements GatewayFilter {
    @Override
    public FilterResult apply(RequestContext context) {
        return FilterResult.allow("Auth passed");
    }
}
