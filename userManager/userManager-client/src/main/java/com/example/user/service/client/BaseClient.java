package com.example.user.service.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.MediaType;
import java.util.Map;

@Slf4j
public class BaseClient {
    private static final Integer CONNECT_TIME_OUT = 1000 * 60; //millis
    private static final Integer READ_TIME_OUT = 1000 * 30; // millis
    protected Client client;

    public BaseClient() {
        this.client = createClient();
    }

    private Client createClient() {
        com.sun.jersey.api.client.config.ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getProperties().put(com.sun.jersey.api.client.config.ClientConfig.PROPERTY_CONNECT_TIMEOUT, CONNECT_TIME_OUT);
        clientConfig.getProperties().put(ClientConfig.PROPERTY_READ_TIMEOUT, READ_TIME_OUT);

        Client client = Client.create(clientConfig);
        return client;
    }

    protected com.sun.jersey.api.client.ClientResponse call(String url, MediaType mediaType, CallType callType,
                                                            Object requestMap, Map<String, Object> headerMap) {
        com.sun.jersey.api.client.ClientResponse response;
        WebResource.Builder builder = client.resource(url).accept(MediaType.APPLICATION_JSON_TYPE).type(mediaType);
        if (headerMap != null) {
            for (Map.Entry<String, Object> entry : headerMap.entrySet()) {
                builder.header(entry.getKey(), entry.getValue());
            }
        }
        long startTime = System.currentTimeMillis();
        if (callType.equals(CallType.POST)) {
            response = builder.post(com.sun.jersey.api.client.ClientResponse.class, requestMap);
        } else if (callType.equals(CallType.PUT)) {
            response = builder.put(com.sun.jersey.api.client.ClientResponse.class, requestMap);
        } else {
            response = builder.get(com.sun.jersey.api.client.ClientResponse.class);
        }

        long endTime = System.currentTimeMillis();
        log.info("Response of Calling " + callType + " request for "
            + url + " with body: " + requestMap + " and headers : " + headerMap + " is : " + response
            + " and took " + (endTime - startTime) + " millis.");

        return response;
    }

    protected enum CallType {
        POST,
        PUT,
        GET
    }
}
