package whopays.groupexpenses.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.models.Group;
import whopays.groupexpenses.models.GroupExpense;
import whopays.groupexpenses.models.User;
import whopays.groupexpenses.repositories.GroupRepository;
import whopays.groupexpenses.repositories.UserRepository;
import whopays.groupexpenses.services.GroupService;

import java.util.List;

@Slf4j
@RestController
@EnableAutoConfiguration
public class GroupController {

    private final GroupService groupService;
    private final GroupRepository groupRepository;
    @Autowired
    public GroupController(GroupService groupService, GroupRepository groupRepository) {
        this.groupService = groupService;
        this.groupRepository = groupRepository;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("groups/{groupId}")
    public Mono<Group> getGroup(@PathVariable("groupId") String groupId) {
        return groupService.findById(groupId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("groups")
    public Flux<Group> getAllGroups() {
        return groupService.findAll();
    }

    @DeleteMapping("groups/{groupdId}/delete")
    public Mono<ResponseEntity<Void>> deleteGroup(@PathVariable("groupdId") String groupdId) {
        return groupRepository.findById(groupdId)
                .flatMap(group -> groupRepository.deleteById(group.getId())
                    .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                ).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("groups/create")
    public Mono<Void> createGroup(@RequestBody Group group) {
        return groupService.createGroup(group);
    }

    @PostMapping("groups/{groupId}/user/add")
    public Mono<Void> addUserToGroup(@RequestBody String userId,
                                     @PathVariable("groupId") String groupId) {
        return groupService.addUserToGroup(userId, groupId);
    }

    @PutMapping("groups/{groupId}/user/delete")
    public  Mono<ResponseEntity<Group>> deleteUserFromGroup(@RequestBody String userId,
                                                           @PathVariable("groupId") String groupId) {

     Mono<Group> group = groupRepository.findById(groupId);
     return group.flatMap( group1 -> {
         group1.setMembers(deleteUserFromList(group1.getMembers(), userId));
         return groupRepository.save(group1);
     }).map(updatedGroup -> new ResponseEntity<>(updatedGroup, HttpStatus.OK))
             .defaultIfEmpty((new ResponseEntity<>(HttpStatus.NOT_FOUND)));

    }

    private List<User> deleteUserFromList(List<User> members, String userId) {
        members.forEach((member) -> {
            if(member.getId().equals(userId)) {
                members.remove(members.indexOf(member));
            }
        });
        return members;
    }

    @PostMapping("groups/{groupId}/expenses/add")
    public  Mono<ResponseEntity<Group>> addExpensesToGroup( @RequestBody GroupExpense groupExpenses,
                                                            @PathVariable("groupId") String groupId) {
        return groupRepository.findById(groupId)
                .flatMap(group -> {
                    group.setGroupExpenses(addExpenses(groupExpenses, group.getGroupExpenses()));
                    return groupRepository.save(group);
                }).map(updatedGroup -> new ResponseEntity<>(updatedGroup, HttpStatus.OK))
                .defaultIfEmpty((new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }

    private List<GroupExpense> addExpenses(GroupExpense groupExpense, List<GroupExpense> list) {
        list.add(groupExpense);
        return list;
    }

    @PatchMapping("groups/{groupId}/expenses/delete")
    public  Mono<ResponseEntity<Group>> deleteExpensesFromGroup(@RequestBody String groupExpensesId,
                                                                @PathVariable("groupId") String groupId) {
        return groupRepository.findById(groupId)
                .flatMap(group -> {
                    group.setGroupExpenses(removeExpenses(groupExpensesId, group.getGroupExpenses()));
                    return groupRepository.save(group);
                }).map(updatedGroup -> new ResponseEntity<>(updatedGroup, HttpStatus.OK))
                .defaultIfEmpty((new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }

    private List<GroupExpense> removeExpenses(String groupExpensesId, List<GroupExpense> list) {
        list.forEach(expenses -> {
            if (expenses.getId().equals(groupExpensesId)) {
                list.remove(list.indexOf(expenses));
            }
        });
        return list;
    }

    @PutMapping("groups/{groupId}/expenses/update")
    public Mono<ResponseEntity<Group>> updateExpensesFromGroup(@RequestBody GroupExpense groupExpenses,
                                                               @PathVariable("groupId") String groupId) {
        return groupRepository.findById(groupId)
                .flatMap(group -> {
                    return groupRepository.save(checkExpenses(group,groupExpenses));
                }).map(updatedGroup -> new ResponseEntity<>(updatedGroup, HttpStatus.OK))
                .defaultIfEmpty((new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }

    public Group checkExpenses(Group group, GroupExpense expenses) {
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
        group.setGroupExpenses(list);
        return group;
    }

    @GetMapping("groups/{groupId}/members")
    public Flux<User> getAllMembers(@PathVariable("groupId") String groupId) {
        return groupService.getAllGroupMembers(groupId);
    }
}
