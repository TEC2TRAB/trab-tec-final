/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Dao;

import Classes.ConnectionFactory;
import Model.Cliente;
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
    
    public void cadastrar(Cliente cliente) throws SQLException{
        String sqlPessoa = "INSERT INTO pessoa " +
                           "(numero,data_nascimento,sexo,nome,cep,bairro,cidade," +
                           "estado,complemento,cpf,rg,rua)" +
                           "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        
        String sqlCliente = "INSERT INTO cliente " +
                            "(cpf, data_cadastro)" +
                            "VALUES (?,?)";
        
        String sqlChecar = "SELECT * FROM pessoa WHERE cpf = ?";
        
        PreparedStatement statement = this.connection.prepareStatement(sqlChecar);
        statement.setString(1, Long.toString(cliente.getCPF()));

        ResultSet resultadoChecar = statement.executeQuery();

        if(!resultadoChecar.isBeforeFirst()) {
            statement.clearParameters();
            statement = this.connection.prepareStatement(sqlPessoa);

            statement.setInt(1, cliente.getNumero());
            statement.setDate(2, new Date(cliente.getDtNasc().getTimeInMillis()));
            statement.setString(3, String.valueOf(cliente.getSexo()));
            statement.setString(4, cliente.getNome().toUpperCase());
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
        statement.setDate(2, new Date(System.currentTimeMillis()));

        statement.execute();
        statement.close();
    }
    
    public List<Cliente> consultar(long cpf) throws SQLException{
        String sql = "SELECT * FROM pessoa INNER JOIN cliente " +
                     "ON pessoa.cpf = cliente.cpf " +
                     "WHERE pessoa.cpf = ?";
        
        
        List<Cliente> clientes = new ArrayList<>();
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setString(1, Long.toString(cpf));

        ResultSet resultado = statement.executeQuery();

        while(resultado.next()) {
            Cliente cliente = new Cliente();

            cliente.setId(resultado.getInt("id_cliente"));
            cliente.setCPF(Long.parseLong(resultado.getString("cpf")));
            cliente.setNumero(resultado.getInt("numero"));
            cliente.setSexo(resultado.getString("sexo").charAt(0));
            cliente.setNome(resultado.getString("nome"));
            cliente.setCep(resultado.getString("cep"));
            cliente.setBairro(resultado.getString("bairro"));
            cliente.setCidade(resultado.getString("cidade"));
            cliente.setEstado(resultado.getString("estado"));
            cliente.setComple(resultado.getString("complemento"));
            cliente.setRG(resultado.getLong("rg"));
            cliente.setRua(resultado.getString("rua"));

            Calendar data1 = Calendar.getInstance();
            data1.setTime(resultado.getDate("data_nascimento"));
            cliente.setDtNasc(data1);
            
            Calendar data2 = Calendar.getInstance();
            data2.setTime(resultado.getDate("data_cadastro"));
            cliente.setDataDeCadastro(data2);

            clientes.add(cliente);
        }
        resultado.close();
        statement.close();

        return clientes;
    }
    
    public List<Cliente> consultar(String nome) throws SQLException{
        String sql = "SELECT * FROM pessoa INNER JOIN cliente " +
                            "ON pessoa.cpf = cliente.cpf " +
                            "WHERE pessoa.nome LIKE ?";
        
        
        List<Cliente> clientes = new ArrayList<>();
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setString(1, nome.toUpperCase() + "%");

        ResultSet resultado = statement.executeQuery();
        while(resultado.next()) {
            Cliente cliente = new Cliente();

            cliente.setId(resultado.getInt("id_cliente"));
            cliente.setNumero(resultado.getInt("numero"));
            cliente.setSexo(resultado.getString("sexo").charAt(0));
            cliente.setNome(resultado.getString("nome"));
            cliente.setCep(resultado.getString("cep"));
            cliente.setBairro(resultado.getString("bairro"));
            cliente.setCidade(resultado.getString("cidade"));
            cliente.setEstado(resultado.getString("estado"));
            cliente.setComple(resultado.getString("complemento"));
            cliente.setRG(resultado.getLong("rg"));
            cliente.setRua(resultado.getString("rua"));
            cliente.setCPF(Long.parseLong(resultado.getString("cpf")));

            Calendar data1 = Calendar.getInstance();
            data1.setTime(resultado.getDate("data_nascimento"));
            cliente.setDtNasc(data1);
            
            Calendar data2 = Calendar.getInstance();
            data2.setTime(resultado.getDate("data_cadastro"));
            cliente.setDataDeCadastro(data2);

            clientes.add(cliente);
        }
        resultado.close();
        statement.close();

        return clientes;
    }
    
    public void atualizar(Cliente cliente) throws SQLException{
        String sql = "UPDATE pessoa SET numero = ?,"+
                     "cep = ?, bairro = ?, cidade = ?," +
                     "estado = ?, complemento = ?, rua = ?" +
                     "WHERE cpf = ?";
        
        
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setInt(1, cliente.getNumero());
        statement.setString(2, cliente.getCep());
        statement.setString(3, cliente.getBairro());
        statement.setString(4, cliente.getCidade());
        statement.setString(5, cliente.getEstado());
        statement.setString(6, cliente.getComple());
        statement.setString(7, cliente.getRua());
        statement.setString(8, Long.toString(cliente.getCPF()));

        statement.execute();
        statement.close();
    }
    
    public void remover(Long cpf) throws SQLException{
        String sql = "DELETE FROM cliente WHERE cpf = ?";
        
        
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setString(1, Long.toString(cpf));

        statement.execute();
        statement.close();
    }
}

