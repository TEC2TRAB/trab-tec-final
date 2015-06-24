/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Esdras
 */
public class Sessao {
    private String nome;
    private int id;
    private String login;
    private long cpf;
    private String permissao;
    private Connection connection;
    
    public Sessao() {
        this.connection = new ConnectionFactory().getConnection(); 
    }
    
    public boolean login(String login, String senha) {
        String sql = "SELECT nome, id_funcionario, funcionario.cpf, demissao, permissao FROM pessoa INNER JOIN funcionario " +
                     "ON pessoa.cpf = funcionario.cpf " +
                     "WHERE login = ? AND senha = ?";
        
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, senha);

            ResultSet resultado = statement.executeQuery();
            if(!resultado.isBeforeFirst()) {
                resultado.close();
                statement.close();
                return false;
            }
            while(resultado.next()) {
                if(resultado.getDate("demissao") != null)
                    throw new IllegalArgumentException("O usuário desta conta é um funcionário demitido.");
                setNome(resultado.getString("nome"));
                setId(resultado.getInt("id_funcionario"));
                setLogin(login);
                setCPF(Long.parseLong(resultado.getString("cpf")));
                setPermissao(resultado.getString("permissao"));
            }                
            resultado.close();
            statement.close();
            return true;
            
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public double vendasDia() {
        String sqlVendedor = "SELECT SUM(valor_total) AS valor_total FROM venda " +
                             "WHERE data_venda = ? AND id_vendedor = ?";
        
        String sqlAdm = "SELECT SUM(valor_total) AS valor_total FROM venda " +
                        "WHERE data_venda = ?";
        
        try {
            PreparedStatement statement = null;
            if("Vendedor".equals(getPermissao())) {
                statement = this.connection.prepareStatement(sqlVendedor);
                statement.setDate(1, new Date(System.currentTimeMillis()));
                statement.setInt(2, getId());
            } else if("Administrador".equals(getPermissao())) {
                statement = this.connection.prepareStatement(sqlAdm);
                statement.setDate(1, new Date(System.currentTimeMillis()));
            }
            ResultSet resultado = statement.executeQuery();
            resultado.next();
            double valorTotal = resultado.getDouble("valor_total");
            
            resultado.close();
            statement.close();
            
            return valorTotal;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public double vendasMes() {
        String sqlVendedor = "SELECT SUM(valor_total) AS valor_total FROM venda " +
                             "WHERE EXTRACT(YEAR FROM data_venda) = cast(? as double precision) AND " +
                             "EXTRACT(MONTH FROM data_venda) = cast(? as double precision) AND id_vendedor = ?";
        
        String sqlAdm = "SELECT SUM(valor_total) AS valor_total FROM venda " +
                        "WHERE EXTRACT(YEAR FROM data_venda) = cast(? as double precision) AND " +
                        "EXTRACT(MONTH FROM data_venda) = cast(? as double precision)";
        
        try {
            PreparedStatement statement = null;
            java.util.Date ano = Calendar.getInstance().getTime();
            java.util.Date mes = Calendar.getInstance().getTime();
            if("Vendedor".equals(getPermissao())) {
                statement = this.connection.prepareStatement(sqlVendedor);
                statement.setString(1, new SimpleDateFormat("YYYY").format(ano));
                statement.setString(2, new SimpleDateFormat("MM").format(mes));
                statement.setInt(3, getId());
            } else if("Administrador".equals(getPermissao())) {
                statement = this.connection.prepareStatement(sqlAdm);
                statement.setString(1, new SimpleDateFormat("YYYY").format(ano));
                statement.setString(2, new SimpleDateFormat("MM").format(mes));
            }
            ResultSet resultado = statement.executeQuery();
            resultado.next();
            double valorTotal = resultado.getDouble("valor_total");
            
            resultado.close();
            statement.close();
            
            return valorTotal;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public String[][] ultimasVendas() {
        String sqlVendedor = "SELECT hora, valor_total FROM venda " +
                             "WHERE id_vendedor = ? ORDER BY id_venda DESC LIMIT 10";
        
        String sqlAdm = "SELECT hora, valor_total FROM venda " +
                        "ORDER BY id_venda DESC LIMIT 10";
        
        try {
            String[][] ultimasVendas = new String[10][2];
            PreparedStatement statement = null;
            if("Vendedor".equals(getPermissao())) {
                statement = this.connection.prepareStatement(sqlVendedor);
                statement.setInt(1, getId());
            } else if("Administrador".equals(getPermissao()))
                statement = this.connection.prepareStatement(sqlAdm);
            
            ResultSet resultado = statement.executeQuery();
            while(resultado.next()) {
                ultimasVendas[resultado.getRow() - 1][0] = resultado.getString("hora");
                ultimasVendas[resultado.getRow() - 1][1] = Double.toString(resultado.getDouble("valor_total"));
            }
            
            resultado.close();
            statement.close();
            
            return ultimasVendas;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    
    public long getCPF() {
        return cpf;
    }
    public void setCPF(long cpf) {
        this.cpf = cpf;
    }
    
    public String getPermissao() {
        return permissao;
    }
    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }
    
}
