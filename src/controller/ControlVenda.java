/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import model.Venda;
import model.dao.VendaDao;

/**
 *
 * @author Esdras
 */
public class ControlVenda {
    private VendaDao dao = new VendaDao();
    
    public void cadastrarVenda(Venda venda) throws SQLException{
        this.dao.cadastrar(venda);
    }
    
    public List<Venda> consultarVenda(long cpf) throws SQLException{
        return this.dao.consultar(cpf);
    }
    
    public List<Venda> consultarVenda(int id) throws SQLException{
        return this.dao.consultar(id);
    }
    
    public List<Venda> consultarVenda(Date data) throws SQLException{
        return this.dao.consultar(data);
    }
    
}
