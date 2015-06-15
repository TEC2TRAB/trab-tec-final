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
import Interface.Edit_Func;
import ModuloDePessoas.Funcionario;
import java.util.ArrayList;
import java.util.List;
import Dao.FuncionarioDao;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
;

/**
 *
 * @author cesar.nascimento
 */
public class ButtonColumnEditFunc extends AbstractCellEditor  
        implements TableCellRenderer, TableCellEditor, ActionListener{  
        JTable table;  
        JButton renderButton;  
        JButton editButton;  
        String text;  
  
        public ButtonColumnEditFunc(JTable table, int column){  
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
            fireEditingStopped();   
            List<Funcionario> funcionarios = new ArrayList<>();
            Edit_Func ef = new Edit_Func();
            FuncionarioDao f = new FuncionarioDao();
            int row;char sexo;
            row = table.getSelectedRow();
            long cpf = Long.parseLong(String.valueOf(table.getValueAt(row, 1)));
            funcionarios = f.consultar(cpf);
            ef.jTextNome.setText(funcionarios.get(0).getNome());
            sexo = funcionarios.get(0).getSexo();
            if(sexo=='M'){
                ef.jRadioSexoM.setSelected(true);
            }else{
                ef.jRadioSexoF.setSelected(true);
            }
            Calendar data1 = Calendar.getInstance();
            data1.setTime(funcionarios.get(0).getDtNasc().getTime());
            SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
            ef.jFormattedDtNasc.setText(s.format(data1.getTime()));
            ef.jTextCPF.setText(String.valueOf(table.getValueAt(row, 1)));
            ef.jTextRG.setText(String.valueOf(funcionarios.get(0).getRG()));
            ef.jTextRua.setText(funcionarios.get(0).getRua());
            ef.jTextNumero.setText(String.valueOf(funcionarios.get(0).getNumero()));
            ef.jFormattedCEP.setText(funcionarios.get(0).getCep());
            ef.jComboEstado.setSelectedItem(funcionarios.get(0).getEstado());
            ef.jTextCidade.setText(funcionarios.get(0).getCidade());
            ef.jTextBairro.setText(funcionarios.get(0).getBairro());
            ef.jTextComple.setText(funcionarios.get(0).getComple());
            ef.setVisible(true);
        }  
    }  
