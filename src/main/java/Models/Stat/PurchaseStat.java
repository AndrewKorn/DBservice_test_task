package Models.Stat;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PurchaseStat implements Comparable<PurchaseStat> {
    @Expose
    private String name;
    @Expose
    private int expenses;

    public PurchaseStat(String name, int expenses) {
        this.name = name;
        this.expenses = expenses;
    }

    @Override
    public int compareTo(PurchaseStat purchaseStat) {
        return Integer.compare(purchaseStat.expenses, expenses);
    }
}
