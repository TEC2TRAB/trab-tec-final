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
import model.Funcionario;
import model.dao.FuncionarioDao;

/**
 *
 * @author Esdras
 */
public class ControlFuncionario {
    
    public void cadastrarFuncionario(Funcionario funcionario) throws SQLException{
    	Connection connection = new ConnectionFactory().getConnection(); 
    	FuncionarioDao dao = new FuncionarioDao(connection);
        dao.cadastrar(funcionario);
        connection.close();
    }
    
    public List<Funcionario> consultarFuncionario(long cpf) throws SQLException{
    	Connection connection = new ConnectionFactory().getConnection(); 
    	FuncionarioDao dao = new FuncionarioDao(connection);
    	List<Funcionario> funcionarios = dao.consultar(cpf);
        connection.close();
    	return funcionarios;
    }
    
    public List<Funcionario> consultarFuncionario(String nome) throws SQLException{
    	Connection connection = new ConnectionFactory().getConnection(); 
    	FuncionarioDao dao = new FuncionarioDao(connection);
    	List<Funcionario> funcionarios = dao.consultar(nome);
        connection.close();
    	return funcionarios;
    }
    
    public void atualizarFuncionario(Funcionario funcionario) throws SQLException{
    	Connection connection = new ConnectionFactory().getConnection(); 
    	FuncionarioDao dao = new FuncionarioDao(connection);
        dao.atualizar(funcionario);
        connection.close();
    }
    
    public void atualizarFuncionario(String login, String senha, long cpf) throws SQLException{
    	Connection connection = new ConnectionFactory().getConnection(); 
    	FuncionarioDao dao = new FuncionarioDao(connection);
        dao.atualizar(login, senha, cpf);
        connection.close();
    }
    
    public boolean verificarSenhaFuncionario(String login, String senha) throws SQLException{
    	Connection connection = new ConnectionFactory().getConnection(); 
    	FuncionarioDao dao = new FuncionarioDao(connection);
    	boolean verificar = dao.verificarSenha(login, senha);
        connection.close();
    	return verificar;
    }
}
