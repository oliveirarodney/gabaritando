/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author maykh
 */
public class DatabaseSQLServer implements Database{

    private Connection connection;
    private static DatabaseSQLServer dataBase;

   private DatabaseSQLServer(){
       
   }
   
   public static DatabaseSQLServer getInstancia(){
       if (dataBase==null) {
           DatabaseSQLServer.dataBase = new DatabaseSQLServer();
       }
       return dataBase;
   }
    @Override
    public Connection conectar() {
        if (connection == null) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://"
                        + "localhost:1433;"
                        + "database=Gabaritando;"
                        + "user=gabaritandoAdmin;"
                        + "password=abc123;";

                this.connection = DriverManager.getConnection(url);
                System.out.println("Conectado");
                return this.connection;
            } catch (SQLException | ClassNotFoundException ex) {
                System.out.println("Desconectado: " + ex.getMessage());
                return null;
            }
        }

        return connection;
    }

//    @Override
    @Override
    public void desconectar(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao desconectar banco");
            }
        }
    }
}
