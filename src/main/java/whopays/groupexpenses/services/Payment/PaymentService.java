package whopays.groupexpenses.services.Payment;

import org.bson.types.ObjectId;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.models.GroupExpenses.Group;
import whopays.groupexpenses.models.GroupExpenses.Payment;

public interface PaymentService {

    Mono<Group> addPaymentToGroupExpense(Payment Payment, String groupId);
}
