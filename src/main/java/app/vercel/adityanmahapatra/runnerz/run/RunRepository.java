package app.vercel.adityanmahapatra.runnerz.run;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;


import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository

public class RunRepository {


    private List<run> runs = new ArrayList<>();

    List<run> findAll(){
        return runs;
    }

    

    @PostConstruct
    private void init() {
        runs.add(new run(1,
         "Monday Morning Run", 
         LocalDateTime.now(), 
         LocalDateTime.now().plus(30, ChronoUnit.MINUTES), 
         3, 
         location.INDOOR));

        runs.add(new run(2, 
        "TUesday Morning Run", 
        LocalDateTime.now(), 
        LocalDateTime.now().plus(30, 
        ChronoUnit.MINUTES), 
        6, 
        location.OUTDOOR));
        
    }
    Optional<run> findById(Integer id){
        return runs.stream() 
                .filter(run->run.id() == id)
                .findFirst();
    }
}
