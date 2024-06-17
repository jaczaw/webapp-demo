package pl.jz.webapp.shell.command.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import pl.jz.webapp.shell.command.client.ToDoApiClient;

@Configuration
public class ToDoApiClientConfig {

    @Value("${todo-api.url}")
    private String toDoApiUrl;

    @Bean
    public ToDoApiClient toDoApiClient() {
        RestClient restClient = RestClient.builder().baseUrl(toDoApiUrl).build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(ToDoApiClient.class);
    }
}
