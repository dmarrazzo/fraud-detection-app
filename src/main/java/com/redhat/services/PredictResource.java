package com.redhat.services;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.redhat.model.CardTransaction;
import com.redhat.openapi.api.DefaultApi;
import com.redhat.openapi.model.InferenceRequest;
import com.redhat.openapi.model.InferenceResponse;
import com.redhat.openapi.model.RequestInput;
import com.redhat.openapi.model.ResponseOutput;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/predict")
public class PredictResource {

    @Inject
    @RestClient
    DefaultApi infer;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String predict() {
        return "Predict is working.";
    }

    @POST
    @Path("/request")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean request(CardTransaction transaction) {
        InferenceRequest request = new InferenceRequest();

        RequestInput input = new RequestInput();
        input.setName("dense_input");
        input.addShapeItem(1);
        input.addShapeItem(5);
        input.datatype("FP32");
        
        input.addDataItem(transaction.getDistanceFromLastTransaction());
        input.addDataItem(transaction.getRatioToMedianPurchase());
        input.addDataItem(booleanToDouble(transaction.isChip()));
        input.addDataItem(booleanToDouble(transaction.isPin()));
        input.addDataItem(booleanToDouble(transaction.isOnline()));
        request.addInputsItem(input);
        
        InferenceResponse response = infer.postV2ModelsMODELNAMEInfer("fraud", request);
        ResponseOutput output = response.getOutputs().get(0);
        Double prediction = output.getData().get(0);

        System.out.println("data: " + input.getData());
        System.out.println("prediction: " + prediction);
        return prediction > 0.995d;
    }

    private static double booleanToDouble(boolean b) {
        if (b)
            return 1.0d;
        else
            return 0;
    }
}
