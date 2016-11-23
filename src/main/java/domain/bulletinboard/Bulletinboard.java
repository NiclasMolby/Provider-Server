package domain.bulletinboard;

import domain.database.DatabaseDriver;

import java.time.LocalDate;
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
        Post post = new Post().owner(owner).date(new Date()).title(title).description(description).type(type);
        post.setId(DatabaseDriver.getInstance().addPost(owner, post));
        posts.add(post);
        return post;
    }

    public void deletePost(Post post) {
        //Database.instance.DeletePost(post); // TODO: slet post fra databasen
        posts.remove(post);
    }

    public void editPost(Post post, String newDescription, String newTitle) {
        Post postFound = posts.stream().filter(p -> p == post).findFirst().get();
        postFound.setDescription(newDescription);
        postFound.setTitle(newTitle);
        DatabaseDriver.getInstance().updatePost(post.getOwner(), post); //TODO: opdater posten i databasen
    }

    private List<Post> getPosts(PostType type) {
        //return posts.stream().filter(p -> p.getType() == type).collect(Collectors.toList());
        return null;
    }

    public List<Post> getAllPosts() {
        this.posts.clear();
        List<Post> posts = DatabaseDriver.getInstance().getPosts();
        posts.parallelStream().forEach(post -> {
            System.out.println("[INFO] #Post " + post.getTitle() + ":\n" + post.getDescription());
            this.posts.add(post);
        });
        return this.posts;
    }

    /*public List<Post> viewWarningPosts() {
        return getPosts(PostTypes.Warning);
    }

    public List<Post> viewRequestPosts() {
        return getPosts(PostTypes.Request);
    }

    public List<Post> viewOfferPosts() {
        return getPosts(PostTypes.Offer);
    }*/

}
