package ir.xdevelop.election_manager.model;


import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.*;

@Entity
public class Election {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @Column
    private Date startTime;

    @Column
    private Date endTime;

    @ElementCollection
    private Map<Integer,String> listOfChoices=new LinkedHashMap<>();

    @Column
    private int numberOfVotes;

    public Election() { }

    public Election(String name, Date startTime, Date endTime, Map<Integer,String> listOfChoices, int numberOfVotes) {
        this.name = name;
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

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
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

    public Map<Integer,String> getListOfChoices() {
        return listOfChoices;
    }

    public void setListOfChoices(Map<Integer,String> listOfChoices) {
        this.listOfChoices = listOfChoices;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    public void getDataFrom(Election e){
        this.name = e.getname();
        this.startTime = e.getStartTime();
        this.endTime = e.getEndTime();
        this.listOfChoices = e.getListOfChoices();
        this.numberOfVotes = e.getNumberOfVotes();
    }

    public void IncremenetNumberOfVotes(){
        this.numberOfVotes ++;
    }

    @Override
    public String toString() {
        JSONObject electionObject = new JSONObject();
        electionObject.put("id",this.id);
        electionObject.put("name",this.name);
        electionObject.put("startTime",this.startTime.toString());
        electionObject.put("endTime",this.endTime.toString());
        electionObject.put("listOfChoices", this.getListOfChoices().toString());
        electionObject.put("numberOfVotes",this.numberOfVotes);
        return electionObject.toString();
    }

    public Map toMap() {
        Map m = new LinkedHashMap(4);
        m.put("id", this.id);
        m.put("name", this.name);
        m.put("startTime", this.startTime.toString());
        m.put("endTime", this.endTime.toString());
        return m;
    }

    public JSONArray getArrayListOfChoices() {
        JSONArray loch = new JSONArray();
        for (Integer i:listOfChoices.keySet()) {
            JSONObject jobj = new JSONObject();
            jobj.put("id",i);
            jobj.put("choice",listOfChoices.get(i));
            loch.put(jobj);
        }
        return loch;
    }

    public JSONArray parseElectionListToJsonArray(List<Election> list){
        JSONArray jsonElements = new JSONArray();
        for (Election e:list){
            jsonElements.put(e.toMap());
        }
        return jsonElements;
    }


    public JSONObject getChoiceByid(int id) {
        JSONObject jobj = new JSONObject();
        jobj.put("id",id);
        jobj.put("choice",listOfChoices.get(id));
        return jobj;
    }

    public boolean started(){
        Calendar cal = Calendar.getInstance();
        cal.setLenient(false);
        return !cal.getTime().before(this.startTime);
    }

}
