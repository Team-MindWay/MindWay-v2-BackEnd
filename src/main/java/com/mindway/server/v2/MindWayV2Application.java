package com.mindway.server.v2;

import com.sun.jna.platform.mac.SystemB;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.TimeZone;

@EnableScheduling
@SpringBootApplication
public class MindWayV2Application {

	@PostConstruct
	public void started() {TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));}

	public static void main(String[] args) {
		SpringApplication.run(MindWayV2Application.class, args);
	}

}
