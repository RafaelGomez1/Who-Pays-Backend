package whopays.groupexpenses.services;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.models.GroupExpenses.Group;
import whopays.groupexpenses.models.GroupExpenses.GroupExpense;
import whopays.groupexpenses.models.GroupExpenses.GroupUser;
import whopays.groupexpenses.repositories.GroupRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final UserService userService;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, UserService userService) {
        this.groupRepository = groupRepository;
        this.userService = userService;
    }

    @Override
    public Mono<Group> createGroup(Group group) {
         return groupRepository.insert(group);
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
    public Mono<ResponseEntity<Group>> addUserToGroup(String groupId, GroupUser groupUser) {
        return groupRepository.findById(groupId)
                .flatMap(group -> {
                    group.setMembers(addUserToGroup(groupUser, group.getMembers()));
                    return groupRepository.save(group).thenReturn(group);
                }).map(updatedGroup -> new ResponseEntity<>(updatedGroup, HttpStatus.CREATED))
                .defaultIfEmpty((new ResponseEntity<>(HttpStatus.CONFLICT)));
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteGroup(String groupdId) {
        return groupRepository.findById(groupdId)
                .flatMap(group -> groupRepository.deleteById(groupdId)
                        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                ).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public Mono<ResponseEntity<Group>> deleteUserFromGroup(String groupId, ObjectId userId) {
        return groupRepository.findById(groupId).flatMap( group1 -> {
            group1.setMembers(deleteUserFromList(group1.getMembers(), userId));
            return groupRepository.save(group1).thenReturn(group1);
        }).map(updatedGroup -> new ResponseEntity<>(updatedGroup, HttpStatus.OK))
                .defaultIfEmpty((new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }

    @Override
    public Mono<ResponseEntity<Group>> addExpensesToGroup(GroupExpense groupExpenses, String groupId) {
        return groupRepository.findById(groupId)
                .flatMap(group -> {
                    group.setGroupExpenses(addExpenses(groupExpenses, group.getGroupExpenses()));
                    return groupRepository.save(group);
                }).map(updatedGroup -> new ResponseEntity<>(updatedGroup, HttpStatus.OK))
                .defaultIfEmpty((new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }

    @Override
    public Mono<ResponseEntity<Group>> deleteExpensesFromGroup(String groupExpensesId, String groupId) {
        return groupRepository.findById(groupId)
                .flatMap(group -> {
                    group.setGroupExpenses(removeExpenses(groupExpensesId, group.getGroupExpenses()));
                    return groupRepository.save(group);
                }).map(updatedGroup -> new ResponseEntity<>(updatedGroup, HttpStatus.OK))
                .defaultIfEmpty((new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }

    @Override
    public Mono<ResponseEntity<Group>> updateExpensesFromGroup(GroupExpense groupExpenses, String groupId) {
        return groupRepository.findById(groupId)
                .flatMap(group -> {
                    group.setGroupExpenses(checkExpenses(group, groupExpenses));
                    return groupRepository.save(group);
                }).map(updatedGroup -> new ResponseEntity<>(updatedGroup, HttpStatus.OK))
                .defaultIfEmpty((new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }

    @Override
    public Mono<ResponseEntity<Group>> addAdminToGroup(GroupUser admin, String groupId) {
        return groupRepository.findById(groupId)
                .flatMap(group -> {
                    group.setAdmins(addUserToGroup(admin, group.getAdmins()));
                    return groupRepository.save(group).thenReturn(group);
                }).map(updatedGroup -> new ResponseEntity<>(updatedGroup, HttpStatus.OK))
                .defaultIfEmpty((new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }

    @Override
    public Mono<ResponseEntity<Group>> removeAdminFromGroup(ObjectId adminId, String groupId) {
       return groupRepository.findById(groupId)
               .flatMap(group -> {
                   group.setAdmins(deleteUserFromList(group.getAdmins(), adminId));
                   return groupRepository.save(group).thenReturn(group);
               }).map(updatedGroup -> new ResponseEntity<>(updatedGroup, HttpStatus.OK))
                .defaultIfEmpty((new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }

    private List<GroupUser> deleteUserFromList(List<GroupUser> membersId, ObjectId userId) {
        List<GroupUser> modifiedMembers =
                membersId.stream()
                        .filter(member -> !member.getId().equals(userId))
                        .collect(Collectors.toList());
        return modifiedMembers;
    }

    private List<GroupUser> addUserToGroup(GroupUser user, List<GroupUser> members) {
        if (members != null && !containsItem(user, members)) {
            members.add(user);
        }
        return members;
    }

    private boolean containsItem(GroupUser user, List<GroupUser> members) {
        return members.stream().anyMatch(member -> member.equals(user));
    }

    private List<GroupExpense> removeExpenses(String groupExpensesId, List<GroupExpense> list) {
        return list.stream()
                .filter(groupExpense -> !groupExpense.getId().equals(groupExpensesId))
                .collect(Collectors.toList());
    }

    private List<GroupExpense> addExpenses(GroupExpense groupExpense, List<GroupExpense> list) {
        list.add(groupExpense);
        return list;
    }

    private List<GroupExpense> checkExpenses(Group group, GroupExpense expenses) {
        List<GroupExpense> list = group.getGroupExpenses();
        list.forEach(exp -> {
            if (exp.getId().equals(expenses.getId())) {
                if (!exp.getConcept().equals(expenses.getConcept())) {
                    exp.setConcept(expenses.getConcept());
                }
                if(exp.getTotalQuantity() != expenses.getTotalQuantity()) {
                    exp.setTotalQuantity(expenses.getTotalQuantity());
                }
                if(!exp.getDebtors().equals(expenses.getDebtors())) {
                    exp.setDebtors(expenses.getDebtors());
                }
                if(!exp.getPayers().equals(expenses.getPayers())) {
                    exp.setPayers(expenses.getPayers());
                }
                if(!exp.getDate().equals(expenses.getDate())) {
                    exp.setDate(expenses.getDate());
                }
                if(!exp.getCategory().equals(expenses.getCategory())) {
                    exp.setCategory(expenses.getCategory());
                }
            }
        });
        return list;
    }
}
