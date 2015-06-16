/*
 * To change this license header, choose License Headers in Project Properties.
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
import Interface.Edit_Clien;
import ModuloDePessoas.Cliente;
import java.util.ArrayList;
import java.util.List;
import Dao.ClienteDao;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author cesar.nascimento
 */
public class ButtonColumnEditClien extends AbstractCellEditor  
        implements TableCellRenderer, TableCellEditor, ActionListener{  
        JTable table;  
        JButton renderButton;  
        JButton editButton;  
        String text;  
  
        public ButtonColumnEditClien(JTable table, int column){  
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
  
            renderButton.setText( (value == null) ? "Editar" : value.toString() );  
            return renderButton;  
        }  
  
        @Override
        public Component getTableCellEditorComponent(  
            JTable table, Object value, boolean isSelected, int row, int column){  
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
            List<Cliente> clientes = new ArrayList<>();
            Edit_Clien ec = new Edit_Clien();
            ClienteDao c = new ClienteDao();
            int row;char sexo;
            row = table.getSelectedRow();
            long cpf = Long.parseLong(String.valueOf(table.getValueAt(row, 1)));
            clientes = c.consultar(cpf);
            ec.jTextNome.setText(clientes.get(0).getNome());
            sexo = clientes.get(0).getSexo();
            if(sexo=='M'){
                ec.jRadioSexoM.setSelected(true);
            }else{
                ec.jRadioSexoF.setSelected(true);
            }
            Calendar data1 = Calendar.getInstance();
            data1.setTime(clientes.get(0).getDtNasc().getTime());
            SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
            ec.jFormattedDtNasc.setText(s.format(data1.getTime()));
            ec.jTextCPF.setText(String.valueOf(table.getValueAt(row, 1)));
            ec.jTextRG.setText(String.valueOf(clientes.get(0).getRG()));
            ec.jTextRua.setText(clientes.get(0).getRua());
            ec.jTextNumero.setText(String.valueOf(clientes.get(0).getNumero()));
            ec.jFormattedCEP.setText(clientes.get(0).getCep());
            ec.jComboEstado.setSelectedItem(clientes.get(0).getEstado());
            ec.jTextCidade.setText(clientes.get(0).getCidade());
            ec.jTextBairro.setText(clientes.get(0).getBairro());
            ec.jTextComple.setText(clientes.get(0).getComple());
            ec.setVisible(true);
            ec.setAlwaysOnTop(true);
        }  
    }  
