package whopays.groupexpenses.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.models.Group;
import whopays.groupexpenses.models.User;
import whopays.groupexpenses.repositories.GroupRepository;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public Mono<Void> createGroup(Group group) {
        return groupRepository.save(group).then();
    }

    @Override
    public Mono<Group> findById(String id) {
        return groupRepository.findById(id);
    }

    @Override
    public Flux<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        groupRepository.deleteById(id).block();
    }

    @Override
    public Mono<Void> addUserToGroup(String userId, String groupId) {
        return null;
    }

    @Override
    public Flux<User> getAllGroupMembers(String id) {
        Flux<User> users = groupRepository.findAllMembersId(id);
        return users;
    }
}
