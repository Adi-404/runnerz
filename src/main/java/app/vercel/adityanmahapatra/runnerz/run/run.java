package app.vercel.adityanmahapatra.runnerz.run;

import java.time.LocalDateTime;

public record run(
    Integer id,
    String title,
    LocalDateTime startedOn,
    LocalDateTime completedOn,
    Integer miles,
    location location
) {}
