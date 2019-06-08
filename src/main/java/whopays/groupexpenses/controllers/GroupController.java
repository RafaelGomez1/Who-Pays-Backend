package whopays.groupexpenses.controllers;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.models.GroupExpenses.Group;
import whopays.groupexpenses.models.GroupExpenses.GroupExpense;
import whopays.groupexpenses.models.GroupExpenses.GroupUser;
import whopays.groupexpenses.services.GroupService;

@Slf4j
@RestController
@EnableAutoConfiguration
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping(value = "groups/{groupId}/user/add", headers ="Accept=application/json")
    public Mono<ResponseEntity<Group>> addUserToGroup(@PathVariable("groupId") String groupId,
                                                      @RequestBody GroupUser groupUser) {
        return groupService.addUserToGroup(groupId, groupUser);
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
        return groupService.deleteGroup(groupdId);
    }

    @PostMapping("groups/create")
    public Mono<Group> createGroup(@RequestBody Group group) {
        return groupService.createGroup(group);
    }


    @PostMapping("groups/{groupId}/user/delete")
    public  Mono<ResponseEntity<Group>> deleteUserFromGroup(@PathVariable("groupId") String groupId,
                                                            @RequestBody ObjectId userId) {
        return groupService.deleteUserFromGroup(groupId, userId);
    }

    @PostMapping("groups/{groupId}/expenses/add")
    public  Mono<ResponseEntity<Group>> addExpensesToGroup( @RequestBody GroupExpense groupExpenses,
                                                            @PathVariable("groupId") String groupId) {
       return groupService.addExpensesToGroup(groupExpenses, groupId);
    }

    @PostMapping("groups/{groupId}/expenses/delete")
    public  Mono<ResponseEntity<Group>> deleteExpensesFromGroup(@RequestBody String groupExpensesId,
                                                                @PathVariable("groupId") String groupId) {
       return groupService.deleteExpensesFromGroup(groupExpensesId, groupId);
    }

    @PostMapping("groups/{groupId}/expenses/update")
    public Mono<ResponseEntity<Group>> updateExpensesFromGroup(@RequestBody GroupExpense groupExpenses,
                                                               @PathVariable("groupId") String groupId) {
        return groupService.updateExpensesFromGroup(groupExpenses, groupId);
    }

    @PostMapping("groups/{groupId}/admin/add")
    Mono<ResponseEntity<Group>> addAdminToGroup( @RequestBody GroupUser admin,
                                                 @PathVariable("groupId") String groupId) {
        return groupService.addAdminToGroup(admin, groupId);
    }

    @PostMapping("groups/{groupId}/admin/delete")
    Mono<ResponseEntity<Group>> removeAdminFromGroup( @RequestBody ObjectId admin,
                                                      @PathVariable("groupId") String groupId) {
        return groupService.removeAdminFromGroup(admin, groupId);
    }
}
