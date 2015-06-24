package Model;

import java.util.Calendar;

/**
 *
 * @author Sousa, Italo.
 */
public class Cliente extends Pessoa{
    private int id;
    private Calendar dataDeCadastro;
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
     * @return the dataDeCadastro
     */
    public Calendar getDataDeCadastro() {
        return dataDeCadastro;
    }

    /**
     * @param dataDeCadastro the dataDeCadastro to set
     */
    public void setDataDeCadastro(Calendar dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }
    
}
