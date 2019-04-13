package ir.xdevelop.election_manager.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.persistence.*;
import java.util.*;

@Entity
public class Election {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String title;

    @Column
    private Date startTime;

    @Column
    private Date endTime;

    @Column
    private String listOfChoices;

    @Column
    private int numberOfVotes;

    public Election() { }

    public Election(String title, Date startTime, Date endTime, String listOfChoices, int numberOfVotes) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.listOfChoices = listOfChoices;
        this.numberOfVotes = numberOfVotes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

//    public String getListOfChoices() {
//        return listOfChoices;
//    }

    public void setListOfChoices(String listOfChoices) {
        this.listOfChoices = listOfChoices;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    public void getDataFrom(Election e){
        this.title = e.getTitle();
        this.startTime = e.getStartTime();
        this.endTime = e.getEndTime();
        this.listOfChoices = e.getListOfChoices().toString(); //e.getListOfChoices();
        this.numberOfVotes = e.getNumberOfVotes();
    }

    public void IncremenetNumberOfVotes(){
        this.numberOfVotes ++;
    }

    @Override
    public String toString() {
        JsonObject electionObject = new JsonObject();
        electionObject.addProperty("id",this.id);
        electionObject.addProperty("title",this.title);
        electionObject.addProperty("startTime",this.startTime.toString());
        electionObject.addProperty("endTime",this.endTime.toString());
        electionObject.add("listOfChoices", this.parseListToJsonArray(this.getListOfChoices()));
        electionObject.addProperty("numberOfVotes",this.numberOfVotes);
        return electionObject.toString();
    }

    private JsonArray parseListToJsonArray(List<String> list){
        JsonArray jsonElements = new JsonArray();
        for (String str:list){
            jsonElements.add(str);
        }
        return jsonElements;
    }

    public List<String> getListOfChoices(){
        List<String> list =new ArrayList<>();
        Collections.addAll(list, this.listOfChoices.split(","));
        return list;
    }

    public boolean started(){
        Calendar cal = Calendar.getInstance();
        cal.setLenient(false);
        return !cal.getTime().before(this.startTime);
    }

}
