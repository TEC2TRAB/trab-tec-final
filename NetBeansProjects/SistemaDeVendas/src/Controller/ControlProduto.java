/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Dao.ProdutoDao;
import Model.Produto;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Esdras
 */
public class ControlProduto {
    private ProdutoDao dao = new ProdutoDao();
    
    public void cadastrarProduto(Produto produto) throws SQLException{
        this.dao.cadastrar(produto);
    }
    
    public List<Produto> consultarProduto(int id) throws SQLException{
        return this.dao.consultar(id);
    }
    
    public List<Produto> consultarProduto(String nome) throws SQLException{
        return this.dao.consultar(nome);
    }
    
    public void atualizarProduto(Produto produto) throws SQLException{
        this.dao.atualizar(produto);
    }
    
    public void retirarProduto(int id, double quantidade) throws SQLException{
        this.dao.retirar(id, quantidade);
    }
    
    public boolean verificarProdutoEstoque(int id, double quantidadeCompra) throws SQLException{
        return this.dao.verificarEstoque(id, quantidadeCompra);
    }
}
