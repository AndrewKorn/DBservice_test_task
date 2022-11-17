package Models.Stat;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class CustomerStat {
    @Expose
    private String name;
    @Expose
    private List<PurchaseStat> purchases;
    @Expose
    private int totalExpenses = 0;

    public CustomerStat(String name, List<PurchaseStat> purchases) {
        this.name = name;
        this.purchases = purchases;
    }

}
