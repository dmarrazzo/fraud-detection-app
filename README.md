# Fraud Detection App

This application complements the [OpenShift AI tutorial - Fraud detection example](https://access.redhat.com/documentation/en-us/red_hat_openshift_ai_self-managed/2.7/html/openshift_ai_tutorial_-_fraud_detection_example), providing a simple web application to consume the fraud prediction service.

## Powered by Quarkus

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Deploy on OpenShift

```sh
mvn package -Dquarkus.kubernetes.deploy=true -DskipTests
```
> **_NOTE:_** Route is configured to expose the application through TLS. Adjust route section in the configuration as your need (`application.properties`).

## CICD with OpenShift Pipelines

[pipelines](docs/pipelines.md)