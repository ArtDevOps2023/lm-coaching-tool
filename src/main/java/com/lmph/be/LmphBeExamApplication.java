package com.lmph.be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.lmph.be")
@EntityScan(basePackages = "com.lmph.be.entity")
public class LmphBeExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmphBeExamApplication.class, args);
	}

}
