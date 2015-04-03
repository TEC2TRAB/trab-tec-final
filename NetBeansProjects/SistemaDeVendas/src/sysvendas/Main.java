/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Testando GitHub.
package sysvendas;

import ModuloDePessoas.ConnectionFactory;
import Interface.Menu;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author cesar.nascimento
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Menu m = new Menu();
        m.setVisible(true);
        
        try (Connection con = new ConnectionFactory().getConnection()) {
            System.out.println("Conexão aberta!");
        } catch(SQLException e) {
            System.out.println(e);
        }
    }
}
