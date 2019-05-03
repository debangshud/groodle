package org.dasgupta.sample.springboot.docker.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/")
    public String home() {
        logger.info("invoked");
        return "Hello World, I am Acacia";
    }

}
