package com.example.guitarforbegginers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableAspectJAutoProxy
@EnableJpaAuditing
@SpringBootApplication
public class GuitarForBegginersApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuitarForBegginersApplication.class, args);
    }

}
