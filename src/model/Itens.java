/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


/**
 *
 * @author Esdras
 */
public class Itens {
    private int idProduto;
    private double quantidade;
    private double preco;
    
    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }
    public int getIdProduto() {
        return idProduto;
    }
    
    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }
    public double getQuantidade() {
        return quantidade;
    }
    
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public double getPreco() {
        return preco;
    }
}
