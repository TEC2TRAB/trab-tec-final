/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Esdras
 */
public class ConnectionFactory {
    public Connection getConnection() {
        String url = "jdbc:postgresql://localhost:5432/bdvendas?currentschema=trabalho";
        String usuario = "postgres";
        String senha = "admin";
        
        try {
            return DriverManager.getConnection(url, usuario, senha);
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
