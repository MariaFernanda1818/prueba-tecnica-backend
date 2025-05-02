package co.prueba.tenica.backend.utils.configuration;

import org.springframework.web.server.WebFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class WebConfig {

    @Bean
    public WebFilter forwardedHeaderFilter() {
        return (exchange, chain) -> chain.filter(exchange); // opcional: lógica extra
    }
}
