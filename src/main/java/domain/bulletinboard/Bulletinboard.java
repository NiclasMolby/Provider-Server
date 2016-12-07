package domain.bulletinboard;

import common.Logger;
import domain.database.DatabaseDriver;

import java.util.List;
import io.swagger.model.*;

import java.util.Date;

public class Bulletinboard {

    public Bulletinboard() {
    }

    /**
     * Creates a new post and adds it database and logs it.
     * @param owner The name of the post owner.
     * @param title The title of the post.
     * @param description The description of the post.
     * @param type Enum, type of post.
     * @return returns the new post.
     */
    public Post createPost(String owner, String title, String description, PostType type) {
        Post post = new Post().owner(owner).date(new Date().toString()).title(title).description(description).type(type);
        post.setId(DatabaseDriver.getInstance().addPost(owner, post));
        Logger.get().log(Logger.LogType.INFO, post.getOwner() + " har oprettet en ny opslag med titlen: " + post.getTitle());
        return post;
    }

    /**
     * Deletes a post in the database and logs it.
     * @param post The post that will be deleted.
     */
    public void deletePost(Post post) {
        DatabaseDriver.getInstance().deletePost(post);
        Logger.get().log(Logger.LogType.INFO, post.getOwner() + " har slettet opslaget med titlen: " + post.getTitle());
    }

    /**
     * Edits a post and saves it in the database and logs it.
     * @param post The post that will be updated.
     * @param newDescription The new description.
     * @param newTitle The new title.
     */
    public void editPost(Post post, String newDescription, String newTitle) {
        post.setDescription(newDescription);
        post.setTitle(newTitle);
        DatabaseDriver.getInstance().updatePost(post.getOwner(), post);
        Logger.get().log(Logger.LogType.INFO, post.getOwner() + " har redigeret opslaget med titlen: " + post.getTitle());
    }

    /**
     *Gets all the posts.
     * @return a list of products.
     */
    public List<Post> getAllPosts() {
        return DatabaseDriver.getInstance().getPosts();
    }
}
