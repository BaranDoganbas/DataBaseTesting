package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {

    //connection
    //statement
    //con ve statement kapatma

    private static Connection connection;
    private static Statement statement;

    //1. Adım : connection olustur
    public static void connectionOlustur(String hostname, String dbisim, String username, String password) {

        //com.mysql.jdbc.Driver
        //oracle.jdbc.driver.OracleDriver
        //org.postgresql.Driver
        //com.microsoft.sqlserver.jdbc.SQLServerDrive
        //org.sqlite.JDBC

        //1. Adım: Driver'a kaydol
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //2. Adım: Connection olustur.
        //url syntax:jdbc:postgresql://hostname:portnumber/databaseismi
        String url = "jdbc:postgresql://" + hostname + "5432/" + dbisim;

        try {
            connection = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static Statement statementOlustur(){
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }

    public static void connectionStatementKapat(){
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}