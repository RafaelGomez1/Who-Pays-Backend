package whopays.groupexpenses.services.GroupBalance;

import org.bson.types.ObjectId;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.models.BalanceModels.GroupBalance;

public interface GroupBalanceService {

    Mono<GroupBalance> getGroupBalanceWithFilter(ObjectId groupId);
}
