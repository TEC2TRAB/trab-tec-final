/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import classes.ConnectionFactory;
import model.Produto;
import model.dao.ProdutoDao;

/**
 *
 * @author Esdras
 */
public class ControlProduto {
    
    public void cadastrarProduto(Produto produto) throws SQLException{
    	Connection connection = new ConnectionFactory().getConnection();
    	ProdutoDao dao = new ProdutoDao(connection);
        dao.cadastrar(produto);
        connection.close();
    }
    
    public List<Produto> consultarProduto(int id) throws SQLException{
    	Connection connection = new ConnectionFactory().getConnection();
    	ProdutoDao dao = new ProdutoDao(connection);
    	List<Produto> produtos = dao.consultar(id);
    	connection.close();
        return produtos;
    }
    
    public List<Produto> consultarProduto(String nome) throws SQLException{
    	Connection connection = new ConnectionFactory().getConnection();
    	ProdutoDao dao = new ProdutoDao(connection);
    	List<Produto> produtos = dao.consultar(nome);
    	connection.close();
    	return produtos;
    }
    
    public void atualizarProduto(Produto produto) throws SQLException{
    	Connection connection = new ConnectionFactory().getConnection();
    	ProdutoDao dao = new ProdutoDao(connection);
        dao.atualizar(produto);
    	connection.close();
    }
    
    public void retirarProduto(int id, double quantidade) throws SQLException{
    	Connection connection = new ConnectionFactory().getConnection();
    	ProdutoDao dao = new ProdutoDao(connection);
        dao.retirar(id, quantidade);
        connection.close();
    }
    
    public void adicionarProduto(int id, double quantidade) throws SQLException{
    	Connection connection = new ConnectionFactory().getConnection();
    	ProdutoDao dao = new ProdutoDao(connection);
    	dao.adicionar(id, quantidade);
    	connection.close();
    }
    
    public boolean verificarProdutoEstoque(int id, double quantidadeCompra) throws SQLException{
    	Connection connection = new ConnectionFactory().getConnection();
    	ProdutoDao dao = new ProdutoDao(connection);
    	boolean estoque = dao.verificarEstoque(id, quantidadeCompra);
    	connection.close();
    	return estoque;
    }
}
