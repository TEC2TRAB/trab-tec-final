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
            statement.setString(1, Long.toString(funcionario.getCPF()));
            
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
                statement.setString(10, Long.toString(funcionario.getCPF()));
                statement.setLong(11, funcionario.getRG());
                statement.setString(12, funcionario.getRua());

                statement.execute();
            }
            statement.clearParameters();
            statement = this.connection.prepareStatement(sqlFuncionario);

            statement.setString(1, Long.toString(funcionario.getCPF()));
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
    
    public List<Funcionario> consultar(long cpf) {
        String sqlFuncionario = "SELECT * FROM funcionario "+
                                "WHERE cpf = ?";
        
        String sqlPessoa = "SELECT * FROM pessoa "+
                           "WHERE cpf = ?";
        try {
            List<Funcionario> funcionarios = new ArrayList<>();
            PreparedStatement statementFuncionario = this.connection.prepareStatement(sqlFuncionario);
            statementFuncionario.setString(1, Long.toString(cpf));
            
            ResultSet resultadoFuncionario = statementFuncionario.executeQuery();
            while(resultadoFuncionario.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(resultadoFuncionario.getInt("id_funcionario"));
                funcionario.setCPF(Long.parseLong(resultadoFuncionario.getString("cpf")));
                funcionario.setLogin(resultadoFuncionario.getString("login"));
                funcionario.setSenha(resultadoFuncionario.getString("senha"));
                
                Calendar data1 = Calendar.getInstance();
                data1.setTime(resultadoFuncionario.getDate("admissao"));
                funcionario.setAdmissao(data1);
                
                Calendar data2 = Calendar.getInstance();
                data2.setTime(resultadoFuncionario.getDate("demissao"));
                funcionario.setDemissao(data2);
                
                PreparedStatement statementPessoa = this.connection.prepareStatement(sqlPessoa);
                statementPessoa.setString(1, Long.toString(cpf));
                
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
                
                funcionarios.add(funcionario);
                
                resultadoPessoa.close();
                statementPessoa.close();
            }
            resultadoFuncionario.close();
            statementFuncionario.close();
            
            return funcionarios;
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
                funcionario.setCPF(Long.parseLong(resultadoPessoa.getString("cpf")));
                Calendar data1 = Calendar.getInstance();
                data1.setTime(resultadoPessoa.getDate("data_nascimento"));
                funcionario.setDtNasc(data1);
                PreparedStatement statementFuncionario = this.connection.prepareStatement(sqlFuncionario);
                statementFuncionario.setString(1, Long.toString(funcionario.getCPF()));
                
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
    
    public void atualizar(Funcionario funcionario) {
        String sqlPessoa = "UPDATE pessoa SET numero = ?,"+
                           "cep = ?, bairro = ?, cidade = ?," +
                           "estado = ?, complemento = ?, rua = ?" +
                           "WHERE cpf = ?";
        
        String sqlFuncionario = "UPDATE funcionario SET login = ?," +
                                "senha = ?, demissao = ? WHERE cpf = ?";
        
        try {
            PreparedStatement statement = this.connection.prepareStatement(sqlPessoa);
            statement.setInt(1, funcionario.getNumero());
            statement.setString(2, funcionario.getCep());
            statement.setString(3, funcionario.getBairro());
            statement.setString(4, funcionario.getCidade());
            statement.setString(5, funcionario.getEstado());
            statement.setString(6, funcionario.getComple());
            statement.setString(7, funcionario.getRua());
            statement.setString(8, Long.toString(funcionario.getCPF()));
            
            statement.execute();
            statement.clearParameters();
            
            statement = this.connection.prepareStatement(sqlFuncionario);
            statement.setString(1, funcionario.getLogin());
            statement.setString(2, funcionario.getSenha());
            statement.setDate(3, new Date(funcionario.getDemissao().getTimeInMillis()));
            statement.setString(4, Long.toString(funcionario.getCPF()));
            
            statement.execute();
            statement.close();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void remover(Long cpf) {
        String sql = "DELETE FROM funcionario WHERE cpf = ?";
        
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, Long.toString(cpf));
            
            statement.execute();
            statement.close();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public String verificarAcesso(String login, String senha) {
        String sqlLogin = "SELECT login FROM funcionario" +
                          "WHERE login = ?";
        
        String sqlSenha = "SELECT senha FROM funcionario" +
                          "WHERE login = ? AND senha = ?";
        
        try {
            String acesso;
            PreparedStatement statement = this.connection.prepareStatement(sqlLogin);
            statement.setString(1, login);
            
            ResultSet resultado = statement.executeQuery();
            if(!resultado.isBeforeFirst()) {
                resultado.close();
                statement.close();
                return acesso = "Usuário inválido.";
            } else {
                statement.clearParameters();
                statement = this.connection.prepareStatement(sqlSenha);
                statement.setString(1, login);
                statement.setString(2, senha);
                
                resultado = statement.executeQuery();
                if(!resultado.isBeforeFirst()) {
                    resultado.close();
                    statement.close();
                    return acesso = "Senha inválida.";
                }                
            }
            resultado.close();
            statement.close();
            return acesso = "Dados de acesso corretos";
            
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
