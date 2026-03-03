# Zero-Trust Gateway (Evolved)

This module scaffolds the architecture for an advanced API gateway featuring:

- Zero-trust filter pipeline
- Threat scoring and behavioral analysis package layout
- Self-healing circuit-breaker module placeholders
- Geo-fencing and payload scanning package boundaries
- Kafka/Redis/Resilience4j-ready Spring Boot baseline

## Current status

This is a **foundation scaffold** with compile-safe placeholders, intended for phased implementation.

## Run locally

```bash
cd zero-trust-gateway
mvn test
```

## Phases

1. Implement request ingestion and `RequestContext` builder.
2. Wire concrete filters with real dependencies.
3. Integrate ONNX threat inference and Redis behavioral profiles.
4. Add self-healing reroute diagnostics and developer portal events.
