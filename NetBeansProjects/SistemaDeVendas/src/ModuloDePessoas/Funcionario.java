package ModuloDePessoas;
/**
 *
 * @author cesar.nascimento
 */
public class Funcionario extends Pessoa{
    private int id;
    private String admissao;
    private String demissao;
    private String login;
    private String senha;
    
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    
    public void setAdmissao(String admissao){
        this.admissao = admissao;
    }
    public String getAdmissao(){
        return admissao;
    }
    
    public void setDemissao(String demissao){
        this.demissao = demissao;
    }
    public String getDemissao(){
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
    
}
