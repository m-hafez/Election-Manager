package ir.xdevelop.electronic_voting.controller;


import ir.xdevelop.electronic_voting.model.Election;
import ir.xdevelop.electronic_voting.repository.ElectionRepository;
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

}

