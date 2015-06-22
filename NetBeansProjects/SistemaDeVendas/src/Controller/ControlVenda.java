/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.VendaDao;
import ModuloDeVendas.Venda;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Esdras
 */
public class ControlVenda {
    private VendaDao dao = new VendaDao();
    
    public void cadastrarVenda(Venda venda) {
        this.dao.cadastrar(venda);
    }
    
    public List<Venda> consultarVenda(long cpf) {
        return this.dao.consultar(cpf);
    }
    
    public List<Venda> consultarVenda(int id) {
        return this.dao.consultar(id);
    }
    
    public List<Venda> consultarVenda(Date data) {
        return this.dao.consultar(data);
    }
    
}
