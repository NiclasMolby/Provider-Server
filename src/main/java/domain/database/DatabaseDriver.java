package domain.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import io.swagger.model.*;
import io.swagger.model.User.RightsEnum;
import java.util.List;

public class DatabaseDriver {

    private static DatabaseDriver instance;
    private Connection connection;
    private Statement stmt;
    private PreparedStatement preparedStatement;
    private ResultSet result;

    public static DatabaseDriver getInstance() {
        if(instance == null) {
            instance = new DatabaseDriver();
        }
        return instance;
    }

    private DatabaseDriver() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://tek-mmmi-db0a.tek.c.sdu.dk:5432/group_2_db", "group_2", "MDI5NTli");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public User getLogin(String username, String password) throws NullPointerException {
        String query = "SELECT * FROM public.user WHERE public.user.username = ? AND public.user.password = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            result = preparedStatement.executeQuery();

            while(result.next()) {
                return new User().username(result.getString(1)).rights(RightsEnum.PROVIA);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Page> getSuppliers() {
        String query = "SELECT public.user.username, public.note.text, public.note.date, public.note.lasteditor FROM public.user "
                + "LEFT JOIN public.note ON public.user.username = public.note.supplier WHERE public.user.rights='Supplier'";
        List<Page> pageList = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            result = stmt.executeQuery(query);
            Page page;
            while(result.next()) {
                if(result.getString(2) == null && result.getDate(3) == null) {
                    page = new Page().owner(result.getString(1));
                }
                else {
                    page = new Page().owner(result.getString(1)).note(new Note().text(result.getString(2)).creationDate(result.getDate(3)).editor(result.getString(4)));
                }
                pageList.add(page);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return pageList;
    }

    public List<Product> getProducts(String page) {
        String query = "SELECT public.product.id, public.product.\"productName\", public.product.description, "
                + "public.product.price, public.product.packaging, public.product.\"chemicalName\", public.product.density, "
                + "public.product.\"deliveryTime\" FROM public.product "
                + "INNER JOIN public.pageproducts ON public.product.id = public.pageproducts.product WHERE public.pageproducts.page = ?;";
        List<Product> productList = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, page);

            result = preparedStatement.executeQuery();
            while (result.next()) {
                productList.add(new Product().productName(result.getString(2)).description(result.getString(3)).price(result.getString(4)).packaging(result.getString(5)).chemicalName(result.getString(6)).molWeight(result.getString(7)).deliveryTime(result.getString(8)).producer(page));

            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public List<Post> getPosts() {
        String query = "SELECT * FROM public.post";
        List<Post> postList = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            result = stmt.executeQuery(query);
            while(result.next()) {
                PostType type;
                switch(result.getString(4)) {
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
                postList.add(new Post().owner(result.getString(1)).title(result.getString(3)).description(result.getString(2)).type(type).date(result.getString(6)).id(result.getInt(5)));
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return postList;
    }

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
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

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
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePost(String owner, Post post) {
        String query = "UPDATE public.post SET text = ?, title = ?, date = ? WHERE id = ?;";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, post.getDescription());
            preparedStatement.setString(2, post.getTitle());
            preparedStatement.setString(3, post.getDate());
            preparedStatement.setInt(4, post.getId());
            preparedStatement.execute();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

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
        catch(SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void deletePost(Post post) {
        String query = "DELETE FROM public.post WHERE public.post.id = ?;";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, post.getId());
            preparedStatement.execute();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

}
