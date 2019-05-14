package whopays.groupexpenses.models.GroupExpenses;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Payment {

    @Id
    private ObjectId id;
    private ObjectId groupExpensesId;
    private String concept;
    private Date paymentDate;
    private List<Payer> payers;

    public Payment() {
        this.payers = new ArrayList<>();
        this.paymentDate = new Date();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public List<Payer> getPayers() {
        return payers;
    }

    public void setPayers(List<Payer> payers) {
        this.payers = payers;
    }

    public ObjectId getGroupExpensesId() {
        return groupExpensesId;
    }

    public void setGroupExpensesId(ObjectId groupExpensesId) {
        this.groupExpensesId = groupExpensesId;
    }
}
