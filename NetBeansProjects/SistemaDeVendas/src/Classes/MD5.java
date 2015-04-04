/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author cesar.nascimento
 */
import java.security.*;
import java.math.*;

public class MD5 {
    private String hash;
    public String geraMD5(String senha){
        try{
            MessageDigest m=MessageDigest.getInstance("MD5");
            m.update(senha.getBytes(),0,senha.length());
            hash=new BigInteger(1,m.digest()).toString(16);
        }catch(NoSuchAlgorithmException e){
            System.out.println("Não foi possível gerar o hash MD5");
        }
        return hash;
    }
}
