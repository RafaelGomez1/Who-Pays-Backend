package whopays.groupexpenses.models.GroupExpenses;

public class Debtor {

    private GroupUser user;
    private float quantity;
    private GroupUser userPaid;

    public GroupUser getUser() {
        return user;
    }

    public void setUser(GroupUser user) {
        this.user = user;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public GroupUser getUserPaid() {
        return userPaid;
    }

    public void setUserPaid(GroupUser userPaid) {
        this.userPaid = userPaid;
    }
}
