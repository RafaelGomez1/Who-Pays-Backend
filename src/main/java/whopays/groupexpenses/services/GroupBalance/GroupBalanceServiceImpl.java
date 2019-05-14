package whopays.groupexpenses.services.GroupBalance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.models.BalanceModels.GroupBalance;
import whopays.groupexpenses.models.GroupExpenses.Group;
import whopays.groupexpenses.repositories.GroupRepository;

@Service
public class GroupBalanceServiceImpl implements GroupBalanceService{

    private final GroupRepository groupRepository;

    @Autowired
    public GroupBalanceServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public Mono<GroupBalance> getGroupBalance(String groupId) {
        Mono<Group> group = groupRepository.findById(groupId);
        return null;
    }
}
