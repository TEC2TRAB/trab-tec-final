/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.ProdutoDao;
import ModuloDeProdutos.Produto;
import java.util.List;

/**
 *
 * @author Esdras
 */
public class ControlProduto {
    private ProdutoDao dao = new ProdutoDao();
    
    public void cadastrarProduto(Produto produto) {
        this.dao.cadastrar(produto);
    }
    
    public List<Produto> consultarProduto(int id) {
        return this.dao.consultar(id);
    }
    
    public List<Produto> consultarProduto(String nome) {
        return this.dao.consultar(nome);
    }
    
    public void atualizarProduto(Produto produto) {
        this.dao.atualizar(produto);
    }
    
    public void retirarProduto(int id, int quantidade) {
        this.dao.retirar(id, quantidade);
    }
}
