/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Testando GitHub.
package sysvendas;

import Interface.Menu;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author cesar.nascimento
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try{
            //Setar estilo que eu quero,ex: Trocar Nimbus por Windows,ou Metal no if
            for ( LookAndFeelInfo info : UIManager.getInstalledLookAndFeels() ) {  
                if ( "Windows".equals( info.getName() ) ) {  
                    UIManager.setLookAndFeel( info.getClassName() );  
                    break;  
                }  
            }
            //ver os estilos que eu tenho instalado no projeto
            for ( UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels() ) {  
                System.out.println( info.getName() );  
            } 
        } catch(UnsupportedLookAndFeelException | ClassNotFoundException | 
                InstantiationException | IllegalAccessException e){
            System.out.println("Não foi possível setar o estilo,contate o administrador");
        }
        Menu m = new Menu();
        m.setVisible(true); 
    }
}
