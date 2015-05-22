/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Classes.ConnectionFactory;
import ModuloDePessoas.Funcionario;
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
public class FuncionarioDao {
    private Connection connection;
    
    public FuncionarioDao() {
        this.connection = new ConnectionFactory().getConnection(); 
    }
    
    public void cadastrar(Funcionario funcionario) {
        String sqlPessoa = "INSERT INTO pessoa " +
                           "(numero,data_nascimento,sexo,nome,cep,bairro,cidade," +
                           "estado,complemento,cpf,rg,rua)" +
                           "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        
        String sqlFuncionario = "INSERT INTO funcionario " +
                                "(cpf,admissao,demissao,login,senha)" +
                                "VALUES (?,?,?,?,?)";
        
        String sqlChecar = "SELECT * FROM pessoa WHERE cpf = ?";
        
        try {
            PreparedStatement statement = this.connection.prepareStatement(sqlChecar);
            statement.setString(1, funcionario.getCPF());
            
            ResultSet resultadoChecar = statement.executeQuery();
            
            if(!resultadoChecar.isBeforeFirst()) {
                statement.clearParameters();
                statement = this.connection.prepareStatement(sqlPessoa);

                statement.setInt(1, funcionario.getNumero());
                statement.setDate(2, new Date(funcionario.getDtNasc().getTimeInMillis()));
                statement.setString(3, String.valueOf(funcionario.getSexo()));
                statement.setString(4, funcionario.getNome());
                statement.setString(5, funcionario.getCep());
                statement.setString(6, funcionario.getBairro());
                statement.setString(7, funcionario.getCidade());
                statement.setString(8, funcionario.getEstado());
                statement.setString(9, funcionario.getComple());
                statement.setString(10, funcionario.getCPF());
                statement.setLong(11, funcionario.getRG());
                statement.setString(12, funcionario.getRua());

                statement.execute();
            }
            statement.clearParameters();
            statement = this.connection.prepareStatement(sqlFuncionario);

            statement.setString(1, funcionario.getCPF());
            statement.setDate(2, new Date(System.currentTimeMillis()));
            statement.setNull(3, java.sql.Types.DATE);
            statement.setString(4, funcionario.getLogin());
            statement.setString(5, funcionario.getSenha());

            statement.execute();
            statement.close();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Este CPF já foi cadastrado no nome de outra pessoa, verifique ou contate "
                    + "o administrador do sistema.", "Alerta", JOptionPane.WARNING_MESSAGE);
            throw new RuntimeException(e);
        }
    }
    
    public Funcionario consultarCPF(String cpf) {
        String sqlFuncionario = "SELECT * FROM funcionario "+
                                "WHERE cpf = ?";
        
        String sqlPessoa = "SELECT * FROM pessoa "+
                           "WHERE cpf = ?";
        
        try {
            PreparedStatement statementFuncionario = this.connection.prepareStatement(sqlFuncionario);
            statementFuncionario.setString(1, cpf);
            
            ResultSet resultadoFuncionario = statementFuncionario.executeQuery();
            Funcionario funcionario = new Funcionario();
            while(resultadoFuncionario.next()) {
                funcionario.setId(resultadoFuncionario.getInt("id_funcionario"));
                funcionario.setCPF(resultadoFuncionario.getString("cpf"));
                funcionario.setLogin(resultadoFuncionario.getString("login"));
                funcionario.setSenha(resultadoFuncionario.getString("senha"));
                
                Calendar data1 = Calendar.getInstance();
                data1.setTime(resultadoFuncionario.getDate("admissao"));
                funcionario.setAdmissao(data1);
                
                Calendar data2 = Calendar.getInstance();
                data2.setTime(resultadoFuncionario.getDate("demissao"));
                funcionario.setDemissao(data2);
                
                PreparedStatement statementPessoa = this.connection.prepareStatement(sqlPessoa);
                statementPessoa.setString(1, funcionario.getCPF());
                
                ResultSet resultadoPessoa = statementPessoa.executeQuery();
                while(resultadoPessoa.next()) {
                    funcionario.setNumero(resultadoPessoa.getInt("numero"));
                    funcionario.setSexo(resultadoPessoa.getString("sexo").charAt(0));
                    funcionario.setNome(resultadoPessoa.getString("nome"));
                    funcionario.setCep(resultadoPessoa.getString("cep"));
                    funcionario.setBairro(resultadoPessoa.getString("bairro"));
                    funcionario.setCidade(resultadoPessoa.getString("cidade"));
                    funcionario.setEstado(resultadoPessoa.getString("estado"));
                    funcionario.setComple(resultadoPessoa.getString("complemento"));
                    funcionario.setRG(resultadoPessoa.getLong("rg"));
                    funcionario.setRua(resultadoPessoa.getString("rua"));
                    
                    Calendar data3 = Calendar.getInstance();
                    data3.setTime(resultadoPessoa.getDate("data_nascimento"));
                    funcionario.setDtNasc(data3);
                }
                
                resultadoPessoa.close();
                statementPessoa.close();
            }
            resultadoFuncionario.close();
            statementFuncionario.close();
            
            return funcionario;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Funcionario> consultar(String nome) {
        String sqlFuncionario = "SELECT * FROM funcionario "+
                                "WHERE cpf = ?";
        
        String sqlPessoa = "SELECT * FROM pessoa "+
                           "WHERE nome LIKE ?";
        
        try {
            List<Funcionario> funcionarios = new ArrayList<>();
            PreparedStatement statementPessoa = this.connection.prepareStatement(sqlPessoa);
            statementPessoa.setString(1, nome + "%");
            
            ResultSet resultadoPessoa = statementPessoa.executeQuery();
            while(resultadoPessoa.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setNumero(resultadoPessoa.getInt("numero"));
                funcionario.setSexo(resultadoPessoa.getString("sexo").charAt(0));
                funcionario.setNome(resultadoPessoa.getString("nome"));
                funcionario.setCep(resultadoPessoa.getString("cep"));
                funcionario.setBairro(resultadoPessoa.getString("bairro"));
                funcionario.setCidade(resultadoPessoa.getString("cidade"));
                funcionario.setEstado(resultadoPessoa.getString("estado"));
                funcionario.setComple(resultadoPessoa.getString("complemento"));
                funcionario.setRG(resultadoPessoa.getLong("rg"));
                funcionario.setRua(resultadoPessoa.getString("rua"));
                funcionario.setCPF(resultadoPessoa.getString("cpf"));

                Calendar data1 = Calendar.getInstance();
                data1.setTime(resultadoPessoa.getDate("data_nascimento"));
                funcionario.setDtNasc(data1);
                
                PreparedStatement statementFuncionario = this.connection.prepareStatement(sqlFuncionario);
                statementFuncionario.setString(1, funcionario.getCPF());
                
                ResultSet resultadoFuncionario = statementFuncionario.executeQuery();
                while(resultadoFuncionario.next()) {
                    funcionario.setId(resultadoFuncionario.getInt("id_funcionario"));
                    funcionario.setLogin(resultadoFuncionario.getString("login"));
                    funcionario.setSenha(resultadoFuncionario.getString("senha"));
                    
                    Calendar data2 = Calendar.getInstance();
                    data2.setTime(resultadoFuncionario.getDate("admissao"));
                    funcionario.setAdmissao(data2);
                    
                    Calendar data3 = Calendar.getInstance();
                    data3.setTime(resultadoFuncionario.getDate("demissao"));
                    funcionario.setDemissao(data3);
                }
                
                funcionarios.add(funcionario);
                
                resultadoFuncionario.close();
                statementFuncionario.close();
            }
            resultadoPessoa.close();
            statementPessoa.close();
            
            return funcionarios;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
