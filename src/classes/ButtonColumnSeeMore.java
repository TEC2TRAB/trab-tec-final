/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import controller.ControlVenda;
import model.Itens;
import model.Venda;
import view.Ver_Venda;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
public class ButtonColumnSeeMore extends AbstractCellEditor  
        implements TableCellRenderer, TableCellEditor, ActionListener{  
        JTable table; 
        JTable tableBuy; 
        JButton renderButton;  
        JButton editButton;  
        String text;
  
        public ButtonColumnSeeMore(JTable table, int column){  
            super();  
            this.table = table;  
            this.tableBuy = tableBuy;
            ImageIcon image = new ImageIcon(getClass().getResource("/images/find.png"));
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
  
            renderButton.setText( (value == null) ? "Ver" : value.toString() );  
            return renderButton;  
        }  
  
        @Override
        public Component getTableCellEditorComponent(  
            JTable table, Object value, boolean isSelected, int row, int column){  
            text = (value == null) ? "Ver" : value.toString();  
            editButton.setText( text );  
            return editButton;  
        }  
  
        @Override
        public Object getCellEditorValue(){  
            return text;  
        }  
  
        @Override
        public void actionPerformed(ActionEvent e){  
            List<Venda> venda = new ArrayList<>();
            Ver_Venda ver = new Ver_Venda();
            DefaultTableModel model = (DefaultTableModel) ver.jTableItens.getModel();
            model.setNumRows(0);
            int row;
            row = table.getSelectedRow();
            int id = Integer.parseInt(String.valueOf(table.getValueAt(row, 0)));
            try {
                ControlVenda cv = new ControlVenda();
                venda = cv.consultarVenda(id);
            } catch(SQLException er) {
                JOptionPane.showMessageDialog(null, er.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }    
            ver.jLabelVendedor.setText(String.valueOf(venda.get(0).getIdVendedor()));
            if(venda.get(0).getCpfCliente()==0){
               ver.jLabelCliente.setText("");
            }else{
               ver.jLabelCliente.setText(String.valueOf(venda.get(0).getCpfCliente())); 
            }
            SimpleDateFormat si = new SimpleDateFormat("dd/MM/yyyy");
            ver.jLabelData.setText(si.format(venda.get(table.getSelectedRow()).getDataVenda().getTime()));
            ver.jLabeltotal.setText("R$ " + String.valueOf(venda.get(table.getSelectedRow()).getValorTotal()));
            Object vec[] = new Object[3];
            for(int j = 0; j < venda.get(table.getSelectedRow()).getItens().size(); j++) {
                vec[0] = venda.get(table.getSelectedRow()).getItens().get(j).getIdProduto();
                vec[1] = venda.get(table.getSelectedRow()).getItens().get(j).getQuantidade();
                vec[2] = venda.get(table.getSelectedRow()).getItens().get(j).getPreco();
                model.addRow(vec);
            }
            ver.setVisible(true);
            ver.setAlwaysOnTop(true);
        } 
    }  
