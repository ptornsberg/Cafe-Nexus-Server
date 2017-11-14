package server.models;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * This class defines the variables of the event.
 *
 * Created by Filip on 10-10-2017.
 */
public class Event {

    private int id;
    private String title;
    private Timestamp created;
    private User owner;
    private Timestamp startDate;
    private Timestamp endDate;
    private String description;
    private ArrayList<User> participants;
    private ArrayList<Post> posts;

    public Event(int id, String title, Timestamp created, User owner, Timestamp startDate, Timestamp endDate, String description) {
        this.id = id;
        this.title = title;
        this.created = created;
        this.owner = owner;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;

        this.participants = new ArrayList<User>();
        this.posts = new ArrayList<Post>();
    }

    //This constructor should be used when creating a new event in the database
    public Event(int owner_id, String title, Timestamp startDate, Timestamp endDate, String description) {
        this.owner = new User(owner_id);
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    // This constructor is used to ONLY get the id of the event
    public Event(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Timestamp getCreated() {
        return created;
    }

    public User getOwner() {
        return owner;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<User> getParticipants() {
        return participants;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }
}


