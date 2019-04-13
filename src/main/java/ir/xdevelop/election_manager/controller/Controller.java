package ir.xdevelop.election_manager.controller;

import com.google.gson.JsonObject;
import ir.xdevelop.election_manager.model.Election;
import ir.xdevelop.election_manager.repository.ElectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
public class Controller {

    @Autowired
    ElectionRepository repository;

    @PostMapping(value = "/elections")
    public void createElection(@RequestBody Election election, HttpServletResponse response){
        if(repository.existsElectionByTitle(election.getTitle())){
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }else {
            repository.save(election);
        }
    }

    @PutMapping(value = "/elections/{electionId}")
    public void EditElection(@RequestBody Election election, HttpServletResponse response){
        if(repository.existsById(election.getId())) {
            Election e = repository.getOne(election.getId());
            e.getDataFrom(election);
            repository.save(e);
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/elections/{electionId}")
    public void RemoveElection(@PathVariable int electionId, HttpServletResponse response){
        if(repository.existsById(electionId)){
            Election election = repository.getOne(electionId);
            if(election.started()) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }else {
                repository.delete(election);
            }
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @PutMapping(value = "/elections/{electionId}/votes/increment")
    public void IncrementNumberOfVotes(@PathVariable int electionId, HttpServletResponse response){
        if(repository.existsById(electionId)){
            Election e = repository.getOne(electionId);
            e.IncremenetNumberOfVotes();
            repository.save(e);
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @GetMapping(value = "/elections/{electionId}/choices")
    public List getListOfChoices(@PathVariable int electionId, HttpServletResponse response){
        if(repository.existsById(electionId)) {
            return repository.getOne(electionId).getListOfChoices();
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    @GetMapping(value = "/elections")
    public List getAllElections(){
        return repository.findAllByOrderByIdAsc();
    }

    @GetMapping(value = "/elections/{electionId}/exists")
    public void electionExists(@PathVariable int electionId, HttpServletResponse response){
        if(repository.existsById(electionId)){
            response.setStatus(HttpServletResponse.SC_FOUND);
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @GetMapping(value = "/elections/{electionId}")
    public String getElectionDetails(@PathVariable int electionId, HttpServletResponse response){
        if(repository.existsById(electionId)) {
            return repository.getOne(electionId).toString();
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    @GetMapping(value = "/elections/{electionId}/votes")
    public String getNumberOfVotes(@PathVariable int electionId, HttpServletResponse response){
        if(repository.existsById(electionId)){
            Election e = repository.getOne(electionId);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("numberOfVotes",e.getNumberOfVotes());
            return jsonObject.toString();
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

}
