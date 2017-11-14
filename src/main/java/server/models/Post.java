package server.models;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by Filip on 10-10-2017.
 */
public class Post {

    private int id;
    private Timestamp created;
    private User owner;
    private String content;
    private Event event;
    private Post parent;

    private ArrayList<Post> comments;


    //This constructor should be used when creating a new post in the database

    public Post(int id, Timestamp created, User owner, String content, Event event, Post parent) {
        this.id = id;
        this.created = created;
        this.owner = owner;
        this.content = content;
        this.event = event;
        this.parent = parent;

        this.comments = new ArrayList<Post>();
    }


    //Use this constructor for fetching ONLY the id of the post

    public Post(int owner, String content, int event, int parent) {
        this.owner = new User(owner);
        this.content = content;
        this.event = new Event(event);
        this.parent = new Post(parent);
        this.comments = new ArrayList<Post>();
    }

    //Use this constructor for adding comments to the Post ArrayList.

    public Post(int id) {
        this.id = id;
        this.comments = new ArrayList<Post>();
    }


    //Getters and setters for post variables.

    public void setId(int id) {this.id = id; }

    public int getId() {
        return id;
    }

    public Timestamp getCreated() {return created;}

    public User getOwner() {
        return owner;
    }

    public String getContent() {
        return content;
    }

    public Event getEvent() {
        return event;
    }

    public Post getParent() {
        return parent;
    }

    public ArrayList<Post> getComments() {
        return comments;
    }
}
