package com.risi.learningreactiveclient.webfluxlearningclient;

//import brave.sampler.Sampler;

import io.dekorate.kubernetes.annotation.ImagePullPolicy;
import io.dekorate.kubernetes.annotation.KubernetesApplication;
import io.dekorate.kubernetes.annotation.Probe;
import io.dekorate.kubernetes.annotation.ServiceType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
//import reactivefeign.spring.config.EnableReactiveFeignClients;

@SpringBootApplication
//@EnableReactiveFeignClients
@EnableDiscoveryClient
@KubernetesApplication(
        //livenessProbe = @Probe(httpActionPath = "/actuator/health"),
        //readinessProbe = @Probe(httpActionPath = "/actuator/health"),
        serviceType = ServiceType.LoadBalancer,
        imagePullPolicy = ImagePullPolicy.Never,
        name = "webflux-client",
        replicas = 1
)
public class WebfluxLearningClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxLearningClientApplication.class, args);
    }

    /*@Bean
    public Sampler sampler() {
        return Sampler.create(1);
    }*/

    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }
}
