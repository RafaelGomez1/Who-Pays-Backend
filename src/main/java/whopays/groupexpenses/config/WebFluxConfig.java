package whopays.groupexpenses.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class WebFluxConfig implements WebFluxConfigurer {

    private static final String ALLOWED_HEADERS = "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN, Accept";

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders(ALLOWED_HEADERS)
                .allowedMethods("PUT, GET, POST, DELETE, OPTIONS")
                .maxAge(3600);
    }


//    @Bean
 /*   public WebFluxConfigurer corsConfigurer() {
        return new WebFluxConfigurerComposite() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*")
                        .allowedMethods("*");
            }
        };
    }*/
}