package Classes;

/**
 *
 * @author cesar.nascimento
 */
public class Pessoa {
    private String nome;
    private String dtnasc;
    private char sexo;
    private String cpf;
    private int rg;
    private String cep;
    private String telefone;
    private String rua;
    private int numero;
    private String estado;
    private String cidade;
    private String bairro;
    private String comple;
    
    void setNome(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return nome;
    }
    
    void setDtNasc(String dtnasc){
        this.dtnasc = dtnasc;
    }
    public String getDtNasc(){
        return dtnasc;
    }
    
    void setSexo(char sexo){
        this.sexo = sexo;
    }
    public char getSexo(){
        return sexo;
    }
    
    void setCPF(String cpf){
        this.cpf = cpf;
    }
    public String getCPF(){
        return cpf;
    }
    
    void setRG(int rg){
        this.rg = rg;
    }
    public int getRG(){
        return rg;
    }
    
    void setCep(String cep){
        this.cep = cep;
    }
    public String getCep(){
        return cep;
    }
    
    void setTelefone(String telefone){
        this.telefone = telefone;
    }
    public String getTelefone(){
        return telefone;
    }
    
    void setRua(String rua){
        this.rua = rua;
    }
    public String getRua(){
        return rua;
    }
    
    void setNumero(int numero){
        this.numero = numero;
    }
    public int getNumero(){
        return numero;
    }
    
    void setEstado(String estado){
        this.estado = estado;
    }
    public String getEstado(){
        return estado;
    }
    
    void setCidade(String cidade){
        this.cidade = cidade;
    }
    public String getCidade(){
        return cidade;
    }
    
    void setBairro(String bairro){
        this.bairro = bairro;
    }
    public String getBairro(){
        return bairro;
    }
    
    void setComple(String comple){
        this.comple = comple;
    }
    public String getComple(){
        return comple;
    }
}