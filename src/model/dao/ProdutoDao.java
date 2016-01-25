/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Esdras
 */
public class ProdutoDao {
    private Connection connection;
    
    public ProdutoDao(Connection connection) {
        this.connection = connection;
    }
    
    public void cadastrar(Produto produto) throws SQLException{
        String sql = "INSERT INTO produto " +
                     "(id_produto,preco,quantidade,descricao,nome) " +
                     "VALUES (?,?,?,?,?)";
        
        
        PreparedStatement statement = this.connection.prepareStatement(sql);

        statement.setInt(1, produto.getId());
        statement.setDouble(2, produto.getPreco());
        statement.setDouble(3, produto.getQuantidade());
        statement.setString(4, produto.getDescricao());
        statement.setString(5, produto.getNome().toUpperCase());

        statement.execute();
        statement.close();
        connection.close();
    }
    
    public List<Produto> consultar(int id) throws SQLException{
        String sql = "SELECT * FROM produto "+
                     "WHERE id_produto = ?";
        
        
        List<Produto> produtos = new ArrayList<>();
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultado = statement.executeQuery();
        while(resultado.next()) {
            Produto produto = new Produto();
            produto.setId(resultado.getInt("id_produto"));
            produto.setPreco(resultado.getDouble("preco"));
            produto.setQuantidade(resultado.getDouble("quantidade"));
            produto.setDescricao(resultado.getString("descricao"));
            produto.setNome(resultado.getString("nome"));
            produtos.add(produto);
        }
        resultado.close();
        statement.close();
        connection.close();

        return produtos;
    }
    
    public List<Produto> consultar(String nome) throws SQLException{
        String sql = "SELECT * FROM produto "+
                     "WHERE nome LIKE ?";
        
        
        List<Produto> produtos = new ArrayList<>();
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setString(1, nome.toUpperCase() + "%");

        ResultSet resultado = statement.executeQuery();
        while(resultado.next()) {
            Produto produto = new Produto();
            produto.setId(resultado.getInt("id_produto"));
            produto.setPreco(resultado.getDouble("preco"));
            produto.setQuantidade(resultado.getDouble("quantidade"));
            produto.setDescricao(resultado.getString("descricao"));
            produto.setNome(resultado.getString("nome"));
            produtos.add(produto);
        }
        resultado.close();
        statement.close();
        connection.close();

        return produtos;
    }
    
    public void atualizar(Produto produto) throws SQLException{
        String sql = "UPDATE produto SET nome = ?,preco = ?," +
                     "quantidade = ?, descricao = ?" +
                     "WHERE id_produto = ?";
        
        
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setString(1, produto.getNome().toUpperCase());
        statement.setDouble(2, produto.getPreco());
        statement.setDouble(3, produto.getQuantidade());
        statement.setString(4, produto.getDescricao());
        statement.setInt(5, produto.getId());

        statement.execute();
        statement.close();
        connection.close();
    }
    
    public void remover(int id) throws SQLException{
        String sql = "DELETE FROM produto WHERE id_produto = ?";
        
        
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setInt(1, id);

        statement.execute();
        statement.close();
        connection.close();
    }
    
    public void retirar(int id, double quantidade) throws SQLException{
        String sql = "UPDATE produto SET quantidade = quantidade - ? WHERE id_produto = ?";
        
        
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setDouble(1, quantidade);
        statement.setInt(2, id);

        statement.execute();
        statement.close();
        connection.close();
    }
    
    public void adicionar(int id, double quantidade) throws SQLException{
        String sql = "UPDATE produto SET quantidade = quantidade + ? WHERE id_produto = ?";
        
        
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setDouble(1, quantidade);
        statement.setInt(2, id);

        statement.execute();
        statement.close();
        connection.close();
    }
    
    public boolean verificarEstoque(int id, double quantidadeCompra) throws SQLException{
        String sql = "SELECT quantidade FROM produto WHERE id_produto = ?";
        
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setInt(1, id);
        
        ResultSet resultado = statement.executeQuery();
        resultado.next();
        
        boolean estoque = resultado.getDouble("quantidade") >= quantidadeCompra;
        
        statement.close();
        resultado.close();
        connection.close();
        
        return estoque;
    }
}
