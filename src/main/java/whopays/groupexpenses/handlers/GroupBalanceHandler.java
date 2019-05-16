package whopays.groupexpenses.handlers;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.models.BalanceModels.GroupBalance;
import whopays.groupexpenses.services.GroupBalance.GroupBalanceService;

@Component
public class GroupBalanceHandler {

    private final GroupBalanceService groupBalanceService;
    private static Mono<ServerResponse> notFound = ServerResponse.notFound().build();

    @Autowired
    public GroupBalanceHandler(GroupBalanceService groupBalanceService) {
        this.groupBalanceService = groupBalanceService;
    }

    public Mono<ServerResponse> getGroupBalanceFiltered(ServerRequest serverRequest) {
        Mono<ObjectId> groupId = serverRequest.bodyToMono(ObjectId.class);
        return groupId.flatMap( group ->
                ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(groupBalanceService.getGroupBalanceWithFilter(group), GroupBalance.class));

    }
}
