package app.vercel.adityanmahapatra.runnerz.run;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record Run(
    @Id
    Integer id,
    @NotEmpty
    String title,
    LocalDateTime startedOn,
    LocalDateTime completedOn,
    @Positive
    Integer miles,
    location location,
    @Version
    Integer version
) {

    public Run{
        if (!completedOn.isAfter(startedOn)){
            throw new IllegalArgumentException("CompletedOn must be after StartedOn");
        }
        if (title.isEmpty()){
            throw new IllegalArgumentException("title cannot be empty");
        }
    }
}
