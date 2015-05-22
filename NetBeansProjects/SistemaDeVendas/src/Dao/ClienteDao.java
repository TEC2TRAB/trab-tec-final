/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Classes.ConnectionFactory;
import ModuloDePessoas.Cliente;
import ModuloDePessoas.Pessoa;
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
public class ClienteDao extends PessoaDao{
    private Connection connection;
    
    public ClienteDao() {
        super(); 
    }
    
    public void cadastrar(Cliente cliente) {
        String sql = "INSERT INTO cliente " +
                     "(cpf)" +
                     "VALUES (?)";
        
        super.cadastrar(cliente);
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement = this.connection.prepareStatement(sql);
            
            statement.setString(1, cliente.getCPF());
            
            statement.execute();
            statement.close();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Este CPF já foi cadastrado.", "Alerta", JOptionPane.WARNING_MESSAGE);
            throw new RuntimeException(e);
        }
    }
    
    public Cliente consultarCPF(String cpf) {
        String sqlCliente = "SELECT * FROM cliente "+
                            "WHERE cpf = ?";
        
        Pessoa p = super.consultarCPF(cpf);
        Cliente cliente = new Cliente(); 
        try {
            PreparedStatement statementCliente = this.connection.prepareStatement(sqlCliente);
            statementCliente.setString(1, cpf);
            
            ResultSet resultadoCliente = statementCliente.executeQuery();
            while(resultadoCliente.next()) {
                cliente.setId(resultadoCliente.getInt("id_cliente"));
                cliente.setCPF(resultadoCliente.getString("cpf"));
                
                    cliente.setNumero(p.getNumero());
                    cliente.setSexo(p.getSexo());
                    cliente.setNome(p.getNome());
                    cliente.setCep(p.getCep());
                    cliente.setBairro(p.getBairro());
                    cliente.setCidade(p.getCidade());
                    cliente.setEstado(p.getEstado());
                    cliente.setComple(p.getComple());
                    cliente.setRG(p.getRG());
                    cliente.setRua(p.getRua());
                    cliente.setDtNasc(p.getDtNasc());
                    
            }
            resultadoCliente.close();
            statementCliente.close();
            
            return cliente;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Pessoa> consultarNome(String nome) {
        String sqlCliente = "SELECT * FROM cliente "+
                                "WHERE cpf = ?";
        
        String sqlPessoa = "SELECT * FROM pessoa "+
                           "WHERE nome LIKE ?";
        
        try {
            List<Pessoa> clientes = new ArrayList<>();
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
                cliente.setCPF(resultadoPessoa.getString("cpf"));

                Calendar data = Calendar.getInstance();
                data.setTime(resultadoPessoa.getDate("data_nascimento"));
                cliente.setDtNasc(data);
                
                PreparedStatement statementCliente = this.connection.prepareStatement(sqlCliente);
                statementCliente.setString(1, cliente.getCPF());
                
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

