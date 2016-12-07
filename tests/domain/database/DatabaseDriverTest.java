package domain.database;

import io.swagger.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Karim on 07-12-2016.
 */
public class DatabaseDriverTest {
    DatabaseDriver database;
    @Before
    public void setUp() throws Exception {
    database = DatabaseDriver.getInstance();
    }

    @After
    public void tearDown() throws Exception {
    database = null;
    }

    @Test
    public void getInstanceUnitTest() throws Exception {
    assertEquals(database, DatabaseDriver.getInstance());
    }

    @Test
    public void getLoginUnitTest() throws Exception {
        User k = new User().username("k").rights(User.RightsEnum.ADMIN);
        assertEquals(k.getUsername(), database.getLogin("k","1").getUsername());
        assertEquals(k.getRights(),database.getLogin("k","1").getRights());
    }

    @Test
    public void getSuppliers() throws Exception {

        fail();
    }

    @Test
    public void getProducts() throws Exception {
fail();
    }

    @Test
    public void getPosts() throws Exception {
fail();
    }

    @Test
    public void addNoteToSupplier() throws Exception {
fail();
    }

    @Test
    public void editNoteOnSupplier() throws Exception {
fail();
    }

    @Test
    public void updatePost() throws Exception {
fail();
    }

    @Test
    public void addPost() throws Exception {
fail();
    }

    @Test
    public void deletePost() throws Exception {
fail();
    }

    @Test
    public void deleteProduct() throws Exception {
fail();
    }

    @Test
    public void updatePage() throws Exception {
fail();
    }

    @Test
    public void updateProduct() throws Exception {
fail();
    }

    @Test
    public void addProduct() throws Exception {
fail();
    }

    @Test
    public void getPDFFilePath() throws Exception {
fail();
    }

    @Test
    public void addProductToPage() throws Exception {
fail();
    }

}