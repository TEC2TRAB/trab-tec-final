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

/**
 *
 * @author Esdras
 */
public class Sessao {
    private String nome;
    private int id;
    private long cpf;
    private int permissao;
    private Connection connection;
    
    public Sessao() {
        this.connection = new ConnectionFactory().getConnection(); 
    }
    
    public boolean login(String login, String senha) {
        String sql = "SELECT nome, id_funcionario, funcionario.cpf, permissao FROM pessoa INNER JOIN funcionario " +
                     "ON pessoa.cpf = funcionario.cpf " +
                     " WHERE login = ? AND senha = ?";
        
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
                setNome(resultado.getString("nome"));
                setId(resultado.getInt("id_funcionario"));
                setCPF(Long.parseLong(resultado.getString("cpf")));
                setPermissao(resultado.getInt("permissao"));
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
    
    public long getCPF() {
        return cpf;
    }
    
    public void setCPF(long cpf) {
        this.cpf = cpf;
    }
    
    public int getPermissao() {
        return permissao;
    }
    
    public void setPermissao(int permissao) {
        this.permissao = permissao;
    }
    
}
