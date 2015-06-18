/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Esdras
 */
public class Sessao {
    private String nome;
    private int id;
    private String login;
    private long cpf;
    private String permissao;
    private Connection connection;
    
    public Sessao() {
        this.connection = new ConnectionFactory().getConnection(); 
    }
    
    public boolean login(String login, String senha) {
        String sql = "SELECT nome, id_funcionario, funcionario.cpf, demissao, permissao FROM pessoa INNER JOIN funcionario " +
                     "ON pessoa.cpf = funcionario.cpf " +
                     "WHERE login = ? AND senha = ?";
        
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, senha);

            ResultSet resultado = statement.executeQuery();
            if(!resultado.isBeforeFirst()) {
                resultado.close();
                statement.close();
                return false;
            }
            while(resultado.next()) {
                if(resultado.getDate("demissao") != null) {
                    throw new IllegalArgumentException("O usuário desta conta é um funcionário demitido.");
                }
                setNome(resultado.getString("nome"));
                setId(resultado.getInt("id_funcionario"));
                setLogin(login);
                setCPF(Long.parseLong(resultado.getString("cpf")));
                setPermissao(resultado.getString("permissao"));
            }                
            resultado.close();
            statement.close();
            return true;
            
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    
    public long getCPF() {
        return cpf;
    }
    public void setCPF(long cpf) {
        this.cpf = cpf;
    }
    
    public String getPermissao() {
        return permissao;
    }
    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }
    
}
