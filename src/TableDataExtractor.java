import java.sql.*;

public class TableDataExtractor {

    public static String getTableData() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/besucher","root","");
        Statement statement = c.createStatement();
        String sql = "Select * from user";
        ResultSet rs = statement.executeQuery(sql);
        String resultString = "<html>Id Username Password";
        while(rs.next())
        {
            resultString = resultString+"<br>"+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3);
        }

        return resultString;
    }
}
