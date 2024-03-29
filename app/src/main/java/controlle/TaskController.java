/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlle;

import Util.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;

/**
 *
 * @author yuri-
 */
public class TaskController {
    public void save(Task task){
        
        String sql = "INSERT INTO tasks (idProject"
                +"name,"
                +"description,"
                +"Completed,"
                +"notes,"
                +"deadine,"
                +"createdAt,"
                +"updateAt) VALUES(?,?,?,?,?,?,?,?)";
        
        
        Connection connection = null;
        PreparedStatement statement= null;
        
        
        try{
            
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            statement.setInt(1,task.getIdProject());
            statement.setString(2,task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
             statement.setDate(7,new Date(task.getCreatedAt().getTime()));
             statement.setDate(8,new Date(task.getUpdateAt().getTime()));
             statement.execute();
            
            
            
        }catch(SQLException e){
             throw new RuntimeException("Erro ao salvar a tarefa"+e.getMessage(),e);
        }finally{
            ConnectionFactory.closeConnection(connection,statement);
            //if(statement != null){
                //statement.close();
           /// }
        }
        
    }
    public void update(Task task){
         String sql = "UPDATE  tasks SET (idProject=?"
                +"name=?,"
                +"description=?,"
                +"notes=?,"
                +"Completed=?,"
                +"deadine=?,"
                +"createdAt=?,"
                +"updateAt=?"
                + "WHERE id =?";
        
         
         Connection connection= null;
         PreparedStatement statement=null;
         
         try{
             //Estabelecendo a conex�o com o banco de dados
            connection=ConnectionFactory.getConnection();
            //Preparando a query
            statement=connection.prepareStatement(sql);
            
            //Setando os valores do statement
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getNotes());
            statement.setBoolean(5, task.isIsCompleted());
            statement.setDate(6,new Date(task.getDeadline().getTime()));
            statement.setDate(7,new Date(task.getCreatedAt().getTime()));
            statement.setDate(8,new Date(task.getUpdateAt().getTime()));
            statement.setInt(9, task.getId());
            
            //Executando a query
            statement.execute();
         }catch(SQLException e){
              throw new RuntimeException("Erro ao atualizar a tarefa");
             
         }
        
        
        
    }
    public void removeById(int taskId) throws SQLException{
        String sql="DELETE FROM tasks WHERE id=?";
        
        Connection connection= null;
        PreparedStatement statement=null;
        
        try{
            //Cria��o da conex�o com o bano de dados
           connection=ConnectionFactory.getConnection();
           
           //Preparando a query
           statement = connection.prepareStatement(sql);// um objeto para ajudar a preparar o comando sql q vai ser usado no bd.
           
           //Setando os valores
            statement.setInt(1, taskId);
            
            //Executando a query
            statement.execute();
            
      
        }catch(SQLException e){
            throw new RuntimeException("Erro ao deletar a tarefa");
            
        } finally{
            
         ConnectionFactory.closeConnection(connection,statement);
            
        }
        
    }
    public List<Task> getAll( int idProject){
        
        String sql="SELECT * FROM  tasks WHERE idProject=?";
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet  resultSet =null;                   // a classe que guarda os valores buscados no bd.
        
        //Lista de tarefas que ser� devolvida quando a chamada do m�todo acontecer
        List<Task>tasks = new ArrayList<Task>();
        
        
        try{
            //Cria��o da conex�o
         connection = ConnectionFactory.getConnection();
         statement=connection.prepareStatement(sql);
         
         //Setando o valor que correponde ao filtro de busca
         statement.setInt(1, idProject);
         
         // Valor retornado pela execu��o da query
         resultSet = statement.executeQuery();      //devolve um resultset, que � o valor de resposta da consulta no bd.
          
         //Enquanto houverem valores a serem pecorridos
         while(resultSet.next()){
             Task task= new Task();
            
              task.setId(resultSet.getInt("id"));
              task.setIdProject(resultSet.getInt("idProject"));
              task.setName(resultSet.getString("name"));
              task.setDescription(resultSet.getString("description"));
              task.setNotes(resultSet.getString("notes"));
              task.setIsCompleted(resultSet.getBoolean("Completed"));
              task.setDeadline(resultSet.getDate("deadine"));
              task.setCreatedAt(resultSet.getDate("createdAt"));
              task.setUpdateAt(resultSet.getDate("updatedAt"));
               
               
               task.add(task);
               
         }
        }catch(SQLException ex) {
             throw new RuntimeException("Erro ao inserir a tarefa"+ex.getMessage(),ex);
        } finally{
            ConnectionFactory.closeConnection(connection,statement,resultSet);
        }
        // Lista de tarefas que foi criada e carregada do banco de dados
                return tasks;

    }

}
