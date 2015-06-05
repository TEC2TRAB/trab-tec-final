/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Classes.ConnectionFactory;
import ModuloDeVendas.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Esdras
 */
public class VendaDao {
    private Connection connection;
    
    public VendaDao() {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void cadastrar(Venda venda) {
        String sqlVenda = "INSERT INTO venda " +
                          "(id_vendedor, id_cliente, valor_total) " +
                          "VALUES (?,?,?)";
        
        String sqlId = "SELECT MAX(id_venda) AS id_venda FROM venda";
        
        String sqlHistorico = "INSERT INTO historico_venda " +
                              "(id_venda, id_produto, quantidade) " +
                              "VALUES (?,?,?)";
        
        try {
            //Inserindo na tabela venda.
            PreparedStatement statement = this.connection.prepareStatement(sqlVenda);
            statement.setInt(1, venda.getIdVendedor());
            statement.setInt(2, venda.getIdCliente());
            statement.setDouble(3, venda.getValorTotal());
            
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
                 
                 statement.execute();
            }
            statement.close();
            
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
