package ir.xdevelop.election_manager.controller;

import ir.xdevelop.election_manager.model.Election;
import ir.xdevelop.election_manager.repository.ElectionRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@RestController
public class Controller {

    JSONObject message = new JSONObject();

    @Autowired
    ElectionRepository repository;

    @PostMapping(value = "/elections/save")
    public String createElection(@RequestBody Election election, HttpServletResponse response){
        if(repository.existsElectionByname(election.getname())){
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            message.put("message","exist");
        }else {
            repository.save(election);
            message.put("message","successful");
            response.setStatus(HttpServletResponse.SC_OK);
        }
        return message.toString();
    }

    @PutMapping(value = "/elections/update")
    public String EditElection(@RequestBody Election election, HttpServletResponse response){
        if(repository.existsById(election.getId())) {
            Election e = repository.getOne(election.getId());
            e.getDataFrom(election);
            repository.save(e);
            message.put("message","successful");
            response.setStatus(HttpServletResponse.SC_OK);
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            message.put("message","notFound");
        }
        return message.toString();
    }

    @GetMapping(value = "/elections/{electionId}/remove")
    public String RemoveElection(@PathVariable int electionId, HttpServletResponse response){
        if(repository.existsById(electionId)){
            Election election = repository.getOne(electionId);
            if(election.started()) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                message.put("message","forbidden, Election is started.");
            }else {
                repository.delete(election);
                message.put("message","successful");
                response.setStatus(HttpServletResponse.SC_OK);
            }
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            message.put("message","notFound");
        }
        return message.toString();
    }

    @GetMapping(value = "/elections/votes/increment")
    public String IncrementNumberOfVotes(HttpServletResponse response){ //(@RequestParam int electionId,HttpServletResponse response){
//        if(repository.existsById(electionId)){
//            Election e = repository.getOne(electionId);
//            e.IncremenetNumberOfVotes();
//            message.put("message","successful");
//            response.setStatus(HttpServletResponse.SC_OK);
//            repository.save(e);
//        }else {
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            message.put("message","notFound");
//        }

        /*!!! because the provided electionPortal not send electionID, this api every time return successful message and response 200 !!!*/
        message.put("message","successful");
        response.setStatus(HttpServletResponse.SC_OK);
        return message.toString();
    }

    @PutMapping(value = "/elections/{electionId}/votes/increment")
    public String IncrementNumberOfVotes_correct(@PathVariable int electionId,HttpServletResponse response){
        if(repository.existsById(electionId)){
            Election e = repository.getOne(electionId);
            e.IncremenetNumberOfVotes();
            message.put("message","successful");
            response.setStatus(HttpServletResponse.SC_OK);
            repository.save(e);
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            message.put("message","notFound");
        }
        return message.toString();
    }

    @GetMapping(value = "/elections/{electionId}/choices")
    public String getListOfChoices(@PathVariable int electionId, HttpServletResponse response){
        if(repository.existsById(electionId)) {
            response.setStatus(HttpServletResponse.SC_OK);
            return repository.getOne(electionId).getListOfChoices().toString();
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    @GetMapping(value = "/elections/all")
    public String getAllElections(){
        Election e = new Election();
        JSONObject response = new JSONObject();
        response.put("data",e.parseElectionListToJsonArray(repository.findAllByOrderByIdAsc()));
        response.put("message","successful");
        return response.toString();
    }

    @GetMapping(value = "/elections/exists")
    public String electionExists(@RequestParam int electionId, HttpServletResponse response){
        if(repository.existsById(electionId)){
            response.setStatus(HttpServletResponse.SC_OK);
            message.put("message","successful");
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            message.put("message","notFound");
        }
        return message.toString();
    }

    @GetMapping(value = "/elections/{electionId}/get")
    public String getElectionDetails(@PathVariable int electionId, HttpServletResponse response){
        JSONObject ans = new JSONObject();
        if(repository.existsById(electionId)) {
            response.setStatus(HttpServletResponse.SC_OK);
            ans.put("data",repository.getOne(electionId).toMap());
            ans.put("message","successful");
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            ans.put("data","election not found!");
            ans.put("message","notFound");
        }
        return ans.toString();
    }

    @PostMapping(value = "elections/{electionId}/choices/save")
    public String createAchoiceForAnElection(@PathVariable int electionId,@RequestBody String rb, HttpServletResponse response){
        if(repository.existsById(electionId)){
            JSONObject jsonObject = new JSONObject(rb);
            Election e = repository.getOne(electionId);
            e.getListOfChoices().put(e.getListOfChoices().size()+1,jsonObject.get("choice").toString()); //repository.rowConstructor()+1
            repository.save(e);
            response.setStatus(HttpServletResponse.SC_OK);
            message.put("message","successful");
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            message.put("message","notFound");
        }
        return message.toString();
    }

    @PutMapping(value = "/elections/{electionId}/choices/{choiceId}/update")
    public String editingAchoiceOfAnElection(@PathVariable int electionId,@PathVariable int choiceId,@RequestBody String rb, HttpServletResponse response){
        if(repository.existsById(electionId)){
            JSONObject jsonObject = new JSONObject(rb);
            Election e = repository.getOne(electionId);
            e.getListOfChoices().replace(choiceId,jsonObject.get("choice").toString());
            repository.save(e);
            response.setStatus(HttpServletResponse.SC_OK);
            message.put("message","successful");
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            message.put("message","notFound");
        }
        return message.toString();
    }

    @GetMapping(value = "/elections/{electionId}/choices/{choiceId}/remove")
    public String removingAchoiceFromAnElection(@PathVariable int electionId,@PathVariable int choiceId, HttpServletResponse response){
        if(repository.existsById(electionId)){
            Election e = repository.getOne(electionId);
            e.getListOfChoices().remove(choiceId);
            repository.save(e);
            response.setStatus(HttpServletResponse.SC_OK);
            message.put("message","successful");
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            message.put("message","notFound");
        }
        return message.toString();
    }

    @GetMapping(value = "/elections/{electionId}/choices/all")
    public String getElectionChoices(@PathVariable int electionId, HttpServletResponse response){
        JSONObject ans = new JSONObject();
        if(repository.existsById(electionId)) {
            ans.put("data",repository.getOne(electionId).getArrayListOfChoices());
            response.setStatus(HttpServletResponse.SC_OK);
            ans.put("message","successful");
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            ans.put("data","election not found!");
            ans.put("message","notFound");
        }
        return ans.toString();
    }

    @GetMapping(value = "/elections/{electionId}/choices/{choiceId}/get")
    public String getElectionOneChoices(@PathVariable int electionId,@PathVariable int choiceId, HttpServletResponse response){
        JSONObject ans = new JSONObject();
        if(repository.existsById(electionId)) {
            ans.put("data",repository.getOne(electionId).getChoiceByid(choiceId));
            response.setStatus(HttpServletResponse.SC_OK);
            ans.put("message","successful");
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            ans.put("data","election not found!");
            ans.put("message","notFound");
        }
        return ans.toString();
    }

    @GetMapping(value = "/elections/{electionId}/votes")
    public String getNumberOfVotes(@PathVariable int electionId, HttpServletResponse response){
        JSONObject ans = new JSONObject();
        if(repository.existsById(electionId)){
            Election e = repository.getOne(electionId);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("numberOfVotes",e.getNumberOfVotes());
            ans.put("data",jsonObject);
            ans.put("message","successful");
            response.setStatus(HttpServletResponse.SC_OK);
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            ans.put("data","election not found!");
            ans.put("message","notFound");
        }
        return ans.toString();
    }

    @GetMapping(value = "/heartbeat")
    public void heartbeat(HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_OK);
    }

}