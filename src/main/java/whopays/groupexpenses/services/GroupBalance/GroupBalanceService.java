package whopays.groupexpenses.services.GroupBalance;

import reactor.core.publisher.Mono;
import whopays.groupexpenses.models.BalanceModels.GroupBalance;

public interface GroupBalanceService {

    Mono<GroupBalance> getGroupBalance(String groupId);
}
