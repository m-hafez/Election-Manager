package ir.xdevelop.election_manager.model;

import com.google.gson.JsonObject;
import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
public class Election {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String title;

    @Column
    private String startTime;

    @Column
    private String endTime;

    @Column
    private String listOfChoices;

    @Column
    private int numberOfVotes;

    public Election() { }

    public Election(String title, String startTime, String endTime, String listOfChoices, int numberOfVotes) {
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
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
        this.listOfChoices =e.getListOfChoices().toString(); //e.getListOfChoices();
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
        electionObject.addProperty("startTime",this.startTime);
        electionObject.addProperty("endTime",this.endTime);
        electionObject.addProperty("listOfChoices",this.getListOfChoices().toString());
        electionObject.addProperty("numberOfVotes",this.numberOfVotes);
        return electionObject.toString();
    }

    public List getListOfChoices(){
        List<String> list =new ArrayList<>();
        for (String str : this.listOfChoices.split(",")) {
            list.add(str);
        }
        return list;
    }

    public boolean started(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cal.setLenient(false);
        Date date = null;
        try {
            date = dateFormat.parse(this.startTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(cal.getTime().before(date)){
            return false;
        }else {
            return true;
        }
    }

}
