package com.csed26.prosim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestProSimApplication {

	public static void main(String[] args) {
		SpringApplication.from(ProSimApplication::main).with(TestProSimApplication.class).run(args);
	}

}
