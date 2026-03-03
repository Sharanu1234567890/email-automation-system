package com.ztgateway.core;

import com.ztgateway.model.DecisionType;
import com.ztgateway.model.FilterResult;
import com.ztgateway.model.RequestContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilterChainExecutor {

    private final List<GatewayFilter> filters;

    public FilterChainExecutor(List<GatewayFilter> filters) {
        this.filters = filters;
    }

    public FilterResult execute(RequestContext context) {
        for (GatewayFilter filter : filters) {
            FilterResult result = filter.apply(context);
            if (result.decision() != DecisionType.ALLOW) {
                return result;
            }
        }
        return FilterResult.allow("All filters passed");
    }
}
