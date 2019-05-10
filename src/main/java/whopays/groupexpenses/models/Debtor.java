package whopays.groupexpenses.models;

public class Debtor {

    private GroupUser user;
    private float quantity;

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



}
