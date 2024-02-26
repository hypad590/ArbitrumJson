package com.example.Micros.Controller;

import com.example.Micros.Service.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private final Service service;

    public Controller(Service service){
        this.service = service;
    }

    @GetMapping("/latest-block")
    public String getBlock(){
        return service.getLatestBlockInfo();
    }
}
