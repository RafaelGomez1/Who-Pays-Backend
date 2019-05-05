package whopays.groupexpenses.repositories;

import reactor.core.publisher.Flux;
import whopays.groupexpenses.models.User;

public interface GroupCustomRepository {

    Flux<User> findAllMembersId(String groupId);
}
