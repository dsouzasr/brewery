package io.spring.cloud.samples.brewery.zuul;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.discovery.composite.CompositeDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.sleuth.util.ExceptionUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Configuration
@EnableZuulProxy
@EnableDiscoveryClient
public class Application {
    public static void main(String[] args) {
        ExceptionUtils.setFail(true);
        new SpringApplication(Application.class).run(args);
    }


}

@Configuration
@Profile("consul")
class ConsulConfig {
    // TODO: Remove this cause it should come from auto configuration
    @Bean
    @Primary
    public CompositeDiscoveryClient compositeDiscoveryClient(List<DiscoveryClient> discoveryClients) {
        return new CompositeDiscoveryClient(discoveryClients);
    }
}