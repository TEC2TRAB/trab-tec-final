/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Classes.ConnectionFactory;
import ModuloDeProdutos.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Esdras
 */
public class ProdutoDao {
    private Connection connection;
    
    public ProdutoDao() {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void cadastrar(Produto produto) {
        String sql = "INSERT INTO produto " +
                     "(id_produto,preco,quantidade,descricao,nome) " +
                     "VALUES (?,?,?,?,?)";
        
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            
            statement.setInt(1, produto.getId());
            statement.setDouble(2, produto.getPreco());
            statement.setInt(3, produto.getQuantidade());
            statement.setString(4, produto.getDescricao());
            statement.setString(5, produto.getNome());
            
            statement.execute();
            statement.close();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Produto consultar(int id) {
        String sql = "SELECT * FROM produto "+
                     "WHERE id_produto = ?";
        
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, id);
            
            ResultSet resultado = statement.executeQuery();
            Produto produto = new Produto();
            while(resultado.next()) {
                produto.setId(resultado.getInt("id_produto"));
                produto.setPreco(resultado.getDouble("preco"));
                produto.setQuantidade(resultado.getInt("quantidade"));
                produto.setDescricao(resultado.getString("descricao"));
                produto.setNome(resultado.getString("nome"));
            }
            statement.execute();
            statement.close();
            
            return produto;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
