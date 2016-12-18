package domain.database;

import database.DatabaseFacade;
import database.IDatabaseFacade;
import domain.security.Hash;
import io.swagger.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static org.junit.Assert.*;

public class DatabaseFacadeTest {
    private IDatabaseFacade database;
    private Post testPost;
    private Product testProduct;
    private Note testNote;

    @Before
    public void setUp() throws Exception {
        database = DatabaseFacade.getInstance();
        testPost = new Post().type(PostType.OFFER).title("test").description("testDescription").owner("Test").date(new Date().toString());
        testProduct = new Product().productName("Test").chemicalName("Test").deliveryTime("Test").description("Test").producer("BobSagat").price(4.20).molWeight(4.20).packaging("test");
        testNote = new Note().creationDate(new java.sql.Date(420)).editor("Test supplier").text("Test");
    }

    @After
    public void tearDown() throws Exception {
        if (database.getPosts().contains(testPost)) {
            database.deletePost(testPost);
        }
        if (database.getProducts("Bobsagat").contains(testProduct)) {
            database.deleteProduct(testProduct);
        }
        database = null;
        testPost = null;
        testProduct = null;
    }

    @Test
    public void getLoginTest() throws Exception {
        User k = new User().username("k").rights(User.RightsEnum.ADMIN);
        Hash hash = new Hash();
        String password = "1";
        byte[] salt = database.getSalt("k");
        password = hash.getHashedPassword(password, salt);
        assertEquals(k, database.getLogin("k", password));
    }

    @Test
    public void wrongUserTest() throws Exception {
        assertNull(database.getLogin("w", "1"));
    }

    @Test
    public void getSuppliersTest() throws Exception {
        List<Page> p = new ArrayList<>();
        if (database.getSuppliers().isEmpty()) {
            fail();
        } else {
            assertEquals(p.getClass(), database.getSuppliers().getClass());
        }
    }

    @Test
    public void addNoteToSupplierTest() throws Exception {
        String supplier = "Test supplier";
        Page testPage = null;
        for (Page p : database.getSuppliers()) {
            if (p.getOwner().equalsIgnoreCase(supplier)) {
                testPage = p;
            }
        }
        if (testPage.getNote() == null) {
            database.addNoteToSupplier(supplier, testNote);
            testPage = null;
            for (Page p : database.getSuppliers()) {
                if (p.getOwner().equalsIgnoreCase(supplier)) {
                    testPage = p;
                }
            }
            assertNotNull(testPage.getNote());
        } else {
            database.editNoteOnSupplier(supplier, testNote);
            testPage = null;
            for (Page p : database.getSuppliers()) {
                if (p.getOwner().equalsIgnoreCase(supplier)) {
                    testPage = p;
                }
            }
            assertEquals(testNote.getText(), testPage.getNote().getText());
        }
    }


    @Test
    public void getProductsTest() throws Exception {
        List<Product> p = new ArrayList<>();
        if (database.getProducts("Test Supplier").isEmpty()) {
            fail("Der er ikke nogen produkter i databasen");
        } else {
            assertEquals(p.getClass(), database.getSuppliers().getClass());
        }
    }

    @Test
    public void getPostsTest() throws Exception {
        List<Post> p = new ArrayList<>();
        if (database.getPosts().isEmpty()) {
            fail("Der er ikke nogen post i databasen");
        } else {
            assertEquals(p.getClass(), database.getSuppliers().getClass());
        }
    }

    @Test
    public void addPostTest() throws Exception {

        if (database.getPosts().contains(testPost)) {
            fail("There is already a test post in the database");
        }
        testPost.id(database.addPost("Test", testPost));
        if (!database.getPosts().contains(testPost)) {
            fail("The post was not added to the database!");
        }
        database.deletePost(testPost);
    }

    @Test
    public void deletePostTest() throws Exception {
        testPost.id(database.addPost("Test", testPost));
        if (!database.getPosts().contains(testPost)) {
            fail("The post was never created in the database");
        }
        database.deletePost(testPost);
        for (Post p : database.getPosts()) {
            if (p.equals(testPost)) {
                fail("The post was not deleted");
            }
        }
    }

    @Test
    public void addProduct() throws Exception {
        testProduct.id(database.addProduct(testProduct));
        database.addProductToPage(testProduct);
        if (!database.getProducts("BobSagat").contains(testProduct)) {
            fail("The post was not added to the database!");
        }
        database.deleteProduct(testProduct);
    }

    @Test
    public void deleteProduct() throws Exception {
        if (database.getProducts("BobSagat").contains(testProduct)) {
            fail("There is already a test product in the database");
        }
        testProduct.id(database.addProduct(testProduct));
        database.addProductToPage(testProduct);
        if (!database.getProducts("BobSagat").contains(testProduct)) {
            fail("The post was not added to the database!");
        }
        database.deleteProduct(testProduct);
        if (database.getProducts("BobSagat").contains(testProduct)) {
            fail("The post was deleted from the database!");
        }
    }

    @Test
    public void getPDFFilePath() throws Exception {
        database.getPDFFilePath(1);
        assertEquals("".getClass(), database.getPDFFilePath(1).getClass());
    }

    @Test
    public void isFileOnPDFpath() throws Exception {
        database.getPDFFilePath(1);
        assertTrue(new File(database.getPDFFilePath(1)).exists());
    }
}
