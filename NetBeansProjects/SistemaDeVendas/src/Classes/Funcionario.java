package Classes;

/**
 *
 * @author cesar.nascimento
 */
public class Funcionario extends Pessoa{
    private int id;
    private String admissao;
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return this.id;
    }
    
    public void setAdmissao(String admissao){
        this.admissao = admissao;
    }
    public String getAdmissao(){
        return admissao;
    }
    
}
