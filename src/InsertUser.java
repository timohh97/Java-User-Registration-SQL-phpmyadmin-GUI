import java.sql.*;

public class InsertUser {


    public static void insertUserIntoDatabase(String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/besucher","root","");
        Statement statement = c.createStatement();
        int rand = (int)(Math.random() * 1000000000);
        String sql = "Insert into user (id,username,password) values ('"+rand+"','"+username+"','"+password+"')";
        statement.executeUpdate(sql);

    }

}
