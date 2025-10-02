# Jakarta + Rust gRPC Demo

This demo shows how to connect a Rust gRPC microservice (using Tonic) with a Jakarta EE backend (using gRPC-Java).

## Structure
- `compute.proto` – shared protobuf definition.
- `rust-grpc-service/` – Rust microservice using Tonic.
- `jakarta-grpc-backend/` – Jakarta EE app that calls Rust via gRPC.

## Build & Run

### Rust Service
```bash
cd rust-grpc-service
cargo build
cargo run
```

This starts the gRPC server on port 50051.

### Java Client (Jakarta)
- Use `protoc` with `protoc-gen-grpc-java` to generate Java classes from `compute.proto`.
- Compile and run `GrpcClient.java` (inside Jakarta project or standalone).

```bash
protoc --java_out=. --grpc-java_out=. compute.proto
javac -cp ".:grpc-all.jar" com/example/grpc/GrpcClient.java
java -cp ".:grpc-all.jar" com.example.grpc.GrpcClient
```

Expected output:
```
Result: 7
```

## Deployment on AWS
- Package Rust service into a Docker image and deploy via Elastic Beanstalk (Docker platform).
- Package Jakarta backend as WAR (with gRPC client) and deploy via Elastic Beanstalk (Java platform).
- Jakarta connects to Rust via internal URL/port 50051.

