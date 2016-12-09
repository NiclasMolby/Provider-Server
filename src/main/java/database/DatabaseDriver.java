package database;

import common.*;
import java.sql.*;
import java.util.ArrayList;
import io.swagger.model.*;
import io.swagger.model.User.RightsEnum;
import java.util.List;

public class DatabaseDriver implements IDatabaseDriver {

    private static DatabaseDriver instance;
    private Connection connection;
    private Statement stmt;
    private PreparedStatement preparedStatement;
    private ResultSet result;

    /**
     * Gets the current instants of the DatabaseDriver.
     * @return The instants of database driver.
     */
    public static DatabaseDriver getInstance() {
        if (instance == null) {
            instance = new DatabaseDriver();
        }
        return instance;
    }

    /**
     * creates a connection to the database.
     */
    private DatabaseDriver() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://tek-mmmi-db0a.tek.c.sdu.dk:5432/group_2_db", 
                    "group_2", "MDI5NTli");
        } 
        catch (Exception e) {
            Logger.log(LogType.WARNING, "Fejl i tilslutningen til databasen.\n" + e);
        }
    }

    /**
     * Gets a user from its username and password.
     * @param username Username for the user
     * @param password Password for the user
     * @return a User if it match a username an password in the database and the users rights.
     */
    public User getLogin(String username, String password) {
        String query = "SELECT public.user.username, public.user.rights FROM public.user WHERE UPPER(public.user.username) = UPPER(?) AND public.user.password = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            result = preparedStatement.executeQuery();
            RightsEnum rights = null;
            if(result.next()) {
                switch (result.getString(2)) {
                    case "Provia":
                        rights = RightsEnum.PROVIA;
                        break;
                    case "Supplier":
                        rights = RightsEnum.SUPPLIER;
                        break;
                    case "Admin":
                        rights = RightsEnum.ADMIN;
                        break;
                }
                return new User().username(result.getString(1)).rights(rights);
            }

        }
        catch (SQLException e) {
            Logger.log(LogType.WARNING, "Fejl i database login metoden.\n" + e);
        }
        return null;
    }

    /**
     * returns a List of all the supplier.
     * @return all the suppliers
     */
    public List<Page> getSuppliers() {
        List<Page> pageList = new ArrayList<>();
        String query = "SELECT public.user.username, public.note.text, public.note.date, "
                + "public.note.lasteditor, public.page.location, public.page.description, "
                + "public.page.contactinformation FROM public.user "
                + "LEFT JOIN public.note ON "
                + "public.user.username = public.note.supplier "
                + "JOIN public.page ON public.page.supplier = public.user.username "
                + "WHERE public.user.rights='Supplier'";
        try {
            stmt = connection.createStatement();
            result = stmt.executeQuery(query);
            Page page;
            while (result.next()) { //gets the suppliers from the results.
                if (result.getString(2) == null && result.getDate(3) == null) {
                    page = new Page().owner(result.getString(1));
                    //Gets a supplier with a empty note
                }
                else {
                    page = new Page().owner(result.getString(1)).note(new Note()
                            .text(result.getString(2)).creationDate(result.getDate(3))
                            .editor(result.getString(4)));
                }
                page.location(result.getString(5)).description(result.getString(6))
                        .contactInformation(result.getString(7));
                pageList.add(page);
            }
        }
        catch (SQLException e) {
            Logger.log(LogType.WARNING, "Fejl i databasen ved hentning af leverandører.\n" + e);
        }
        return pageList;
    }

    /**
     * Gets the all the products from the database
     * @param pageSupplier the product owner name
     * @return a List of alle the products for a owner.
     */
    public List<Product> getProducts(String pageSupplier) {
        String query = "SELECT public.product.id, public.product.productname, public.product.description, "
                + "public.product.price, public.product.packaging, public.product.chemicalname, public.product.density, "
                + "public.product.deliverytime FROM public.product "
                + "INNER JOIN public.pageproducts ON public.product.id = public.pageproducts.product WHERE public.pageproducts.page = ?;";
        List<Product> productList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, pageSupplier);

            result = preparedStatement.executeQuery();
            while (result.next()) {
                productList.add(new Product().id(result.getInt(1))
                        .productName(result.getString(2)).description(result.getString(3))
                        .price(result.getDouble(4)).packaging(result.getString(5))
                        .chemicalName(result.getString(6)).molWeight(result.getDouble(7))
                        .deliveryTime(result.getString(8)).producer(pageSupplier));
            }
        }
        catch (SQLException e) {
            Logger.log(LogType.WARNING, "Fejl i databasen ved hentning af produkter.\n" + e);
        }
        return productList;
    }

    /**
     * Gets all the Posts from the database
     * @return a List with all Posts
     */
    public List<Post> getPosts() {
        String query = "SELECT * FROM public.post";
        List<Post> postList = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            result = stmt.executeQuery(query);
            while (result.next()) {
                PostType type;
                switch (result.getString(4)) {
                    case "Warning":
                        type = PostType.WARNING;
                        break;
                    case "Request":
                        type = PostType.REQUEST;
                        break;
                    case "Offer":
                        type = PostType.OFFER;
                        break;
                    default:
                        type = PostType.NOTAVAILABLE;
                        break;
                }
                postList.add(new Post().owner(result.getString(1)).title(result.getString(3))
                        .description(result.getString(2)).type(type)
                        .date(result.getString(6)).id(result.getInt(5)));
            }
        }
        catch (SQLException e) {
            Logger.log(LogType.WARNING, "Fejl i databasen ved hentning af posts.\n" + e);
        }
        return postList;
    }

    /**
     * Adds a note to a supplier to the database.
     * @param supplierName The name of the supplier.
     * @param note the note that will be added to the supplier.
     */
    public void addNoteToSupplier(String supplierName, Note note) {
        String query = "INSERT INTO public.note(supplier, text, date, lasteditor) VALUES (?, ?, ?, ?);";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, supplierName);
            preparedStatement.setString(2, note.getText());
            preparedStatement.setDate(3, (java.sql.Date) note.getCreationDate());
            preparedStatement.setString(4, note.getEditor());
            preparedStatement.execute();
        }
        catch (SQLException e) {
            Logger.log(LogType.WARNING, "Fejl i databasen ved tilføj note til leverandør.\n" + e);
        }
    }

    /**
     * Edits a already existing note.
     * @param supplierName The supplier which note will be edited.
     * @param note The edited note.
     */
    public void editNoteOnSupplier(String supplierName, Note note) {
        String query = "UPDATE public.note SET text = ?, date = ?, lasteditor = ? WHERE public.note.supplier = ?;";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, note.getText());
            preparedStatement.setDate(2, (java.sql.Date) note.getCreationDate());
            preparedStatement.setString(3, note.getEditor());
            preparedStatement.setString(4, supplierName);
            preparedStatement.execute();
        }
        catch (SQLException e) {
            Logger.log(LogType.WARNING, "Fejl i databasen ved rediger note på leverandør.\n" + e);
        }
    }

    /**
     *Updates a Post in the database.
     * @param post The Edited post.
     */
    public void updatePost(Post post) {
        String query = "UPDATE public.post SET text = ?, title = ?, date = ? WHERE id = ?;";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, post.getDescription());
            preparedStatement.setString(2, post.getTitle());
            preparedStatement.setString(3, post.getDate());
            preparedStatement.setInt(4, post.getId());
            preparedStatement.execute();
        }
        catch (SQLException e) {
            Logger.log(LogType.WARNING, "Fejl i databasen ved opdater post.\n" + e);
        }
    }

    /**
     * Adds a post to a Owner and returns the ID of the new post.
     * @param owner The owner where the post will be added.
     * @param post The new post.
     * @return The ID of the post.
     */
    public int addPost(String owner, Post post) {
        String query = "INSERT INTO public.post(username, type, text, date, title) "
                + "VALUES(?, ?, ?, ?, ?) "
                + "RETURNING id;";
        int id = (int) (Math.random() * Integer.MAX_VALUE);
        try {
            preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, owner);
            preparedStatement.setString(2, post.getType().toString());
            preparedStatement.setString(3, post.getDescription());
            preparedStatement.setString(4, post.getDate());
            preparedStatement.setString(5, post.getTitle());
            preparedStatement.executeUpdate();
            result = preparedStatement.getGeneratedKeys();

            result.next();
            id = result.getInt(1);
        }
        catch (SQLException e) {
            Logger.log(LogType.WARNING, "Fejl i databasen ved tilføj post.\n" + e);
        }
        return id;
    }

    /**
     * Deletes a Post in the database.
     * @param post The post which will be deleted.
     */
    public void deletePost(Post post) {
        String query = "DELETE FROM public.post WHERE id = ?;";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, post.getId());
            preparedStatement.execute();
        }
        catch (SQLException e) {
            Logger.log(LogType.WARNING, "Fejl i database ved slet post.\n" + e);
        }
    }

    /**
     * Deletes a Product in the database.
     * @param product The product which will be deleted.
     */
    public void deleteProduct(Product product) {
        String query = "DELETE FROM public.product WHERE id = ?;";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, product.getId());
            preparedStatement.execute();
        }
        catch (SQLException e) {
            Logger.log(LogType.WARNING, "Fejl i database ved slet produkt.\n" + e);
        }
    }

    /**
     * Updates a entire Page.
     * @param page The supplier which page will be updated.
     * @param description The new Supplier description.
     * @param location The new supplier location.
     * @param contactInformation the new supplier contact information.
     */
    public void updatePage(String page, String description, String location, String contactInformation) {
        String query = "UPDATE public.page SET description = ?, location = ?, contactinformation = ? WHERE supplier = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, description);
            preparedStatement.setString(2, location);
            preparedStatement.setString(3, contactInformation);
            preparedStatement.setString(4, page);
            preparedStatement.execute();
        }
        catch (SQLException e) {
            Logger.log(LogType.WARNING, "Fejl i databasen ved opdater leverandørside.\n" + e);
        }
    }

    /**
     * Updates a product from the product ID.
     * @param product The updated product.
     */
    public void updateProduct(Product product) {
        String query = "UPDATE public.product SET chemicalname = ?, productname = ?, description = ?, deliverytime = ?, price = ?, packaging = ?, density = ?  WHERE id = ?;";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getChemicalName());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setString(4, product.getDeliveryTime()); //// TODO: 05-12-2016 Look at datatypes so we avoid having to parse types
            preparedStatement.setDouble(5, product.getPrice());
            preparedStatement.setString(6, product.getPackaging());
            preparedStatement.setDouble(7, product.getMolWeight());
            preparedStatement.setInt(8, product.getId());
            preparedStatement.execute();
        }
        catch (SQLException e) {
            Logger.log(LogType.WARNING, "Fejl i databasen ved opdater produkt.\n" + e);
        }
    }

    /**
     * Adds a product to the database.
     * @param product The product that will be added to the database.
     * @return The ID of the product.
     */
    public int addProduct(Product product) {
        String query = "INSERT INTO public.product(chemicalname, description, deliverytime, price, packaging, density, productname) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?) "
                + "RETURNING id;";
        int id = (int) (Math.random() * Integer.MAX_VALUE);
        try {
            preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getChemicalName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setString(3, product.getDeliveryTime());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setString(5, product.getPackaging());
            preparedStatement.setDouble(6, product.getMolWeight());
            preparedStatement.setString(7, product.getProductName());
            preparedStatement.executeUpdate();
            result = preparedStatement.getGeneratedKeys();

            result.next();
            id = result.getInt(1);
        }
        catch (SQLException e) {
            Logger.log(LogType.WARNING, "Fejl i databasen ved tilføj produkt.\n" + e);
        }
        return id;
    }

    /**
     * Gets the PDF file path from the database.
     * @param productID The id of the product which PDF path will be returned.
     * @return The file path to the PDF on the server.
     */
    public String getPDFFilePath(int productID) {
        String query = "SELECT pdfpath FROM public.product WHERE id = ?";
        String productFilePath = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productID);
            result = preparedStatement.executeQuery();
            result.next();
            if (result.getString(1) != null) {
                productFilePath = result.getString(1);
            }
        }
        catch (SQLException e) {
            Logger.log(LogType.WARNING, "Fejl i databasen ved hent pdf sti.\n" + e);
        }
        return productFilePath;
    }

    /**
     * Links a product ID to a product producer and saves it in the database.
     * @param product the product that will be linked.
     */
    public void addProductToPage(Product product) {
        String query = "INSERT INTO public.pageproducts(page, product) "
                + "VALUES(?, ?);";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(2, product.getId());
            preparedStatement.setString(1, product.getProducer());//gets the name of the product producer
            preparedStatement.execute();
        }
        catch (SQLException e) {
            Logger.log(LogType.WARNING, "Fejl i databasen ved tilføj produkt til product/page relationen.\n" + e);
        }

    }
}
