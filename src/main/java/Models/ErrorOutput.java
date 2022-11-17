package Models;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorOutput {
    @Expose
    private final String type = "error";
    @Expose
    private String message;

    public ErrorOutput(String message) {
        this.message = message;
    }
}
