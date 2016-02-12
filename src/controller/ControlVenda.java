/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import classes.ConnectionFactory;
import model.Venda;
import model.dao.VendaDao;

/**
 *
 * @author Esdras
 */
public class ControlVenda {
    
    public void cadastrarVenda(Venda venda) throws SQLException{
    	Connection connection = new ConnectionFactory().getConnection();
    	VendaDao dao = new VendaDao(connection);
    	dao.cadastrar(venda);
    	connection.close();
    }
    
    public List<Venda> consultarVenda(long cpf) throws SQLException{
    	Connection connection = new ConnectionFactory().getConnection();
    	VendaDao dao = new VendaDao(connection);
    	List<Venda> vendas = dao.consultar(cpf);
        connection.close();
    	return vendas;
    }
    
    public List<Venda> consultarVenda(int id) throws SQLException{
    	Connection connection = new ConnectionFactory().getConnection();
    	VendaDao dao = new VendaDao(connection);
    	List<Venda> vendas = dao.consultar(id);
    	connection.close();
        return vendas;
    }
    
    public List<Venda> consultarVenda(Date data) throws SQLException{
    	Connection connection = new ConnectionFactory().getConnection();
    	VendaDao dao = new VendaDao(connection);
    	List<Venda> vendas = dao.consultar(data);
    	connection.close();
        return vendas;
    }
    
    public Venda consultarVendaPorId(int id) throws SQLException{
    	Connection connection = new ConnectionFactory().getConnection();
    	VendaDao dao = new VendaDao(connection);
    	Venda venda = dao.consultarPorId(id);
    	connection.close();
    	return venda;
    }
}
