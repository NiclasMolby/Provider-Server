package domain.bulletinboard;

import domain.database.DatabaseDriver;

import java.util.ArrayList;
import java.util.List;
import io.swagger.model.*;
import java.util.Date;

public class Bulletinboard {

    private List<Post> posts;

    public Bulletinboard() {
        posts = new ArrayList<>();
    }

    public Post createPost(String owner, String title, String description, PostType type) {
        Post post = new Post().owner(owner).date(new Date().toString()).title(title).description(description).type(type);
        post.setId(DatabaseDriver.getInstance().addPost(owner, post));
        posts.add(post);
        return post;
    }

    public void deletePost(Post post) {
        DatabaseDriver.getInstance().deletePost(post);
        posts.remove(post);
    }

    public void editPost(Post post, String newDescription, String newTitle) {
        post.setDescription(newDescription);
        post.setTitle(newTitle);
        DatabaseDriver.getInstance().updatePost(post.getOwner(), post); //TODO: opdater posten i databasen
    }

    public List<Post> getAllPosts() {
        this.posts.clear();
        List<Post> posts = DatabaseDriver.getInstance().getPosts();
        posts.parallelStream().forEach(post -> {
            this.posts.add(post);
        });
        return this.posts;
    }
}
