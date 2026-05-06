package com.uxelf.sportpulse.ms_standings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsStandingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsStandingsApplication.class, args);
	}

}
