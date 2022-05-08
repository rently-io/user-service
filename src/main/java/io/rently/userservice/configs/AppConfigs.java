package io.rently.userservice.configs;

import io.jsonwebtoken.SignatureAlgorithm;
import io.rently.userservice.middlewares.Interceptor;
import io.rently.userservice.services.MailerService;
import io.rently.userservice.utils.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfigs implements WebMvcConfigurer {

    @Value("${mailer.secret}")
    public String secret;
    @Value("${mailer.algo}")
    public SignatureAlgorithm algo;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*").allowedHeaders("*");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Interceptor(new Jwt(secret, algo), RequestMethod.GET));
    }

    @Bean
    public Jwt jwt(
            @Value("${server.secret}") String secret,
            @Value("${server.algo}") SignatureAlgorithm algo
    ) {
        return new Jwt(secret, algo);
    }

    @Bean
    public MailerService mailerService(
            @Value("${mailer.secret}") String secret,
            @Value("${mailer.algo}") SignatureAlgorithm algo,
            @Value("${mailer.baseurl}") String baseUrl
    ) {
        return new MailerService(new Jwt(secret, algo), baseUrl, new RestTemplate());
    }
}