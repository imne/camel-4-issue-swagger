package com.example.testingswagger;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.apache.camel.springboot.springdoc.SpringdocAutoConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class CamelConfiguration extends SpringdocAutoConfiguration {

    @Value("${enable.camel.trace}")
    private boolean enableTracing;

    @Bean
    public CamelContextConfiguration camelContextConfiguration() {
        return new CamelContextConfiguration() {
            @Override
            public void beforeApplicationStart(CamelContext camelContext) {
                camelContext.disableJMX();
                camelContext.setUseMDCLogging(true);
                camelContext.setAllowUseOriginalMessage(false);
                camelContext.setTracing(enableTracing);
                camelContext.setStreamCaching(false);

            }

            @Override
            public void afterApplicationStart(CamelContext camelContext) {

            }
        };

    }
}


