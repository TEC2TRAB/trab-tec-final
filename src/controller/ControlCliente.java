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
import model.Cliente;
import model.dao.ClienteDao;

/**
 *
 * @author Esdras
 */
public class ControlCliente {
    
    public void cadastrarCliente(Cliente cliente) throws SQLException {
    	Connection connection = new ConnectionFactory().getConnection();
    	ClienteDao dao = new ClienteDao(connection);
        dao.cadastrar(cliente);
        connection.close();
    }
    
    public List<Cliente> consultarCliente(long cpf) throws SQLException{
    	Connection connection = new ConnectionFactory().getConnection();
    	ClienteDao dao = new ClienteDao(connection);
    	List<Cliente> clientes = dao.consultar(cpf);
        connection.close();
    	return clientes;
    }
    
    public List<Cliente> consultarCliente(String nome) throws SQLException{
    	Connection connection = new ConnectionFactory().getConnection();
    	ClienteDao dao = new ClienteDao(connection);
    	List<Cliente> clientes = dao.consultar(nome);
        connection.close();
    	return clientes;
    }
    
    public void atualizarCliente(Cliente cliente) throws SQLException{
    	Connection connection = new ConnectionFactory().getConnection();
    	ClienteDao dao = new ClienteDao(connection);
        dao.atualizar(cliente);
        connection.close();
    }
}
