package pl.pjatk.movieService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
@SpringBootApplication
public class MovieServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(MovieServiceApplication.class, args);
	}
	@Bean
	public OpenAPI customOpenAPI(@Value("${application-description}") String appDesciption, @Value("${application-version}") String appVersion) {
		return new OpenAPI()
				.info(new Info()
								.title("Movie Service JAZ")
								.version(appVersion)
								.description(appDesciption)
								.termsOfService("http://swagger.io/terms/")
								.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}
}
