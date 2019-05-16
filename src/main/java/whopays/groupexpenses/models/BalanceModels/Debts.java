package whopays.groupexpenses.models.BalanceModels;

import java.util.Date;

public class Debts {

    private String username;
    private double quantity;
    private String concept;
    private Date debtDate;

    public Debts(String username, double quantity, String concept, Date debtDate) {
        this.username = username;
        this.quantity = quantity;
        this.concept = concept;
        this.debtDate = debtDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public Date getDebtDate() {
        return debtDate;
    }

    public void setDebtDate(Date debtDate) {
        this.debtDate = debtDate;
    }
}
