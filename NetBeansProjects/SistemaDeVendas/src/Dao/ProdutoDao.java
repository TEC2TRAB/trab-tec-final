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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

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
            statement.setString(5, produto.getNome().toUpperCase());
            
            statement.execute();
            statement.close();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Este ID já foi cadastrado.", "Alerta", JOptionPane.WARNING_MESSAGE);
            throw new RuntimeException(e);
        }
    }
    
    public List<Produto> consultar(int id) {
        String sql = "SELECT * FROM produto "+
                     "WHERE id_produto = ?";
        
        try {
            List<Produto> produtos = new ArrayList<>();
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, id);
            
            ResultSet resultado = statement.executeQuery();
            while(resultado.next()) {
                Produto produto = new Produto();
                produto.setId(resultado.getInt("id_produto"));
                produto.setPreco(resultado.getDouble("preco"));
                produto.setQuantidade(resultado.getInt("quantidade"));
                produto.setDescricao(resultado.getString("descricao"));
                produto.setNome(resultado.getString("nome"));
                produtos.add(produto);
            }
            resultado.close();
            statement.close();
            
            return produtos;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Produto> consultar(String nome) {
        String sql = "SELECT * FROM produto "+
                     "WHERE nome LIKE ?";
        
        try {
            List<Produto> produtos = new ArrayList<>();
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, nome.toUpperCase() + "%");
            
            ResultSet resultado = statement.executeQuery();
            while(resultado.next()) {
                Produto produto = new Produto();
                produto.setId(resultado.getInt("id_produto"));
                produto.setPreco(resultado.getDouble("preco"));
                produto.setQuantidade(resultado.getInt("quantidade"));
                produto.setDescricao(resultado.getString("descricao"));
                produto.setNome(resultado.getString("nome"));
                produtos.add(produto);
            }
            resultado.close();
            statement.close();
            
            return produtos;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void atualizar(Produto produto) {
        String sql = "UPDATE produto SET preco = ?," +
                     "quantidade = ?, descricao = ?" +
                     "WHERE id = ?";
        
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setDouble(1, produto.getPreco());
            statement.setInt(2, produto.getQuantidade());
            statement.setString(3, produto.getDescricao());
            statement.setInt(4, produto.getId());
            
            statement.execute();
            statement.close();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void remover(int id) {
        String sql = "DELETE FROM produto WHERE id = ?";
        
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, id);
            
            statement.execute();
            statement.close();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
