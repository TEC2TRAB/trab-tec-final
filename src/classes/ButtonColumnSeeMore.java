/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import controller.ControlVenda;
import model.Venda;
import view.Ver_Venda;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
        /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		JTable table; 
        JTable tableBuy; 
        JButton renderButton;  
        JButton editButton;  
        String text;
  
        public ButtonColumnSeeMore(JTable table, int column){  
            super();  
            this.table = table;  
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
            Venda venda = new Venda();
            Ver_Venda ver = new Ver_Venda();
            DefaultTableModel model = (DefaultTableModel) ver.jTableItens.getModel();
            model.setNumRows(0);
            int row;
            row = table.getSelectedRow();
            int id = Integer.parseInt(String.valueOf(table.getValueAt(row, 4)));
            try {
                ControlVenda cv = new ControlVenda();
                venda = cv.consultarVendaPorId(id);
            } catch(SQLException er) {
                JOptionPane.showMessageDialog(null, er.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }    
            ver.jLabelVendedor.setText(String.valueOf(venda.getIdVendedor()));
            if(venda.getCpfCliente()==0){
               ver.jLabelCliente.setText("");
            }else{
               ver.jLabelCliente.setText(String.valueOf(venda.getCpfCliente())); 
            }
            SimpleDateFormat si = new SimpleDateFormat("dd/MM/yyyy");
            ver.jLabelData.setText(si.format(venda.getDataVenda().getTime()));
            BigDecimal valorTotal = new BigDecimal(venda.getValorTotal()).setScale(2, RoundingMode.HALF_EVEN);
            ver.jLabeltotal.setText("R$ " + String.valueOf(valorTotal.doubleValue()));
            Object vec[] = new Object[3];
            for(int j = 0; j < venda.getItens().size(); j++) {
                vec[0] = venda.getItens().get(j).getIdProduto();
                vec[1] = venda.getItens().get(j).getQuantidade();
                BigDecimal precoItem = new BigDecimal(venda.getItens().get(j).getPreco()).setScale(2, RoundingMode.HALF_EVEN);
                vec[2] = precoItem.doubleValue();
                model.addRow(vec);
            }
            ver.setVisible(true);
            ver.setAlwaysOnTop(true);
        } 
    }  
