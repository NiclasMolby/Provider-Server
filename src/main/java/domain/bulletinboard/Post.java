package domain.bulletinboard;

import domain.util.PostTypes;
import java.util.Date;

public class Post {

    private PostTypes type;
    private int id;
    private String owner;
    private Date date;
    private String description;
    private String title;

    public Post(String owner, String title, String description, PostTypes type, Date date, int id) {
        this.description = description;
        this.owner = owner;
        this.type = type;
        this.title = title;
        this.date = date;
        this.id = id;
    }

    public Post(String owner, String title, String description, PostTypes type) {
        this(owner, title, description, type, new Date(), (int) (Math.random() * Integer.MAX_VALUE));
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public PostTypes getType() {
        return type;
    }
}
