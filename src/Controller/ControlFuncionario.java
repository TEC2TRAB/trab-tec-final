/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Dao.FuncionarioDao;
import Model.Funcionario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Esdras
 */
public class ControlFuncionario {
    private FuncionarioDao dao = new FuncionarioDao();
    
    public void cadastrarFuncionario(Funcionario funcionario) throws SQLException{
        this.dao.cadastrar(funcionario);
    }
    
    public List<Funcionario> consultarFuncionario(long cpf) throws SQLException{
        return this.dao.consultar(cpf);
    }
    
    public List<Funcionario> consultarFuncionario(String nome) throws SQLException{
        return this.dao.consultar(nome);
    }
    
    public void atualizarFuncionario(Funcionario funcionario) throws SQLException{
        this.dao.atualizar(funcionario);
    }
    
    public void atualizarFuncionario(String login, String senha, long cpf) throws SQLException{
        this.dao.atualizar(login, senha, cpf);
    }
    
    public boolean verificarSenhaFuncionario(String login, String senha) throws SQLException{
        return this.dao.verificarSenha(login, senha);
    }
}
