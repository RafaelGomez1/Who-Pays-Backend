package whopays.groupexpenses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.publisher.Flux;
import whopays.groupexpenses.models.User;

@SpringBootApplication
@EnableWebFlux
public class GroupexpensesApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroupexpensesApplication.class, args);
    }
}
