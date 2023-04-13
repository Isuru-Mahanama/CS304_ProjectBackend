package com.example.demo;

import com.example.demo.Configuration.FileStorageProperties;
import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EntityScan(basePackages = {"com.example.demo.Model"})
@EnableConfigurationProperties({FileStorageProperties.class})
public class DemoApplication {

	@PostConstruct
	public void setup(){
		Stripe.apiKey = "sk_test_51MQ5hTKYmWyAbs5BgkM3uhaEMitedthL9BYSWA4fmXMV0KCQZL4FaEplhaVpPsvYzy08QLwNHiCxrM1ILfWTC5mh00SC1ZNdVK";
	}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
