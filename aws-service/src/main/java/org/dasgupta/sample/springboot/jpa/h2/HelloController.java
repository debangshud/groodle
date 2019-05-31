package org.dasgupta.sample.springboot.jpa.h2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.Inet4Address;
import java.net.UnknownHostException;

@RestController
public class HelloController {

    @GetMapping("/")
    public String sayHello() throws UnknownHostException {

        String hostName = Inet4Address.getLocalHost().getHostName();

        return "Hello from " + hostName;
    }
}
