package whopays.groupexpenses.models.BalanceModels;

import java.util.ArrayList;
import java.util.List;

public class GroupBalance {

    private List<UserBalance> usersBalance;

    public GroupBalance() {
        this.usersBalance = new ArrayList<>();
    }

    public List<UserBalance> getUsersBalance() {
        return usersBalance;
    }

    public void setUsersBalance(List<UserBalance> usersBalance) {
        this.usersBalance = usersBalance;
    }

    public void addUserBalance(UserBalance userBalance) {this.usersBalance.add(userBalance);}
}
