package com.redhat.health;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.eclipse.microprofile.config.inject.ConfigProperty;
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

    @ConfigProperty(name = "model.server.host", defaultValue = "modelmesh-serving")
    private String modelserverHost;

    @ConfigProperty(name = "model.server.port", defaultValue = "8008")
    private int modelserverPort;

    @ConfigProperty(name = "model.name", defaultValue = "fraud")
    private String modelName;

    @Inject
    @RestClient
    DefaultApi infer;

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("Model Serving connection health check");

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://" + modelserverHost + ":" + modelserverPort + "/v2/models/" + modelName))
                    .build();

            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

            if (response.statusCode() == 200)
                responseBuilder.up();
            else
                responseBuilder.down();
        } catch (Exception e) {
            responseBuilder.down();
            responseBuilder.withData("exception", e.toString());
        }

        return responseBuilder.build();
    }

}
