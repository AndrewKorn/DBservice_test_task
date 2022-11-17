package Models.Search;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SearchOutput {
    @Expose
    private final String type = "search";
    @Expose
    private List<Result> results;

    private String message;
}
