/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Classes.ConnectionFactory;
import ModuloDePessoas.Cliente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Esdras
 */

public class ClienteDao {
    private Connection connection;
    
    public ClienteDao() {
        this.connection = new ConnectionFactory().getConnection(); 
    }
    
    public void cadastrar(Cliente cliente) {
        String sqlPessoa = "INSERT INTO pessoa " +
                           "(numero,data_nascimento,sexo,nome,cep,bairro,cidade," +
                           "estado,complemento,cpf,rg,rua)" +
                           "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        
        String sqlCliente = "INSERT INTO cliente " +
                            "(cpf)" +
                            "VALUES (?)";
        
        String sqlChecar = "SELECT * FROM pessoa WHERE cpf = ?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(sqlChecar);
            statement.setString(1, Long.toString(cliente.getCPF()));
            
            ResultSet resultadoChecar = statement.executeQuery();
            
            if(!resultadoChecar.isBeforeFirst()) {
                statement.clearParameters();
                statement = this.connection.prepareStatement(sqlPessoa);
                
                statement.setInt(1, cliente.getNumero());
                statement.setDate(2, new Date(cliente.getDtNasc().getTimeInMillis()));
                statement.setString(3, String.valueOf(cliente.getSexo()));
                statement.setString(4, cliente.getNome());
                statement.setString(5, cliente.getCep());
                statement.setString(6, cliente.getBairro());
                statement.setString(7, cliente.getCidade());
                statement.setString(8, cliente.getEstado());
                statement.setString(9, cliente.getComple());
                statement.setString(10, Long.toString(cliente.getCPF()));
                statement.setLong(11, cliente.getRG());
                statement.setString(12, cliente.getRua());

                statement.execute();
            }
            statement.clearParameters();
            statement = this.connection.prepareStatement(sqlCliente);

            statement.setString(1, Long.toString(cliente.getCPF()));

            statement.execute();
            statement.close();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Este CPF já foi cadastrado.", "Alerta", JOptionPane.WARNING_MESSAGE);
            throw new RuntimeException(e);
        }
    }
    
    public List<Cliente> consultar(long cpf) {
        String sqlCliente = "SELECT * FROM cliente "+
                            "WHERE cpf = ?";
        
        String sqlPessoa = "SELECT * FROM pessoa "+
                           "WHERE cpf = ?";
        
        try {
            List<Cliente> clientes = new ArrayList<>();
            PreparedStatement statementCliente = this.connection.prepareStatement(sqlCliente);
            statementCliente.setString(1, Long.toString(cpf));
            
            ResultSet resultadoCliente = statementCliente.executeQuery();
            
            while(resultadoCliente.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(resultadoCliente.getInt("id_cliente"));
                cliente.setCPF(Long.parseLong(resultadoCliente.getString("cpf")));
                
                PreparedStatement statementPessoa = this.connection.prepareStatement(sqlPessoa);
                statementPessoa.setString(1, Long.toString(cliente.getCPF()));
                
                ResultSet resultadoPessoa = statementPessoa.executeQuery();
                while(resultadoPessoa.next()) {
                    cliente.setNumero(resultadoPessoa.getInt("numero"));
                    cliente.setSexo(resultadoPessoa.getString("sexo").charAt(0));
                    cliente.setNome(resultadoPessoa.getString("nome"));
                    cliente.setCep(resultadoPessoa.getString("cep"));
                    cliente.setBairro(resultadoPessoa.getString("bairro"));
                    cliente.setCidade(resultadoPessoa.getString("cidade"));
                    cliente.setEstado(resultadoPessoa.getString("estado"));
                    cliente.setComple(resultadoPessoa.getString("complemento"));
                    cliente.setRG(resultadoPessoa.getLong("rg"));
                    cliente.setRua(resultadoPessoa.getString("rua"));
                    
                    Calendar data3 = Calendar.getInstance();
                    data3.setTime(resultadoPessoa.getDate("data_nascimento"));
                    cliente.setDtNasc(data3);
                }
                
                clientes.add(cliente);
                
                resultadoPessoa.close();
                statementPessoa.close();
            }
            resultadoCliente.close();
            statementCliente.close();
            
            return clientes;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Cliente> consultar(String nome) {
        String sqlCliente = "SELECT * FROM cliente "+
                            "WHERE cpf = ?";
        
        String sqlPessoa = "SELECT * FROM pessoa "+
                           "WHERE nome LIKE ?";
        
        try {
            List<Cliente> clientes = new ArrayList<>();
            PreparedStatement statementPessoa = this.connection.prepareStatement(sqlPessoa);
            statementPessoa.setString(1, nome + "%");
            
            ResultSet resultadoPessoa = statementPessoa.executeQuery();
            while(resultadoPessoa.next()) {
                Cliente cliente = new Cliente();
                cliente.setNumero(resultadoPessoa.getInt("numero"));
                cliente.setSexo(resultadoPessoa.getString("sexo").charAt(0));
                cliente.setNome(resultadoPessoa.getString("nome"));
                cliente.setCep(resultadoPessoa.getString("cep"));
                cliente.setBairro(resultadoPessoa.getString("bairro"));
                cliente.setCidade(resultadoPessoa.getString("cidade"));
                cliente.setEstado(resultadoPessoa.getString("estado"));
                cliente.setComple(resultadoPessoa.getString("complemento"));
                cliente.setRG(resultadoPessoa.getLong("rg"));
                cliente.setRua(resultadoPessoa.getString("rua"));
                cliente.setCPF(Long.parseLong(resultadoPessoa.getString("cpf")));

                Calendar data1 = Calendar.getInstance();
                data1.setTime(resultadoPessoa.getDate("data_nascimento"));
                cliente.setDtNasc(data1);
                PreparedStatement statementCliente = this.connection.prepareStatement(sqlCliente);
                statementCliente.setString(1, Long.toString(cliente.getCPF()));
                
                ResultSet resultadoCliente = statementCliente.executeQuery();
                while(resultadoCliente.next()) {
                    cliente.setId(resultadoCliente.getInt("id_cliente"));
                }
                
                clientes.add(cliente);
                
                resultadoCliente.close();
                statementCliente.close();
            }
            resultadoPessoa.close();
            statementPessoa.close();
            
            return clientes;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

