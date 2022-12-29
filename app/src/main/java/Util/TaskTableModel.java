/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

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
    public Object getValueAt(int rowIndex, int columnIndex) { // valor que deve ser exibido em uma determinada linha e coluna.
        return switch (columnIndex) {
            case 1 -> tasks.get(rowIndex).getName();
            case 2 -> tasks.get(rowIndex).getDescription();
            case 3 -> tasks.get(rowIndex).getDeadline();
            case 4 -> tasks.get(rowIndex).isIsCompleted();
            case 5 -> "";
            case 6 -> "";
            default -> "Dados não encontrados";
        }; // método for chamado vai receber qual linha e qual coluna vai sair essa informação.
    }

    public String[] getColumns() {
        return columns;
    }

    

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    

}
