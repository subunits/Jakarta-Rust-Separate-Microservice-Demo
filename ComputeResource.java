package com.example;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

@Path("/compute")
public class ComputeResource {
    private final HttpClient client = HttpClient.newHttpClient();

    @GET
    public Response add(@QueryParam("a") int a, @QueryParam("b") int b) {
        try {
            String url = "http://localhost:8081/add/" + a + "/" + b;
            HttpRequest req = HttpRequest.newBuilder()
                                         .uri(URI.create(url))
                                         .build();
            HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
            return Response.ok("Rust says: " + res.body()).build();
        } catch (Exception e) {
            return Response.serverError().entity("Error: " + e.getMessage()).build();
        }
    }
}
