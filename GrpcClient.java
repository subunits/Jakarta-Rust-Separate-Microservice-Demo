package com.example.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import compute.ComputeServiceGrpc;
import compute.Compute.AddRequest;
import compute.Compute.AddReply;

public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        ComputeServiceGrpc.ComputeServiceBlockingStub stub =
                ComputeServiceGrpc.newBlockingStub(channel);

        AddReply reply = stub.add(AddRequest.newBuilder().setA(3).setB(4).build());

        System.out.println("Result: " + reply.getResult());

        channel.shutdown();
    }
}
