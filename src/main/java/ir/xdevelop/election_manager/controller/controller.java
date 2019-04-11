package ir.xdevelop.election_manager.controller;

import com.google.gson.JsonObject;
import ir.xdevelop.election_manager.model.Election;
import ir.xdevelop.election_manager.repository.ElectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class controller {

    @Autowired
    ElectionRepository repository;

    @PostMapping(value = "/elections/create")
    public void createElection(@RequestBody Election election, HttpServletResponse response){
        if(repository.existsElectionByTitle(election.getTitle())){
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }else {
            repository.save(election);
        }
    }

    @PutMapping(value = "/elections/edit")
    public void EditElection(@RequestBody Election election, HttpServletResponse response){
        if(repository.existsElectionById(election.getId())) {
            Election e = repository.getOne(election.getId());
            e.getDataFrom(election);
            repository.save(e);
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/elections/{electionId}/remove")
    public void RemoveElection(@PathVariable int electionId, HttpServletResponse response){
        if(repository.existsElectionById(electionId)){
            repository.delete(repository.getOne(electionId));
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @PutMapping(value = "/elections/{electionId}/votes/incremenet")
    public void IncremenetNumberOfVotes(@PathVariable int electionId, HttpServletResponse response){
        if(repository.existsElectionById(electionId)){
            Election e = repository.getOne(electionId);
            e.IncremenetNumberOfVotes();
            repository.save(e);
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @GetMapping(value = "/elections/{electionId}/choices")
    public List getListOfChoices(@PathVariable int electionId, HttpServletResponse response){
        if(repository.existsElectionById(electionId)) {
            return repository.getOne(electionId).getArrayListOfChoices();
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    @GetMapping(value = "/elections")
    public List getAllElections(){
        return repository.findAll();
    }

    @GetMapping(value = "/elections/{electionId}/exists")
    public void electionExists(@PathVariable int electionId, HttpServletResponse response){
        if(repository.existsElectionById(electionId)){
            response.setStatus(HttpServletResponse.SC_FOUND);
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @GetMapping(value = "/elections/{electionId}")
    public String getElectionDetails(@PathVariable int electionId, HttpServletResponse response){
        if(repository.existsElectionById(electionId)) {
            return repository.getOne(electionId).toString();
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    @GetMapping(value = "/elections/{electionId}/votes")
    public String getNumberOfVotes(@PathVariable int electionId, HttpServletResponse response){
        if(repository.existsElectionById(electionId)){
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
