package com.acme.demo.gateway

import groovy.util.logging.Slf4j
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.web.bind.annotation.RestController

/**
 * The API Gateway application.  Handles all traffic, and routes it to the appropriate service.
 * This is really the only application which needs to be 'public' facing.  In AWS for example all the
 * other services could be placed in a private VPC.
 *
 * Uses the spring-cloud Zuul proxy for routing.  Also makes use of the spring-cloud Eureka for service
 * discovery.
 *
 * @author William Gorder
 * @since 12/30/14
 */
@SpringBootApplication
@RestController
@EnableDiscoveryClient
@Slf4j
class ApiGatewayApplication {

    static void main(String[] args) {
        SpringApplication.run ApiGatewayApplication, args
    }
}
