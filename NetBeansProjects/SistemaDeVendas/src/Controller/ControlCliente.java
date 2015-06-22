/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Dao.ClienteDao;
import Model.Cliente;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Esdras
 */
public class ControlCliente {
    private ClienteDao dao = new ClienteDao();
    
    public void cadastrarCliente(Cliente cliente) throws SQLException {
        this.dao.cadastrar(cliente);
    }
    
    public List<Cliente> consultarCliente(long cpf) throws SQLException{
        return this.dao.consultar(cpf);
    }
    
    public List<Cliente> consultarCliente(String nome) throws SQLException{
        return this.dao.consultar(nome);
    }
    
    public void atualizarCliente(Cliente cliente) throws SQLException{
        this.dao.atualizar(cliente);
    }
}
