package whopays.groupexpenses.models.GroupExpenses;

import whopays.groupexpenses.models.GroupExpenses.GroupUser;

public class Payer {

    private GroupUser payer;
    private float quantity;
    private GroupUser debtor;

    public GroupUser getPayer() {
        return payer;
    }

    public void setPayer(GroupUser payer) {
        this.payer = payer;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public GroupUser getDebtor() {
        return debtor;
    }

    public void setDebtor(GroupUser debtor) {
        this.debtor = debtor;
    }
}
