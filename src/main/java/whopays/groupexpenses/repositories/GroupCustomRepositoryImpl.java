package whopays.groupexpenses.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import whopays.groupexpenses.models.User;

public class GroupCustomRepositoryImpl implements GroupCustomRepository {

    @Autowired
    ReactiveMongoTemplate mongoTemplate;

    @Override
    public Flux<User> findAllMembersIdParam(String groupId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(groupId));
        query.fields().include("members");
        return mongoTemplate.find(query, User.class);
    }
}
