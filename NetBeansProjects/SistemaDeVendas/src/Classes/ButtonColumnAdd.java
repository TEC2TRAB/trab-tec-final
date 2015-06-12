/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import ModuloDeProdutos.Produto;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author cesar.nascimento
 */
public class ButtonColumnAdd extends AbstractCellEditor  
        implements TableCellRenderer, TableCellEditor, ActionListener{  
        JTable table; 
        JTable tableBuy; 
        JButton renderButton;  
        JButton editButton;  
        String text; 
        List<Produto> p = new ArrayList<>();
  
        public ButtonColumnAdd(JTable table, int column, List<Produto> p, JTable tableBuy){  
            super();  
            this.table = table;  
            this.tableBuy = tableBuy;
            ImageIcon image = new ImageIcon(getClass().getResource("/images/add.png"));
            renderButton = new JButton(image);  
            this.p = p;
            
            editButton = new JButton(image);

            editButton.setFocusPainted( false ); 
            editButton.addActionListener( this );  
  
            TableColumnModel columnModel = table.getColumnModel();  
            columnModel.getColumn(column).setCellRenderer( this );  
            columnModel.getColumn(column).setCellEditor( this );  
        }  
  
        @Override
        public Component getTableCellRendererComponent(  
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){  
            
            //Aqui fica as ações de animação do botão
            if (hasFocus){  
                renderButton.setForeground(table.getForeground());  
                renderButton.setBackground(UIManager.getColor("Button.background"));  
            }  
            else if (isSelected){  
                renderButton.setForeground(table.getSelectionForeground());  
                 renderButton.setBackground(table.getSelectionBackground());
            }  
            else{  
                renderButton.setForeground(table.getForeground());  
                renderButton.setBackground(UIManager.getColor("Button.background"));  
            }  
  
            renderButton.setText( (value == null) ? "Adicionar" : value.toString() );  
            return renderButton;  
        }  
  
        @Override
        public Component getTableCellEditorComponent(  
            JTable table, Object value, boolean isSelected, int row, int column){  
            text = (value == null) ? "Adicionar" : value.toString();  
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
            DefaultTableModel model = (DefaultTableModel) tableBuy.getModel();
            int row;
            row = table.getSelectedRow();
            double quantidade = (double) table.getValueAt(row, 3);
            Object vec[] = new Object[4];
            vec[0] = table.getValueAt(row, 0);
            vec[1] = table.getValueAt(row, 1);
            vec[2] = table.getValueAt(row, 2);
            vec[3] = table.getValueAt(row, 3);
            if(quantidade==0){
                JOptionPane.showMessageDialog(null, "Você não pode adicionar itens com quantidade 0.", "Alerta", JOptionPane.WARNING_MESSAGE);
            }else{
                model.addRow(vec);
            }
        } 
    }  
