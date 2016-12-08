package domain.bulletinboard;

import io.swagger.model.Post;
import io.swagger.model.PostType;
import java.util.List;

public interface IBulletinboard {
    
    Post createPost(String owner, String title, String description, PostType type);
    
    void deletePost(Post post);
    
    void editPost(Post post, String newDescription, String newTitle);
    
    List<Post> getAllPosts();
}
