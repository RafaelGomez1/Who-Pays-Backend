package whopays.groupexpenses.models.GroupExpenses;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class Payment {

    @Id
    private ObjectId id;
    private String groupExpensesId;
    private String concept;
    private Date paymentDate;
    private Payer payer;

    public Payment() {
        this.id = ObjectId.get();
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

    public String getGroupExpensesId() {
        return groupExpensesId;
    }

    public void setGroupExpensesId(String groupExpensesId) {
        this.groupExpensesId = groupExpensesId;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }
}
