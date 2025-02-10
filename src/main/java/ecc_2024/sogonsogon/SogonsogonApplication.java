package ecc_2024.sogonsogon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class SogonsogonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SogonsogonApplication.class, args);
	}

}
