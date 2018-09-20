package database;

public class DatabaseFactory {
    
    
    public static Database openDatabaseSQLServer(){
        
        return DatabaseSQLServer.getInstancia();
    }
}
