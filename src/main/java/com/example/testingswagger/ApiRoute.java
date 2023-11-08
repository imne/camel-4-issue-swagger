package com.example.testingswagger;


import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Component
public class ApiRoute extends RouteBuilder {
    private static final String REQUEST_PROCESSED_FINE = "Request Processed Fine";
    private static final String INVALID_CREDENTIALS = "Invalid Credentials";
    private static final String SOMETHING_WENT_WRONG = "Something went wrong";

    @Value("${camel.servlet.mapping.context-path}")
    String contextPath;

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.json)
                .enableCORS(true)
                .dataFormatProperty("prettyPrint", "true")
                .port(8080)
               .contextPath(contextPath.substring(0, contextPath.length() - 2))
                .apiProperty("cors", "true")
                //turn on open-api docs
                .apiContextPath("/v3/api-docs")
                .apiContextRouteId("swagger-docs-api")
                .apiProperty("api.title", "Hello World API")
                .apiProperty("api.version", "2")
                .apiProperty("api.contact.name", "Dummy Team")
                .apiProperty("api.contact.email", "dummyemail@email.com")
                .apiProperty("api.description", "Hello World Testing swagger");
        rest("/json")
                .description("Hello World API")
                .id("hello-world-rest-api-route")
                //heartbeat endpoint
                .post("/hello")
                .routeId("heartbeat-exposed")
                .produces(APPLICATION_JSON_VALUE)
                .consumes(APPLICATION_JSON_VALUE)
                .description("Hello World Testing Swagger API")
                .enableCORS(true)
                .type(String.class)
                .responseMessage("200", REQUEST_PROCESSED_FINE)
                .responseMessage("401", INVALID_CREDENTIALS)
                .responseMessage("500", SOMETHING_WENT_WRONG)
                .outType(String.class)
                .to("direct:hello-world");

    }
}
