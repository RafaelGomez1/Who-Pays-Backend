package whopays.groupexpenses.handlers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.services.GroupBalance.GroupBalanceService;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
public class GroupBalanceHandler {

    private final GroupBalanceService groupBalanceService;
    private static Mono<ServerResponse> notFound = ServerResponse.notFound().build();

    @Autowired
    public GroupBalanceHandler(GroupBalanceService groupBalanceService) {
        this.groupBalanceService = groupBalanceService;
    }

    public Mono<ServerResponse> getGroupBalanceFiltered(ServerRequest serverRequest) {
        String groupId = serverRequest.pathVariable("groupId");
        return groupBalanceService.getGroupBalanceWithFilter(groupId)
                .flatMap( group ->
                        ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(fromObject(group)));
    }
}
