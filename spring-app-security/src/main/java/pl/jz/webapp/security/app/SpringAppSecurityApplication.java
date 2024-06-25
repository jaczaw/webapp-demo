package pl.jz.webapp.security.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pl.jz.webapp.security.app.config.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class SpringAppSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAppSecurityApplication.class, args);
	}

}
