package domain.bulletinboard;

import domain.util.PostTypes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bulletinboard {

    private List<Post> posts;

    public Bulletinboard() {
        posts = new ArrayList<>();
    }

    public void createPost(String owner, String title, String description, PostTypes type) {
        Post post = new Post(owner, title, description, type);
        //post.setId(Database.instance.AddPost(owner, post)); //TODO: hent id fra databasen når en post bliver oprettet
        posts.add(post);
    }

    public void deletePost(Post post) {
        //Database.instance.DeletePost(post); // TODO: slet post fra databasen
        posts.remove(post);
    }

    public void editPost(Post post, String newDescription, String newTitle) {
        Post postFound = posts.stream().filter(p -> p == post).findFirst().get();
        postFound.setDescription(newDescription);
        postFound.setTitle(newTitle);
        DatabaseDriver.instance.UpdatePost(post.owner, post); //TODO: opdater posten i databasen
    }

    private List<Post> getPosts(PostTypes type) {
        return posts.stream().filter(p -> p.getType() == type).collect(Collectors.toList());
    }

    public List<Post> viewAllPosts() {
        return posts;
    }

    public List<Post> viewWarningPosts() {
        return getPosts(PostTypes.Warning);
    }

    public List<Post> viewRequestPosts() {
        return getPosts(PostTypes.Request);
    }

    public List<Post> viewOfferPosts() {
        return getPosts(PostTypes.Offer);
    }

}
