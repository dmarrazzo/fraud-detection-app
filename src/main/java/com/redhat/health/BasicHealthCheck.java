package com.redhat.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.redhat.openapi.api.DefaultApi;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@Readiness
@ApplicationScoped
public class BasicHealthCheck implements HealthCheck {

    @Inject
    @RestClient
    DefaultApi infer;

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("Model Serving connection health check");

        try {
            infer.getV2HealthReady();
            responseBuilder.up();
        } catch (Exception e) {
            responseBuilder.down();
        }

        return responseBuilder.build();
    }

}
