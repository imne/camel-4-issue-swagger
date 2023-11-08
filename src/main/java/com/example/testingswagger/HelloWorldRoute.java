package com.example.testingswagger;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:hello-world")
                .log("HelloWorld")
                .setBody(constant("HelloWorld"))
                .routeId("direct:hello-world")
                .id("direct:hello-world");
    }
}
