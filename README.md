# Jakarta + Rust Separate Microservice Demo

This demo shows a clean separation where **Rust** runs as a microservice and **Jakarta EE** middleware calls it.

## Structure
- `rust-service/` — Actix-Web microservice in Rust exposing `/add/{a}/{b}`.
- `jakarta-backend/` — Jakarta EE WAR project that calls the Rust service and exposes `/compute?a=..&b=..`.

## Build & Run

### Rust service
```bash
cd rust-service
cargo run
```
Service runs on http://localhost:8081/add/{a}/{b}

Example:
```bash
curl http://localhost:8081/add/7/5
# 12
```

### Jakarta backend
```bash
cd jakarta-backend
mvn package
```
Deploy `target/jakarta-rust-integration.war` to Payara, WildFly, or TomEE.

### Call Jakarta endpoint
```bash
curl "http://localhost:8080/jakarta-rust-integration/api/compute?a=7&b=5"
# Rust says: 12
```

## Notes
- This avoids running WASM inside the JVM. Rust runs separately as a service.
- Jakarta handles business logic, persistence, and orchestration.
- Rust handles compute and exposes lightweight REST endpoints.
- You can swap REST for gRPC if stronger contracts and performance are needed.
