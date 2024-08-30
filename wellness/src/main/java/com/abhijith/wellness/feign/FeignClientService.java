package com.abhijith.wellness.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "auth-service", url = "http://localhost:8081")
public interface FeignClientService {

    @GetMapping("auth/validateId")
    public Boolean validateId(@RequestParam("id") String id);

    @GetMapping("athletes/validate")
    public Boolean validateAthleteId(@RequestParam("id") String id);

    @GetMapping("athletes/findByUserId")
    public String findAthleteByUserId(@RequestParam("userId") String userId);
}
