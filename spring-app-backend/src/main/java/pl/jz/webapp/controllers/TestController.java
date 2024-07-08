package pl.jz.webapp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jz.webapp.config.AppProperties;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
@EnableConfigurationProperties(AppProperties.class)
public class TestController {

    private final AppProperties appProperties;

    @GetMapping("/test")
    public String getTest() {
        return String.format("Hello, Test from %s ",appProperties.getApplicationName());
    }
}
