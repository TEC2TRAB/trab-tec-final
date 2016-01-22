package model;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Sousa, Italo
 */
public class Venda {
    
    private int id;
    private int idVendedor;
    private long cpfCliente;
    private ArrayList<Itens> itens;
    private double valorTotal;
    private Calendar dataVenda;
    private String hora;
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the idVendedor
     */
    public int getIdVendedor() {
        return idVendedor;
    }

    /**
     * @param idVendedor the idVendedor to set
     */
    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    /**
     * @return the cpfCliente
     */
    public long getCpfCliente() {
        return cpfCliente;
    }

    /**
     * @param cpfCliente the idCliente to set
     */
    public void setCpfCliente(long cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    /**
     * @return the itens
     */
    public ArrayList<Itens> getItens() {
        return itens;
    }

    /**
     * @param itens the historico to set
     */
    public void setItens(ArrayList<Itens> itens) {
        this.itens = itens;
    }

    /**
     * @return the valorTotal
     */
    public double getValorTotal() {
        return valorTotal;
    }

    /**
     * @param valorTotal the valorTotal to set
     */
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    public Calendar getDataVenda() {
        return dataVenda;
    }
    
    public void setDataVenda(Calendar dataVenda) {
        this.dataVenda = dataVenda;
    }
    
    public String getHora() {
        return hora;
    }
    
    public void setHora(String hora) {
        this.hora = hora;
    }
}
