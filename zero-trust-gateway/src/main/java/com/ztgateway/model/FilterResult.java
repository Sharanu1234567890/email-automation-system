package com.ztgateway.model;

public record FilterResult(DecisionType decision, String reason) {
    public static FilterResult allow(String reason) {
        return new FilterResult(DecisionType.ALLOW, reason);
    }
}
