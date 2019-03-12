package org.rwchildress.lifegroupservices.diagnostics;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
@CrossOrigin(origins = { "http://localhost:4200", "https://rwchildress.org" })
public class HealthCheckController {

    @GetMapping(value = "/healthCheck", headers = "Accept=application/json")
    public String healthCheck() {
        return "{\"Status\" : \"OK\"}";
    }

}