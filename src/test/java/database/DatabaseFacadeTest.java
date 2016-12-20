package database;

import database.DatabaseFacade;
import domain.security.Hash;
import io.swagger.model.*;
import org.junit.*;
import java.util.Date;

import static org.junit.Assert.*;

public class DatabaseFacadeTest {
    
    private static Post testPost;
    private static Product testProduct;
    private static Note testNote;

    @BeforeClass
    public static void setUp() throws Exception {
        testPost = new Post().type(PostType.OFFER).title("Test").description("Test description").owner("Test Supplier").date(new Date().toString());
        testProduct = new Product().productName("Test").chemicalName("Test").deliveryTime("Test").description("Test").producer("Test Supplier").price(4.20).molWeight(4.20).packaging("Test");
        testNote = new Note().creationDate(new java.sql.Date(1)).editor("Test Supplier").text("Test Text");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        if (DatabaseFacade.getInstance().getPosts().contains(testPost)) {
            DatabaseFacade.getInstance().deletePost(testPost);
        }
        for(Product product : DatabaseFacade.getInstance().getProducts("Test Supplier")) {
            DatabaseFacade.getInstance().deleteProduct(product);
        }
        testPost = null;
        testProduct = null;
    }

    @Test
    public void getLoginTest() throws Exception {
        Hash hash = new Hash();
        User user = new User().username("Test Supplier").rights(User.RightsEnum.SUPPLIER);
        String password = "123";
        byte[] salt = DatabaseFacade.getInstance().getSalt("Test Supplier");
        password = hash.getHashedPassword(password, salt);
        assertEquals(user, DatabaseFacade.getInstance().getLogin("Test Supplier", password));
    }

    @Test
    public void wrongUserTest() throws Exception {
        assertNull(DatabaseFacade.getInstance().getLogin("1", "1"));
    }

    @Test
    public void getSuppliersTest() throws Exception {
        assertTrue(DatabaseFacade.getInstance().getSuppliers()
                .parallelStream()
                .filter(page -> page.getOwner().equals("Test Supplier"))
                .count() > 0);
    }

    @Test
    public void addNoteToSupplierTest() throws Exception { //Karim's test, orker ikke at fixe den
        String supplier = "Test supplier";
        Page testPage = null;
        for (Page p : DatabaseFacade.getInstance().getSuppliers()) {
            if (p.getOwner().equalsIgnoreCase(supplier)) {
                testPage = p;
            }
        }
        if (testPage.getNote() == null) {
            DatabaseFacade.getInstance().addNoteToSupplier(supplier, testNote);
            testPage = null;
            for (Page p : DatabaseFacade.getInstance().getSuppliers()) {
                if (p.getOwner().equalsIgnoreCase(supplier)) {
                    testPage = p;
                }
            }
            assertNotNull(testPage.getNote());
        }
        else {
            DatabaseFacade.getInstance().editNoteOnSupplier(supplier, testNote.text("More Test Text"));
            testPage = null;
            for (Page p : DatabaseFacade.getInstance().getSuppliers()) {
                if (p.getOwner().equalsIgnoreCase(supplier)) {
                    testPage = p;
                }
            }
            assertEquals(testNote.getText(), testPage.getNote().getText());
        }
    }


    @Test
    public void getProductsTest() throws Exception {
        addProduct();
        assertFalse(DatabaseFacade.getInstance().getProducts("Test Supplier").isEmpty());
    }

    @Test
    public void getPostsTest() throws Exception {
        assertFalse(DatabaseFacade.getInstance().getPosts().isEmpty());
    }
    
    private void addPost() throws Exception {
        testPost.id(DatabaseFacade.getInstance().addPost("Test Supplier", testPost));
        assertTrue(DatabaseFacade.getInstance().getPosts().contains(testPost));
    }

    @Test
    public void deletePostTest() throws Exception {
        addPost();
        DatabaseFacade.getInstance().deletePost(testPost);
        assertFalse(DatabaseFacade.getInstance().getPosts().contains(testPost));
    }
    
    private void addProduct() throws Exception {
        testProduct.id(DatabaseFacade.getInstance().addProduct(testProduct));
        DatabaseFacade.getInstance().addProductToPage(testProduct);
        assertTrue(DatabaseFacade.getInstance().getProducts("Test Supplier").contains(testProduct));
    }

    @Test
    public void deleteProductTest() throws Exception {
        addProduct();
        DatabaseFacade.getInstance().deleteProduct(testProduct);
        assertFalse(DatabaseFacade.getInstance().getProducts("Test Supplier").contains(testProduct));
    }

    @Test
    public void getPDFFilePathTest() throws Exception {
        DatabaseFacade.getInstance().getPDFFilePath(1);
        assertEquals("".getClass(), DatabaseFacade.getInstance().getPDFFilePath(1).getClass());
    }
}
