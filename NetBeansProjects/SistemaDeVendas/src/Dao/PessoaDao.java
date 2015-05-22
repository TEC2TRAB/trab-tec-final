/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Classes.ConnectionFactory;
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
public abstract class PessoaDao {
    private Connection connection;
    
    public PessoaDao() {
        this.connection = new ConnectionFactory().getConnection(); 
    }
    
    public void cadastrar(Pessoa pessoa) {
        String sqlPessoa = "INSERT INTO pessoa " +
                           "(numero,data_nascimento,sexo,nome,cep,bairro,cidade," +
                           "estado,complemento,cpf,rg,rua)" +
                           "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement statement = this.connection.prepareStatement(sqlPessoa);
            
            statement.setInt(1, pessoa.getNumero());
            statement.setDate(2, new Date(pessoa.getDtNasc().getTimeInMillis()));
            statement.setString(3, String.valueOf(pessoa.getSexo()));
            statement.setString(4, pessoa.getNome());
            statement.setString(5, pessoa.getCep());
            statement.setString(6, pessoa.getBairro());
            statement.setString(7, pessoa.getCidade());
            statement.setString(8, pessoa.getEstado());
            statement.setString(9, pessoa.getComple());
            statement.setString(10, pessoa.getCPF());
            statement.setLong(11, pessoa.getRG());
            statement.setString(12, pessoa.getRua());
            
            statement.execute();
            statement.close();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Este CPF já foi cadastrado.", "Alerta", JOptionPane.WARNING_MESSAGE);
            throw new RuntimeException(e);
        }
    }
    
    public Pessoa consultarCPF(String cpf) {        
        String sql = "SELECT * FROM pessoa "+
                     "WHERE cpf = ?";
        
        try {
            Pessoa pessoa = new Pessoa();
                
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, pessoa.getCPF());

            ResultSet resultado = statement.executeQuery();
            while(resultado.next()) {
                pessoa.setNumero(resultado.getInt("numero"));
                pessoa.setSexo(resultado.getString("sexo").charAt(0));
                pessoa.setNome(resultado.getString("nome"));
                pessoa.setCep(resultado.getString("cep"));
                pessoa.setBairro(resultado.getString("bairro"));
                pessoa.setCidade(resultado.getString("cidade"));
                pessoa.setEstado(resultado.getString("estado"));
                pessoa.setComple(resultado.getString("complemento"));
                pessoa.setRG(resultado.getLong("rg"));
                pessoa.setRua(resultado.getString("rua"));
                pessoa.setCPF(resultado.getString("cpf"));

                Calendar data = Calendar.getInstance();
                data.setTime(resultado.getDate("data_nascimento"));
                pessoa.setDtNasc(data);
            }
            resultado.close();
            statement.close();

            return pessoa;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Pessoa> consultarNome(String nome) {
        String sqlPessoa = "SELECT * FROM pessoa "+
                           "WHERE nome LIKE ?";
        
        try {
            List<Pessoa> pessoas = new ArrayList<>();
            PreparedStatement statement = this.connection.prepareStatement(sqlPessoa);
            statement.setString(1, nome + "%");
            
            ResultSet resultado = statement.executeQuery();
            while(resultado.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setNumero(resultado.getInt("numero"));
                pessoa.setSexo(resultado.getString("sexo").charAt(0));
                pessoa.setNome(resultado.getString("nome"));
                pessoa.setCep(resultado.getString("cep"));
                pessoa.setBairro(resultado.getString("bairro"));
                pessoa.setCidade(resultado.getString("cidade"));
                pessoa.setEstado(resultado.getString("estado"));
                pessoa.setComple(resultado.getString("complemento"));
                pessoa.setRG(resultado.getLong("rg"));
                pessoa.setRua(resultado.getString("rua"));
                pessoa.setCPF(resultado.getString("cpf"));

                Calendar data = Calendar.getInstance();
                data.setTime(resultado.getDate("data_nascimento"));
                pessoa.setDtNasc(data);
                
                pessoas.add(pessoa);
            }
            resultado.close();
            statement.close();
            
            return pessoas;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


