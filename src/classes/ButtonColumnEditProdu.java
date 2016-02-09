/*
 * To change this license header, choose License Headers in Projept Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import controller.ControlProduto;
import model.Produto;
import view.Edit_Produ;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author cesar.nascimento
 */
public class ButtonColumnEditProdu extends AbstractCellEditor  
        implements TableCellRenderer, TableCellEditor, ActionListener{  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTable table;  
    JButton renderButton;  
    JButton editButton;  
    String text;  

    public ButtonColumnEditProdu(JTable table, int column){  
        super();  
        this.table = table;  
        ImageIcon image = new ImageIcon(getClass().getResource("/images/settings.png"));
        renderButton = new JButton(image);  


        editButton = new JButton(image);

        editButton.setFocusPainted( false ); 
        editButton.addActionListener( this );  

        TableColumnModel columnModel = table.getColumnModel();  
        columnModel.getColumn(column).setCellRenderer( this );  
        columnModel.getColumn(column).setCellEditor( this );  
    }  

    @Override
    public Component getTableCellRendererComponent(  
        JTable table, Object value, boolean isSelepted, boolean hasFocus, int row, int column){  

        //Aqui fica as ações de animação do botão
        if (hasFocus){  
            renderButton.setForeground(table.getForeground());  
            renderButton.setBackground(UIManager.getColor("Button.background"));  
        }  
        else if (isSelepted){  
            renderButton.setForeground(table.getSelectionForeground());  
             renderButton.setBackground(table.getSelectionBackground());
        }  
        else{  
            renderButton.setForeground(table.getForeground());  
            renderButton.setBackground(UIManager.getColor("Button.background"));  
        }  

        renderButton.setText( (value == null) ? "Editar" : value.toString() );  
        return renderButton;  
    }  

    @Override
    public Component getTableCellEditorComponent(  
        JTable table, Object value, boolean isSelepted, int row, int column){  
        text = (value == null) ? "Editar" : value.toString();  
        editButton.setText( text );  
        return editButton;  
    }  

    @Override
    public Object getCellEditorValue(){  
        return text;  
    }  

    @Override
    public void actionPerformed(ActionEvent e){    
        List<Produto> produtos = new ArrayList<>();
        Edit_Produ ep = new Edit_Produ();
        int row;
        row = table.getSelectedRow();
        int id = Integer.parseInt(String.valueOf(table.getValueAt(row, 1)));
        try {
            ControlProduto cp = new ControlProduto();
            produtos = cp.consultarProduto(id);
        } catch(SQLException er) {
            JOptionPane.showMessageDialog(null, er.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } 
        double preco = produtos.get(0).getPreco();
        double quantidade = produtos.get(0).getQuantidade();
        BigDecimal bd_preco = new BigDecimal(preco).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal bd_quantidade = new BigDecimal(quantidade).setScale(2, RoundingMode.HALF_EVEN);
        ep.jTextID.setText(String.valueOf(produtos.get(0).getId()));
        ep.jTextNome.setText(produtos.get(0).getNome());
        ep.jTextQuantidade.setText(String.valueOf(bd_quantidade.doubleValue()));
        ep.jTextPreco.setText(String.valueOf(bd_preco.doubleValue()));
        ep.jTextDescricao.setText(produtos.get(0).getDescricao().trim());
        ep.setVisible(true);
        ep.setAlwaysOnTop(true);
    }  
}  
