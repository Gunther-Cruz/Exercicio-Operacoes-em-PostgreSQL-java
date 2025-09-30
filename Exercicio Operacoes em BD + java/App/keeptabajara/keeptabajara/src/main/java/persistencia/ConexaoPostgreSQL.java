package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoPostgreSQL {
    private String host;
    private String username;
    private String password;
    private String dbname;
    private String port;

    public ConexaoPostgreSQL(){
        this.host = "localhost";        
        this.dbname = "anotacao_db";
        this.username = "postgres";
        this.password = "postgres";
        this.port = "5432";
    }

    public Connection getConexao() throws SQLException{
        String url = "jdbc:postgresql://"+this.host+":"+this.port+"/"+this.dbname;
        return DriverManager.getConnection(url, this.username, this.password);
    }
    
}
