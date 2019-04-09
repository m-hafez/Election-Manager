package ir.xdevelop.election_manager.controller;


import ir.xdevelop.election_manager.model.Election;
import ir.xdevelop.election_manager.repository.ElectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class controller {

    @Autowired
    ElectionRepository repository;

    @RequestMapping(value = "/election/create")
    public void createElection(@RequestBody Election election, HttpServletResponse response){
        if(repository.existsElectionByTitle(election.getTitle())){
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }else {
            repository.save(election);
        }
    }

    @RequestMapping(value = "/election/edit")
    public void EditElection(@RequestBody Election election, HttpServletResponse response){
        if(repository.existsElectionById(election.getId())) {
            Election e = repository.getOne(election.getId());
            e.getDataFrom(election);
            repository.save(e);
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @RequestMapping(value = "/election/remove")
    public void RemoveElection(@RequestParam int electionId, HttpServletResponse response){
        if(repository.existsElectionById(electionId)){
            repository.delete(repository.getOne(electionId));
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @RequestMapping(value = "/election/incremenet-number-of-votes")
    public void IncremenetNumberOfVotes(@RequestParam int electionId, HttpServletResponse response){
        if(repository.existsElectionById(electionId)){
            Election e = repository.getOne(electionId);
            e.IncremenetNumberOfVotes();
            repository.save(e);
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

        @RequestMapping(value = "/election/get-list-of-choices")
    public List getListOfChoices(@RequestParam int electionId, HttpServletResponse response){
        if(repository.existsElectionById(electionId)) {
            return repository.getOne(electionId).getArrayListOfChoices();
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    @RequestMapping(value = "/election/get-all")
    public List getAllElections(){
        return repository.findAll();
    }

    @RequestMapping(value = "/election/exists")
    public void electionExists(@RequestParam int electionId, HttpServletResponse response){
        if(repository.existsElectionById(electionId)){
            response.setStatus(HttpServletResponse.SC_FOUND);
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @RequestMapping(value = "/election/get-details")
    public String getElectionDetails(@RequestParam int electionId, HttpServletResponse response){
        if(repository.existsElectionById(electionId)) {
            return repository.getOne(electionId).toString();
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }
    
}

