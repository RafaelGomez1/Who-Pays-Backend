package whopays.groupexpenses.services.Payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.models.GroupExpenses.Group;
import whopays.groupexpenses.models.GroupExpenses.GroupExpense;
import whopays.groupexpenses.models.GroupExpenses.Payer;
import whopays.groupexpenses.models.GroupExpenses.Payment;
import whopays.groupexpenses.repositories.GroupRepository;

import java.util.Collections;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final GroupRepository groupRepository;

    @Autowired
    public PaymentServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public Mono<Group> addPaymentToGroupExpense(Payment payment, String groupId) {
        Mono<Group> group = groupRepository.findById(groupId);
        return group.flatMap(gr -> {
            Payer paymentPayer = payment.getPayer();
            List<GroupExpense> expenses = gr.getGroupExpenses();

            //Retrieve the groupExpense we are gonna add the payment on
            GroupExpense groupExpense = gr.getGroupExpenses().stream()
                                    .filter(expense -> expense.getId().equals(payment.getGroupExpensesId()))
                                    .findFirst()
                                    .orElse(null);

            if (groupExpense != null) {
                //Find the first debt we have
                List<Payer> payers = groupExpense.getPayers();
                Payer payer = payers.stream()
                                .filter(pay -> pay.getDebtor().getId().equals(paymentPayer.getPayer().getId()))
                                .findFirst()
                                .orElse(null);
                if (payer != null) {
                    //Your debt is now solved and is removed from the list
                    if (payer.getQuantity() - paymentPayer.getQuantity() == 0) {
                        expenses.remove(expenses.indexOf(groupExpense));
                    } else {
                        Payer updatedPayer = payer;
                        updatedPayer.setQuantity(payer.getQuantity() - paymentPayer.getQuantity());
                        Collections.replaceAll(payers, payer, updatedPayer);
                    }
                    groupExpense.addPayments(payment);
                    groupExpense.addTotalDebPaid(paymentPayer.getQuantity());
                    groupExpense.setPayers(payers);
                    gr.setGroupExpenses(expenses);
                }
            }
            return groupRepository.save(gr);
        });

    }
}
