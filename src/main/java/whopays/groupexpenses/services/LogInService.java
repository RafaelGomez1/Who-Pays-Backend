package whopays.groupexpenses.services;

import reactor.core.publisher.Mono;
import whopays.groupexpenses.models.LogInObject;

public interface LogInService {

    Mono<Boolean> checkCredentials(LogInObject userCredentials);
}
