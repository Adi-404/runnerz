package app.vercel.adityanmahapatra.runnerz.run;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record run(
    Integer id,
    @NotEmpty
    String title,
    LocalDateTime startedOn,
    LocalDateTime completedOn,
    @Positive
    Integer miles,
    location location
) {

    public run{
        if (!completedOn.isAfter(startedOn)){
            throw new IllegalArgumentException("CompletedOn must be after StartedOn");
        }
        if (title.isEmpty()){
            throw new IllegalArgumentException("title cannot be empty");
        }
    }
}
