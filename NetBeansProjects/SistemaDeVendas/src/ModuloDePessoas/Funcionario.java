package ModuloDePessoas;

import java.util.Calendar;

/**
 *
 * @author cesar.nascimento
 */
public class Funcionario extends Pessoa{
    private int id;
    private Calendar admissao;
    private Calendar demissao = null;
    private String login;
    private String senha;
    private String permissao;
    
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    
    public void setAdmissao(Calendar admissao){
        this.admissao = admissao;
    }
    public Calendar getAdmissao(){
        return admissao;
    }
    
    public void setDemissao(Calendar demissao){
        this.demissao = demissao;
    }
    public Calendar getDemissao(){
        return demissao;
    }
    
    public void setLogin(String login){
        this.login = login;
    }
    public String getLogin(){
        return login;
    }
    
    public void setSenha(String senha){
        this.senha = senha;
    }
    public String getSenha(){
        return senha;
    }
    
    public void setPermissao(String permissao){
        this.permissao = permissao;
    }
    public String getPermissao(){
        return permissao;
    }
    
}
