/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Dao;

import Classes.ConnectionFactory;
import Model.Venda;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Esdras
 */
public class VendaDao {
    private Connection connection;
    
    public VendaDao() {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void cadastrar(Venda venda) throws SQLException{
        String sqlVenda = "INSERT INTO venda " +
                          "(id_vendedor, id_cliente, valor_total, data_venda, hora) " +
                          "VALUES (?,?,?,?,?)";
        
        String sqlId = "SELECT MAX(id_venda) AS id_venda FROM venda";
        
        String sqlHistorico = "INSERT INTO historico_venda " +
                              "(id_venda, id_produto, quantidade, preco) " +
                              "VALUES (?,?,?,?)";
        
        
        //Inserindo na tabela venda.
        PreparedStatement statement = this.connection.prepareStatement(sqlVenda);
        statement.setInt(1, venda.getIdVendedor());

        if(venda.getIdCliente() == -1)     
            statement.setNull(2, java.sql.Types.NULL);
        else
            statement.setInt(2, venda.getIdCliente());

        statement.setDouble(3, venda.getValorTotal());
        statement.setDate(4, new Date(System.currentTimeMillis()));

        java.util.Date hora = Calendar.getInstance().getTime();
        statement.setString(5, new SimpleDateFormat("HH:mm").format(hora));

        statement.execute();
        statement.clearParameters();

        //Obtendo id_venda do banco de dados.
        statement = this.connection.prepareStatement(sqlId);
        ResultSet resultado = statement.executeQuery();
        resultado.next();
        venda.setId(resultado.getInt("id_venda"));
        statement.clearParameters();

        //Inserindo os elementos da matriz historico no banco de dados.
        statement = this.connection.prepareStatement(sqlHistorico);
        double[][] historico = venda.getHistorico();
        for(int i = 0; i < historico.length; i++) {
             statement.setInt(1, venda.getId());
             statement.setInt(2, (int)historico[i][0]);
             statement.setDouble(3, historico[i][1]);
             statement.setDouble(4, historico[i][2]);

             statement.execute();
        }
        statement.close();
           
    }
    
    public List<Venda> consultar(long cpf) throws SQLException{
        String sqlVenda = "SELECT * FROM venda " +
                          "WHERE id_cliente = (SELECT id_cliente FROM cliente WHERE cpf = ?)";
        
        String sqlHistorico = "SELECT * FROM historico_venda " +
                              "WHERE id_venda = (SELECT id_venda FROM venda WHERE id_cliente = " +
                              "(SELECT id_cliente FROM cliente WHERE cpf = ?))";
        
        
        List<Venda> vendas = new ArrayList<>();
        PreparedStatement statementVenda = this.connection.prepareStatement(sqlVenda);
        statementVenda.setString(1, Long.toString(cpf));

        ResultSet resultadoVenda = statementVenda.executeQuery();
        while(resultadoVenda.next()) {
            Venda venda = new Venda();
            venda.setId(resultadoVenda.getInt("id_venda"));
            venda.setIdCliente(resultadoVenda.getInt("id_cliente"));
            venda.setIdVendedor(resultadoVenda.getInt("id_vendedor"));
            venda.setValorTotal(resultadoVenda.getDouble("valor_total"));
            venda.setHora(resultadoVenda.getString("hora"));

            Calendar data = Calendar.getInstance();
            data.setTime(resultadoVenda.getDate("data_venda"));
            venda.setDataVenda(data);

            PreparedStatement statementHistorico = this.connection.prepareStatement(sqlHistorico);
            statementHistorico.setString(1, Long.toString(cpf));

            ResultSet resultadoHistorico = statementHistorico.executeQuery();
            double[][] historico = null;
            while(resultadoHistorico.next()) {
                historico[resultadoHistorico.getRow() - 1][0] = resultadoHistorico.getInt("id_produto");
                historico[resultadoHistorico.getRow() - 1][1] = resultadoHistorico.getDouble("quantidade");
            }
            venda.setHistorico(historico);

            vendas.add(venda);
            resultadoHistorico.close();
            statementHistorico.close();
        }
        resultadoVenda.close();
        statementVenda.close();

        return vendas;   
    }
    
    public List<Venda> consultar(int id) throws SQLException{
        String sqlVenda = "SELECT * FROM venda " +
                          "WHERE id_vendedor = ?";
        
        String sqlHistorico = "SELECT * FROM historico_venda " +
                              "WHERE id_venda = (SELECT id_venda FROM venda WHERE id_vendedor = ?)";
        
        
        List<Venda> vendas = new ArrayList<>();
        PreparedStatement statementVenda = this.connection.prepareStatement(sqlVenda);
        statementVenda.setInt(1, id);

        ResultSet resultadoVenda = statementVenda.executeQuery();
        while(resultadoVenda.next()) {
            Venda venda = new Venda();
            venda.setId(resultadoVenda.getInt("id_venda"));
            venda.setIdCliente(resultadoVenda.getInt("id_cliente"));
            venda.setIdVendedor(resultadoVenda.getInt("id_vendedor"));
            venda.setValorTotal(resultadoVenda.getDouble("valor_total"));

            Calendar data = Calendar.getInstance();
            data.setTime(resultadoVenda.getDate("data_venda"));
            venda.setDataVenda(data);

            PreparedStatement statementHistorico = this.connection.prepareStatement(sqlHistorico);
            statementHistorico.setInt(1, id);

            ResultSet resultadoHistorico = statementHistorico.executeQuery();
            double[][] historico = null;
            while(resultadoHistorico.next()) {
                historico[resultadoHistorico.getRow() - 1][0] = resultadoHistorico.getInt("id_produto");
                historico[resultadoHistorico.getRow() - 1][1] = resultadoHistorico.getDouble("quantidade");
            }
            venda.setHistorico(historico);

            vendas.add(venda);
            resultadoHistorico.close();
            statementHistorico.close();
        }
        resultadoVenda.close();
        statementVenda.close();

        return vendas;    
    }
    
    public List<Venda> consultar(Date data) throws SQLException{
        String sqlVenda = "SELECT * FROM venda " +
                          "WHERE data_venda = ?";
        
        String sqlHistorico = "SELECT * FROM historico_venda " +
                              "WHERE id_venda = (SELECT id_venda FROM venda WHERE data_venda = ?)";
        
        
        List<Venda> vendas = new ArrayList<>();
        PreparedStatement statementVenda = this.connection.prepareStatement(sqlVenda);
        statementVenda.setDate(1, data);

        ResultSet resultadoVenda = statementVenda.executeQuery();
        while(resultadoVenda.next()) {
            Venda venda = new Venda();
            venda.setId(resultadoVenda.getInt("id_venda"));
            venda.setIdCliente(resultadoVenda.getInt("id_cliente"));
            venda.setIdVendedor(resultadoVenda.getInt("id_vendedor"));
            venda.setValorTotal(resultadoVenda.getDouble("valor_total"));

            Calendar dataResult = Calendar.getInstance();
            dataResult.setTime(resultadoVenda.getDate("data_venda"));
            venda.setDataVenda(dataResult);

            PreparedStatement statementHistorico = this.connection.prepareStatement(sqlHistorico);
            statementHistorico.setDate(1, data);

            ResultSet resultadoHistorico = statementHistorico.executeQuery();
            double[][] historico = null;
            while(resultadoHistorico.next()) {
                historico[resultadoHistorico.getRow() - 1][0] = resultadoHistorico.getInt("id_produto");
                historico[resultadoHistorico.getRow() - 1][1] = resultadoHistorico.getDouble("quantidade");
            }
            venda.setHistorico(historico);

            vendas.add(venda);
            resultadoHistorico.close();
            statementHistorico.close();
        }
        resultadoVenda.close();
        statementVenda.close();

        return vendas;    
    }
}
