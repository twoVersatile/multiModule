package com.example.cart.service.server.external;

import com.example.user.service.client.ClientConfig;
import com.example.user.service.client.UserServiceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExternalClientProvider {
    @Value("${user.service.host}")
    private String userServiceHost;

    @Value("${user.service.port}")
    private String userServicePort;


    @Bean
    public UserServiceClient userServiceClient() {
        ClientConfig clientConfig = new ClientConfig();

        clientConfig.setHost(userServiceHost);
        clientConfig.setPort(userServicePort);

        return new UserServiceClient(clientConfig);
    }
}
