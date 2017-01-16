package domain.bulletinboard;

import common.*;
import database.DatabaseFacade;

import java.util.List;

import database.IDatabaseFacade;
import domain.controller.Controller;
import io.swagger.model.*;

import java.util.Date;

public class Bulletinboard implements IBulletinboard {

    private IDatabaseFacade database;
    private static IBulletinboard instance;
    private Object updateLock = Controller.updateLock;

    private Bulletinboard() {
        database = DatabaseFacade.getInstance();
    }

    public static IBulletinboard getBulletinboard() {
        if(instance == null) {
            instance = new Bulletinboard();
        }
        return instance;
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
        synchronized (updateLock) {
            Post post = new Post().owner(owner).date(new Date().toString()).title(title).description(description).type(type);
            post.setId(database.addPost(owner, post));
            Logger.log(LogType.INFO, post.getOwner() + " har oprettet et ny opslag med titlen " + post.getTitle());
            updateLock.notifyAll();
            return post;
        }
    }

    /**
     * Deletes a post in the database and logs it.
     * @param post The post that will be deleted.
     */
    public void deletePost(Post post) {
        synchronized (updateLock) {
            database.deletePost(post);
            Logger.log(LogType.INFO, post.getOwner() + " har slettet opslaget med titlen " + post.getTitle());
            updateLock.notifyAll();
        }
    }

    /**
     * Edits a post and saves it in the database and logs it.
     * @param post The post that will be updated.
     * @param newDescription The new description.
     * @param newTitle The new title.
     */
    public void editPost(Post post, String newDescription, String newTitle) {
        synchronized (updateLock) {
            post.setDescription(newDescription);
            post.setTitle(newTitle);
            database.updatePost(post);
            Logger.log(LogType.INFO, post.getOwner() + " har ændret opslaget med titlen " + post.getTitle());
            updateLock.notifyAll();
        }
    }

    /**
     *Gets all the posts.
     * @return a list of products.
     */
    public synchronized List<Post> getAllPosts() {
        return database.getPosts();
    }
}
