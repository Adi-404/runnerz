package app.vercel.adityanmahapatra.runnerz.run;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

// import org.hibernate.validator.internal.util.logging.Log_.logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.slf4j.*;

import jakarta.annotation.PostConstruct;

@Repository

public class RunRepository {


    // private List<run> runs = new ArrayList<>();

    // List<run> findAll(){
    //     return runs;
    // }

    

    // @PostConstruct
    // private void init() {
    //     runs.add(new run(1,
    //      "Monday Morning Run", 
    //      LocalDateTime.now(), 
    //      LocalDateTime.now().plus(30, ChronoUnit.MINUTES), 
    //      3, 
    //      location.INDOOR));

    //     runs.add(new run(2, 
    //     "TUesday Morning Run", 
    //     LocalDateTime.now(), 
    //     LocalDateTime.now().plus(30, 
    //     ChronoUnit.MINUTES), 
    //     6, 
    //     location.OUTDOOR));
        
    // }
    // Optional<run> findById(Integer id){
    //     return runs.stream() 
    //             .filter(run->run.id() == id)
    //             .findFirst();
    // }

    // void create (run run){
    //     runs.add(run);
    // }

    // void update (run run, Integer id){
    //     Optional<run> esxistingrun = findById(id);

    //     if (esxistingrun.isPresent()){
    //         runs.set(runs.indexOf(esxistingrun.get()),run);
    //     }

    // }

    // void delete (Integer id ){
    //     runs.removeIf(run -> run.id().equals(id));
    // }

    private static final Logger log = LoggerFactory.getLogger(RunRepository.class);
    private final JdbcClient jdbcClient;

    public RunRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    public List<run> findAll(){
        return jdbcClient.sql ("select * from run")
                .query(run.class)
                .list();
    }

    public Optional<run> findById(Integer id){

        return jdbcClient.sql("select id,title,started_on,completed_on, miles,location from run where id= :id")
                .param("id", id)
                .query(run.class)
                .optional();

    }

    public void create(run run ){
        var updated = jdbcClient.sql("insert into run(id,title,started_on,completed_on,miles,location) values(?,?,?,?,?,?)")
                .params(List.of(run.id(),run.title(),run.startedOn(),run.completedOn(),run.miles(),run.location().toString()))
                .update();
        
        Assert.state(updated == 1, "Failed to insert run");
    }

    public void update(run run, Integer id){
        var updated = jdbcClient.sql("update run set title = ?, started_on = ?, completed_on = ?, miles = ?, location = ? where id = ?")
                .params(List.of(run.title(),run.startedOn(),run.completedOn(),run.miles(),run.location().toString(),id))
                .update();

        Assert.state(updated == 1,"Failed to update run");
    }

    public void delete(Integer id){
        var updated = jdbcClient.sql("delete from run where id = ?")
                .params(List.of(id))
                .update();

        Assert.state(updated == 1, "Failed to delete run");
    }

    public int count() {return jdbcClient.sql("select count(*) from run").query().listOfRows().size();}

    public void saveAll(List<run> runs){
        runs.stream()
                .forEach(this::create);
    }

    public List<run> findByLocation(location location){
        return jdbcClient.sql("select * from run where location = :location")
                .param("location", location.toString())
                .query(run.class)
                .list();
    }
}
