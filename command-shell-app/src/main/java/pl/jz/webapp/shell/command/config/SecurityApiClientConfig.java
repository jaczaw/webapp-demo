package pl.jz.webapp.shell.command.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.client.RestClientSsl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import pl.jz.webapp.shell.command.client.SecurityApiClient;

@Configuration
public class SecurityApiClientConfig {

    @Value("${security-api-url}")
    private String securityApiUrl;

    @Bean
    public SecurityApiClient securityApiClient(RestClientSsl ssl ) {

        RestClient restClient = RestClient.builder()
                .baseUrl(securityApiUrl)
                .apply(ssl.fromBundle("demo"))
                .build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(SecurityApiClient.class);
    }
}
