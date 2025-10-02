use tonic::{transport::Server, Request, Response, Status};
use compute::compute_service_server::{ComputeService, ComputeServiceServer};
use compute::{AddRequest, AddReply};

pub mod compute {
    tonic::include_proto!("compute");
}

#[derive(Default)]
pub struct MyComputeService {}

#[tonic::async_trait]
impl ComputeService for MyComputeService {
    async fn add(&self, request: Request<AddRequest>) -> Result<Response<AddReply>, Status> {
        let req = request.into_inner();
        let sum = req.a + req.b;
        Ok(Response::new(AddReply { result: sum }))
    }
}

#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    let addr = "[::1]:50051".parse()?;
    let compute_service = MyComputeService::default();

    println!("Rust gRPC service listening on {}", addr);

    Server::builder()
        .add_service(ComputeServiceServer::new(compute_service))
        .serve(addr)
        .await?;

    Ok(())
}
