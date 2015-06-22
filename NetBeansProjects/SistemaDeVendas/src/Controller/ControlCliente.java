/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.ClienteDao;
import ModuloDePessoas.Cliente;
import java.util.List;

/**
 *
 * @author Esdras
 */
public class ControlCliente {
    private ClienteDao dao = new ClienteDao();
    
    public void cadastrarCliente(Cliente cliente) {
        this.dao.cadastrar(cliente);
    }
    
    public List<Cliente> consultarCliente(long cpf) {
        return this.dao.consultar(cpf);
    }
    
    public List<Cliente> consultarCliente(String nome) {
        return this.dao.consultar(nome);
    }
    
    public void atualizarCliente(Cliente cliente) {
        this.dao.atualizar(cliente);
    }
}
