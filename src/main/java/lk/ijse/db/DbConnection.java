package lk.ijse.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static  DbConnection dbConnection;

    private Connection connection;

    private DbConnection() throws SQLException {
       connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/importMaster2","root","Ijse@1234");
        System.out.println("connectio ok");
    }

    public static DbConnection getInstance() throws SQLException {
        if(dbConnection== null){
            return new DbConnection();
        }
         return  dbConnection;
    }

    public Connection getConnection(){
        return connection;
    }



}
