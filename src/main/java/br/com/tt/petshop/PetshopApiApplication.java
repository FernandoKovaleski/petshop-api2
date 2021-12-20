package br.com.tt.petshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PetshopApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetshopApiApplication.class, args);
    }
}
