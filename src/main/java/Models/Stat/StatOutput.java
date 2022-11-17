package Models.Stat;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class StatOutput {
    @Expose
    private final String type = "stat";
    @Expose
    private int totalDays;
    @Expose
    private List<CustomerStat> customers;
    @Expose
    private int totalExpenses = 0;
    @Expose
    private int avgExpenses;

    private String message;

    public StatOutput(int totalDays, List<CustomerStat> customers) {
        this.totalDays = totalDays;
        this.customers = customers;
    }
}
