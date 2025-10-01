use actix_web::{get, web, App, HttpServer, Responder};

#[get("/add/{a}/{b}")]
async fn add(path: web::Path<(i32, i32)>) -> impl Responder {
    let (a, b) = path.into_inner();
    format!("{}", a + b)
}

#[actix_web::main]
async fn main() -> std::io::Result<()> {
    HttpServer::new(|| App::new().service(add))
        .bind(("127.0.0.1", 8081))?
        .run()
        .await
}
