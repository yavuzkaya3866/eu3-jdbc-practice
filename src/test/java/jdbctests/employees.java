package jdbctests;

import java.sql.*;

public class employees {
    public static void main(String[] args) throws SQLException {

        String dbUrl= "jdbc:oracle:thin:@54.174.126.24:1521:xe";
        String dbUsername= "hr";
        String dbPassword = "hr";

        //create connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement = connection.createStatement();
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("Select * from employees");

        while(resultSet.next()) {
            System.out.println(resultSet.getString(1) + " - " +
                    resultSet.getString(2) + " - " +
                    resultSet.getString(3) + " - " +
                    resultSet.getString(4));

        }

        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }

}