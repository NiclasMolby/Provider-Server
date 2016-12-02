package domain.bulletinboard;

import common.Logger;
import domain.database.DatabaseDriver;

import java.util.List;
import io.swagger.model.*;

import java.util.Date;
import java.util.stream.Collectors;

public class Bulletinboard {

    public Bulletinboard() { }

    public Post createPost(String owner, String title, String description, PostType type) {
        Post post = new Post().owner(owner).date(new Date().toString()).title(title).description(description).type(type);
        post.setId(DatabaseDriver.getInstance().addPost(owner, post));

        Logger.get().log(Logger.LogType.INFO, post.getOwner() + " har oprettet en ny opslag med titlen: " + post.getTitle());
        return post;
    }

    public void deletePost(Post post) {
        DatabaseDriver.getInstance().deletePost(post);
        Logger.get().log(Logger.LogType.INFO, post.getOwner() + " har slettet opslaget med titlen: " + post.getTitle());
    }

    public void editPost(Post post, String newDescription, String newTitle) {
        post.setDescription(newDescription);
        post.setTitle(newTitle);
        DatabaseDriver.getInstance().updatePost(post.getOwner(), post); //TODO: opdater posten i databasen
        Logger.get().log(Logger.LogType.INFO, post.getOwner() + " har redigeret opslaget med titlen: " + post.getTitle());
    }

    public List<Post> getAllPosts() {
        return DatabaseDriver.getInstance().getPosts();
    }
}
