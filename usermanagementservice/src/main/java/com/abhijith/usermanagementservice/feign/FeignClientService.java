package com.abhijith.usermanagementservice.feign;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.cloud.openfeign.FeignClient(name = "auth-service", url = "http://localhost:8081")
public interface FeignClientService {

    @GetMapping("/validateId")
    public Boolean validateId(@RequestParam("id") String id);
}
