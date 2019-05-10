package whopays.groupexpenses.models;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Document("GroupExpenses")
public class GroupExpense {

    @Id
    private ObjectId id;
    private String concept;
    private long totalQuantity;
    private List<Debtor> debtors;
    private List<Payer> payers;
    private Date date;
    private Category category;
    private ExpenseStatus status;

    public GroupExpense() {
        this.id = ObjectId.get();
        this.status = ExpenseStatus.NOT_PAID;
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

    public long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(long totalQuantity) {
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

    public void setCategory(Category category) {
        this.category = category;
    }

    public ExpenseStatus getStatus() {
        return status;
    }

    public void setStatus(ExpenseStatus status) {
        this.status = status;
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
