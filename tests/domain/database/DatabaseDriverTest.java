package domain.database;

import io.swagger.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Karim on 07-12-2016.
 */
public class DatabaseDriverTest {
    private DatabaseDriver database;
    private Post testPost;

    @Before
    public void setUp() throws Exception {
    database = DatabaseDriver.getInstance();
        testPost = new Post();
        testPost.type(PostType.OFFER);
        testPost.description("testDescription");
        testPost.owner("Test");
        testPost.setDate(new Date().toString());
    }

    @After
    public void tearDown() throws Exception {
        List<Post> posts = database.getPosts();
        for(Post p : posts){
            if(p.getId() == testPost.getId()){
                database.deletePost(testPost);
            }
        }

        database = null;
        testPost = null;

    }

    @Test
    public void getLoginTest() throws Exception {
        User k = new User().username("k").rights(User.RightsEnum.ADMIN);
        assertEquals(k, database.getLogin("k", "1"));
    }

    @Test
    public void wrongUser() throws Exception {
        assertNull(database.getLogin("w","1"));
    }

    @Test
    public void getSuppliersTest() throws Exception {
        List<Page> p = new ArrayList<>();
        if(database.getSuppliers().isEmpty()){
            fail();
        } else  {
            assertEquals(p.getClass(),database.getSuppliers().getClass());
        }
    }

    @Test
    public void getProducts() throws Exception {
        List<Product> p = new ArrayList<>();
        if(database.getProducts("BobSagat").isEmpty()){
            fail();
        } else  {
            assertEquals(p.getClass(),database.getSuppliers().getClass());
        }
    }

    @Test
    public void getPosts() throws Exception {
        List<Post> p = new ArrayList<>();
        if(database.getPosts().isEmpty()){
            fail();
        } else  {
            assertEquals(p.getClass(),database.getSuppliers().getClass());
        }
    }

    @Test
    public void addPost() throws Exception {
        //TODO lave den...
    }

    @Test
    public void deletePost() throws Exception { //TODO check at post bliver oprettet.
        List<Post> posts;
        testPost.id(database.addPost("Test",testPost));
        database.deletePost(testPost);
        posts = database.getPosts();
        for(Post p: posts){
            if(p.equals(testPost)){
                fail("The post was not deleted");
            }
        }
    }

    @Test
    public void deleteProduct() throws Exception {
     //TODO
    }

    @Test
    public void addProduct() throws Exception {
    //TODO
    }

    @Test
    public void getPDFFilePath() throws Exception {
    //TODO
    }

}