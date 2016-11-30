package domain.bulletinboard;

import domain.controller.Logger;
import domain.database.DatabaseDriver;

import java.util.ArrayList;
import java.util.List;
import io.swagger.model.*;
import sun.rmi.runtime.Log;

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

        Logger.get().log(Logger.LogType.INFO, post.getOwner() + " har oprettet en ny post med titlen: " + post.getTitle());
        return post;
    }

    public void deletePost(Post post) {
        DatabaseDriver.getInstance().deletePost(post);
        posts.remove(post);
        Logger.get().log(Logger.LogType.INFO, "Posten: " + post.getTitle() + " er blevet slettet");
    }

    public void editPost(Post post, String newDescription, String newTitle) {
        post.setDescription(newDescription);
        post.setTitle(newTitle);
        DatabaseDriver.getInstance().updatePost(post.getOwner(), post); //TODO: opdater posten i databasen
        Logger.get().log(Logger.LogType.INFO, "Posten: " + post.getTitle() + " er blevet redigeret");
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
