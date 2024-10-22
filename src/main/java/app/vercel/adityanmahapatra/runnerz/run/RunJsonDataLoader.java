package app.vercel.adityanmahapatra.runnerz.run;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.*;
import java.io.*;


@Component
public class RunJsonDataLoader implements CommandLineRunner{
    private static final Logger log = LoggerFactory.getLogger(RunJsonDataLoader.class);
    private final jdbcCLientRunRepository runRepository;
    private final ObjectMapper objectMapper;

    public RunJsonDataLoader(jdbcCLientRunRepository runRepository, ObjectMapper objectMapper){
        this.runRepository = runRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if(runRepository.count() != 0){
            try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/runs.json")){
            Runs allRuns =objectMapper.readValue(inputStream, Runs.class);
            log.info ("Reading {} runs from JSON data and saving to a database.", allRuns.runs().size());
            runRepository.saveAll(allRuns.runs());

        } catch (IOException e){
            throw new RuntimeException("Error loading JSON data", e);
        }
        
    }else {
        log.info("Runs already loaded. Skipping data loading.");
    }

}
}

