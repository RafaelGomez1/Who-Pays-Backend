package whopays.groupexpenses.models.GroupExpenses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Document("GroupExpenses")
public class GroupExpense {

    @Id
    private String id;
    private String concept;
    private double totalQuantity;
    private double totalDebt;
    private double totalDebtPaid;
    private List<Debtor> debtors;
    private List<Payer> payers;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date date;
    private Category category;
    private ExpenseStatus status;
    private List<Payment> payments;

    public GroupExpense() {
        this.payments = new ArrayList<>();
        this.status = ExpenseStatus.NOT_PAID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public double getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(double totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public List<Debtor> getDebtors() {
        return debtors;
    }

    public void setDebtors(List<Debtor> debtors) {
        this.debtors = debtors;
    }

    public List<Payer> getPayers() {
        return payers;
    }

    public void setPayers(List<Payer> payers) {
        this.payers = payers;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void addPayer(Payer payer) {this.payers.add(payer);}

    public void setCategory(Category category) {
        this.category = category;
    }

    public ExpenseStatus getStatus() {
        return status;
    }

    public void setStatus(ExpenseStatus status) {
        this.status = status;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public void addPayments(Payment payment) {this.payments.add(payment);}

    public double getTotalDebtPaid() {
        return totalDebtPaid;
    }

    public void setTotalDebtPaid(double totalDebtPaid) {
        this.totalDebtPaid = totalDebtPaid;
    }

    public double getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(double totalDebt) {
        this.totalDebt = totalDebt;
    }

    public void addTotalDebPaid (double totalDebtPaid) {
        this.totalDebtPaid+= totalDebtPaid;
        if (totalDebt == this.totalDebtPaid) {
            this.status = ExpenseStatus.PAID;
        } else if (this.totalDebtPaid >0 && this.status != ExpenseStatus.PARTIALLY_PAID) {
            this.status = ExpenseStatus.PARTIALLY_PAID;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupExpense that = (GroupExpense) o;
        return getTotalQuantity() == that.getTotalQuantity() &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getConcept(), that.getConcept()) &&
                Objects.equals(getDebtors(), that.getDebtors()) &&
                Objects.equals(getPayers(), that.getPayers()) &&
                Objects.equals(getDate(), that.getDate()) &&
                Objects.equals(getCategory(), that.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getConcept(), getTotalQuantity(), getDebtors(), getPayers(), getDate(), getCategory());
    }
}
