package com.abhijith.eventservice.feign;

import com.abhijith.eventservice.client.Athlete;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "auth-service", url = "http://localhost:8081")
public interface FeignClientService {

    @GetMapping("auth/validateId")
    public Boolean validateId(@RequestParam("id") String id);

    @GetMapping("athletes/validate/{id}")
    public Boolean validateAthleteId(@PathVariable("id") String id) ;
    @GetMapping("athletes/{id}")
    public ResponseEntity<Athlete> getAthleteById(@PathVariable String id);
    @GetMapping("athletes/findByUserId/{userId}")
    public String findAthleteIdByUserId(@PathVariable("userId") String userId);
}
