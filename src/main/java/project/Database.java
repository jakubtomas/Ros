package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Database   {
    private final String JDBC = "com.mysql.jdbc.Driver";
    //private final String URL = "jdbc:mysql://localhost:3306/world_x?autoReconnect=true&useSSL=false";
    private final String URL = "jdbc:mysql://itsovy.sk:3306/chat1n?autoReconnect=true&useSSL=false";
    //  private final String QUERY = "SELECT * FROM users Where email like \"%gmail%\" ";
    //private  final  String WORD = "SELECT * FROM users  Where  name like \"%lev%\"  or  second_name  like \"%lev%\"";
    private  final  String WORD = "Select * from message order by id desc limit 10";


    private Connection connection;
    private final String URL2 = "jdbc:mysql://localhost:3306/restaurantorderingsystem?autoReconnect=true&useSSL=false";


    public Connection getConnection() throws Exception {

        Class.forName(JDBC);
        connection = DriverManager.getConnection(URL2, "root", "root");
        return connection;

    }

    public void selectCategory() throws Exception {
       // SELECT * From category
        PreparedStatement statement  = getConnection().prepareStatement("SELECT * From category");
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {

            String name = rs.getString("name");

            System.out.println("resutl form database is " + name );
        }

        connection.close();

    }
}