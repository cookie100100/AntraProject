package org.example.otherserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDiscoveryClient
public class OtherserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(OtherserverApplication.class, args);
    }

}
