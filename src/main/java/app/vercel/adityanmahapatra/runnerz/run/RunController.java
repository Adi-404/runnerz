package app.vercel.adityanmahapatra.runnerz.run;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping ("/api/runs")
public class RunController {

    
    private final RunRepository runRepository;

    public RunController (RunRepository runRepository){
        this.runRepository = runRepository;
    }

    @GetMapping("")

    List<run> findAll(){
        return runRepository.findAll();
    }

    @GetMapping("/{id}")
    run findById(@PathVariable Integer id) {
        Optional<run> run = runRepository.findById(1);

        if (run.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return run.get();
    }


}
