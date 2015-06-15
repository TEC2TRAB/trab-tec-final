/*
 * To change this license header, choose License Headers in Projept Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

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
import Interface.Edit_Produ;
import ModuloDeProdutos.Produto;
import java.util.ArrayList;
import java.util.List;
import Dao.ProdutoDao;

/**
 *
 * @author cesar.nascimento
 */
public class ButtonColumnEditProdu extends AbstractCellEditor  
        implements TableCellRenderer, TableCellEditor, ActionListener{  
        JTable table;  
        JButton renderButton;  
        JButton editButton;  
        String text;  
  
        public ButtonColumnEditProdu(JTable table, int column){  
            super();  
            this.table = table;  
            ImageIcon image = new ImageIcon(getClass().getResource("images/settings.png"));
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
            fireEditingStopped();   
            List<Produto> produtos = new ArrayList<>();
            Edit_Produ ep = new Edit_Produ();
            ProdutoDao p = new ProdutoDao();
            int row;
            row = table.getSelectedRow();
            int id = Integer.parseInt(String.valueOf(table.getValueAt(row, 1)));
            produtos = p.consultar(id);
            ep.jTextID.setText(String.valueOf(produtos.get(0).getId()));
            ep.jTextNome.setText(produtos.get(0).getNome());
            ep.jTextQuantidade.setText(String.valueOf(produtos.get(0).getQuantidade()));
            ep.jTextPreco.setText(String.valueOf(produtos.get(0).getPreco()));
            ep.jTextDescricao.setText(produtos.get(0).getDescricao().trim());
            ep.setVisible(true);
        }  
    }  
