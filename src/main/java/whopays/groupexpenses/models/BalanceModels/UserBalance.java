package whopays.groupexpenses.models.BalanceModels;

import java.util.ArrayList;
import java.util.List;

public class UserBalance {

    private String username;
    private double totalDebt;
    private double totalActive;
    private List<Assets> assets;
    private List<Debts> debts;

    public UserBalance() {
        this.debts = new ArrayList<>();
        this.assets = new ArrayList<>();
        this.totalActive = 0.0;
        this.totalDebt = 0.0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(double totalDebt) {
        this.totalDebt = totalDebt;
    }

    public double getTotalActive() {
        return totalActive;
    }

    public void setTotalActive(double totalActive) {
        this.totalActive = totalActive;
    }

    public List<Assets> getAssets() {
        return assets;
    }

    public void setAssets(List<Assets> assets) {
        this.assets = assets;
    }

    public List<Debts> getDebts() {
        return debts;
    }

    public void setDebts(List<Debts> debts) {
        this.debts = debts;
    }

    public void addAsset(Assets assets) {
        this.assets.add(assets);
    }

    public void addDebt(Debts debts) { this.debts.add(debts); }

    public void addTotalDebts(double totalDebt) { this.totalDebt+= totalDebt;}

    public void addTotalAssets(double totalAssets) {this.totalActive+= totalAssets;}

    public void subTotalDebts(double totalDebt) { this.totalDebt-= totalDebt;}

    public void subTotalAssets(double totalAssets) {this.totalActive-= totalAssets;}

}
