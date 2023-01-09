/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Task;

/**
 *
 * @author yuri-
 */
public class TaskTableModel extends AbstractTableModel {// implementação padrão de model para table

    String[] columns = {"Nome", "Descrição", "Prazo", "Tarefa Concluída", "Editar", "Excluir"};

    List<Task> tasks = new ArrayList<>();

    @Override
    public int getRowCount() { //quantidade de tarefas,quantas linhas vão ser mostradas.
        return tasks.size();
    }

    @Override
    public int getColumnCount() { /// quantas colunas
        return columns.length;
    }
    
     @Override
     public String getColumnName(int columnIndex){
         return columns[columnIndex];
     }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) { // valor que deve ser exibido em uma determinada linha e coluna.
         SimpleDateFormat dateFormat =  new SimpleDateFormat("dd/MM/yyyy");
        return switch (columnIndex) {
            
            case 0 -> tasks.get(rowIndex).getName();
                
            case 1 -> tasks.get(rowIndex).getDescription();
               
   
            case 2 -> dateFormat.format(tasks.get(rowIndex).getDeadline());
                
            case 3 -> tasks.get(rowIndex).isIsCompleted();
                
            case 4 -> "";
                
            case 5 -> "";
                
            default -> "Dados não encontrados";
        }; // método for chamado vai receber qual linha e qual coluna vai sair essa informação.
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex , int columnIndex){
        tasks.get(rowIndex).setIsCompleted((boolean)aValue); //Quando o TaskTableModel for chamado essa função vai retornar receber o valor que for passado pelo setIsCompleted, que vai ser true ou false.
        // Object o componente gráfico pega ele tipo object, mas ele é convertido para um tipo boolean
        
        
        
        
        
        
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    public String[] getColumns() {
        return columns;
    }

     public boolean isCelleditable(int rowIndex, int columnIndex){ // o componente visual pergunta para o model, se o componente é editavel ou n.
         //return true;// todos os componentes seriam editáveis.
         return columnIndex == 3;
         //if(columnIndex==3)
            // return true;
             
            // else
             
            // return false;
                     
                     }
         @Override 
         public  Class<?> getColumnClass(int columnIndex){ // qual a classe do componente que está em uma determinada coluna, se é um texto, um boolean, na classe Pai ele é uma String.
            if(tasks.isEmpty()){ // verificar se a lista de tarefas é vazia.
                return Object.class;
            } 
            
            return this.getValueAt(0, columnIndex).getClass();// pegar a classe da linha 0
         }
    

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    

}
