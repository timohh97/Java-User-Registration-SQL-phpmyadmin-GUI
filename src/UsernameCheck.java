import java.sql.*;

public class UsernameCheck {

    public static boolean checkIfUsernameExists(String username) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/besucher","root","");
        Statement statement = c.createStatement();
        String sql = "Select * from user where username='"+username+"'";
        ResultSet rs = statement.executeQuery(sql);
        return !rs.next();

    }
}
