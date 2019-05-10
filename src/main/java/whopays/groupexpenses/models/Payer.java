package whopays.groupexpenses.models;

public class Payer {

    private GroupUser payer;
    private float quantity;

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


}
