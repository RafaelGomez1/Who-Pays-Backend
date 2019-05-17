package whopays.groupexpenses.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.models.GroupExpenses.Group;
import whopays.groupexpenses.models.GroupExpenses.Payment;
import whopays.groupexpenses.services.Payment.PaymentService;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("groups/{groupId}/expenses/payment/add")
    public Mono<Group> addPaymentToGroupExpense(@PathVariable("groupId") String groupId,
                                                @RequestBody Payment payment) {
        return paymentService.addPaymentToGroupExpense(payment, groupId);
    }
}
