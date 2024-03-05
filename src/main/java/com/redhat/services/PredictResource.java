package com.redhat.services;

import inference.GRPCInferenceService;
import inference.GrpcPredictV2.ModelMetadataRequest;
import inference.GrpcPredictV2.ModelMetadataResponse;
import io.quarkus.grpc.GrpcClient;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/predict")
public class PredictResource {

    @GrpcClient
    GRPCInferenceService infer;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String predict() {
        return "Predict is working.";
    }

    @POST
    @Path("/request")
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<ModelMetadataResponse> live() {
        System.out.println("model");
        var request = ModelMetadataRequest.newBuilder().setName("fraud").build();
        infer.modelMetadata(request);
        return infer.modelMetadata(request);
    }
}
