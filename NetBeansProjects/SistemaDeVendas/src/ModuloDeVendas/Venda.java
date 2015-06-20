package ModuloDeVendas;

import java.util.Calendar;

/**
 *
 * @author Sousa, Italo
 */
public class Venda {
    
    private int id;
    private int idVendedor;
    private int idCliente;
    private double[][] historico;
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
     * @return the idCliente
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return the historico
     */
    public double[][] getHistorico() {
        return historico;
    }

    /**
     * @param historico the historico to set
     */
    public void setHistorico(double[][] historico) {
        this.historico = historico;
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
