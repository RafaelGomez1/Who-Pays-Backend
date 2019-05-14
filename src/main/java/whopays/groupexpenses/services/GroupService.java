package whopays.groupexpenses.services;

import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.models.GroupExpenses.Group;
import whopays.groupexpenses.models.GroupExpenses.GroupExpense;
import whopays.groupexpenses.models.GroupExpenses.GroupUser;

public interface GroupService {

    Mono<Group> createGroup(Group command);
    Mono<Group> findById(String id);
    Flux<Group> findAll();
    void deleteById(String id);
    Mono<ResponseEntity<Group>> addUserToGroup(String groupId, GroupUser groupUser);
    Mono<ResponseEntity<Void>> deleteGroup(String groupdId);
    Mono<ResponseEntity<Group>> deleteUserFromGroup(String groupId, ObjectId userId);
    Mono<ResponseEntity<Group>> addExpensesToGroup( GroupExpense groupExpenses, String groupId);
    Mono<ResponseEntity<Group>> deleteExpensesFromGroup(ObjectId groupExpensesId,  String groupId);
    Mono<ResponseEntity<Group>> updateExpensesFromGroup(GroupExpense groupExpenses, String groupId);
    Mono<ResponseEntity<Group>> addAdminToGroup(GroupUser admin, String groupId);
    Mono<ResponseEntity<Group>> removeAdminFromGroup(ObjectId admin, String groupId);
}
