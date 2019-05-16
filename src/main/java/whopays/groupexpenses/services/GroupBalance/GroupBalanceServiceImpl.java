package whopays.groupexpenses.services.GroupBalance;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.models.BalanceModels.Assets;
import whopays.groupexpenses.models.BalanceModels.Debts;
import whopays.groupexpenses.models.BalanceModels.GroupBalance;
import whopays.groupexpenses.models.BalanceModels.UserBalance;
import whopays.groupexpenses.models.GroupExpenses.*;
import whopays.groupexpenses.repositories.GroupRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupBalanceServiceImpl implements GroupBalanceService{

    private GroupRepository groupRepository;

    @Autowired
    public GroupBalanceServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Mono<GroupBalance> getGroupBalanceWithFilter(ObjectId groupId) {
        Mono<Group> recoveredGroup = groupRepository.findById(groupId.toString());
        return recoveredGroup.flatMap(group -> {
            List<GroupUser> userGroup = group.getMembers();
            GroupBalance groupBalance = new GroupBalance();
            for (GroupUser member : userGroup) {
                UserBalance userBalance = new UserBalance();
                userBalance.setUsername(member.getUsername());
                for (GroupExpense groupExpense : group.getGroupExpenses()) {
                    for (Payer payer : groupExpense.getPayers().stream()
                            .filter(payer -> payer.getPayer().getUsername().equals(member.getUsername())
                                || payer.getDebtor().getUsername().equals(member.getUsername())).collect(Collectors.toList())) {
                        if (payer.getPayer().getId().equals(member.getId())) {
                            Assets assets = new Assets(payer.getDebtor().getUsername(),
                                    payer.getQuantity()/2, groupExpense.getConcept(), groupExpense.getDate());
                            userBalance.addAsset(assets);
                            userBalance.addTotalAssets(payer.getQuantity()/2);
                        }
                        if (payer.getDebtor().getId().equals(member.getId())) {
                            Debts debts = new Debts(payer.getPayer().getUsername(),
                                    payer.getQuantity()/2, groupExpense.getConcept(), groupExpense.getDate());
                            userBalance.addDebt(debts);
                            userBalance.addTotalDebts(payer.getQuantity()/2);
                        }
                    }
                }
                groupBalance.addUserBalance(userBalance);
            }
            return Mono.just(groupBalance);
        });
    }
}

