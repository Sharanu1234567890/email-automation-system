package com.ztgateway.model;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class RequestContext {
    private final String requestId;
    private final String route;
    private final String sourceIp;
    private final Instant receivedAt;
    private final Map<String, Object> attributes = new HashMap<>();

    public RequestContext(String requestId, String route, String sourceIp, Instant receivedAt) {
        this.requestId = requestId;
        this.route = route;
        this.sourceIp = sourceIp;
        this.receivedAt = receivedAt;
    }

    public String getRequestId() { return requestId; }
    public String getRoute() { return route; }
    public String getSourceIp() { return sourceIp; }
    public Instant getReceivedAt() { return receivedAt; }
    public Map<String, Object> getAttributes() { return attributes; }
}
