package whopays.groupexpenses.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.models.Group;
import whopays.groupexpenses.repositories.GroupRepository;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public Mono<Group> createGroup(Group group) {
        return groupRepository.save(group).thenReturn(group);
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
}
