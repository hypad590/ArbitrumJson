package com.example.Micros.Service;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;

@org.springframework.stereotype.Service
public class Service {

    private final RestTemplate restTemplate;
    private JSONObject jsonObject;
    public Service(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getLatestBlockInfo() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        ResponseEntity<String> response = restTemplate.postForEntity(
                "https://arb1.arbitrum.io/rpc",
                new HttpEntity<>(
                        "{\"jsonrpc\":\"2.0\",\"method\":\"" +
                                "eth_getBlockByNumber" +
                                "\",\"params\":" +
                                "[\"latest\", true]" +
                                ",\"id\":1}",
                        headers
                ), String.class);
                jsonObject = new JSONObject(response);
        try{
            FileWriter writer = new FileWriter("out.txt");
            writer.write(callJson());
            writer.close();
        }catch (IOException e){
            System.out.println("WHAAA?!");
            e.printStackTrace();
        }
        return callJson();
    }
    public String callJson(){
        return String.format(
                "Headers: %s\nBody: %sStatusCodeValue: %s",
                jsonObject.get("headers"),
                jsonObject.get("body"),
                jsonObject.get("statusCodeValue")
        );
    }
}
