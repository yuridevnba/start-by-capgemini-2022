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
                +"completed,"
                +"notes,"
                +"deadline,"
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
            
            
            
        }catch(Exception e){
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
                +"completed=?,"
                +"deadline=?,"
                +"createdAt=?,"
                +"updateAt=?"
                + "WHERE id =?";
        
         
         Connection connection= null;
         PreparedStatement statement=null;
         
         try{
            connection=ConnectionFactory.getConnection();
            statement=connection.prepareStatement(sql);
            
            statement.setInt(1, task.getIdProject());
            statement.setString(1, task.getName());
            statement.setString(1, task.getDescription());
            statement.setString(1, task.getNotes());
            statement.setBoolean(1, task.isIsCompleted());
            statement.setDate(1,new Date(task.getDeadline().getTime()));
            statement.setDate(1,new Date(task.getCreatedAt().getTime()));
            statement.setDate(1,new Date(task.getUpdateAt().getTime()));
            statement.execute();
         }catch(Exception e){
             
         }
        
        
        
    }
    public void removeById(int taskId) throws SQLException{
        String sql="DELETE FROM tasks WHERE id=?";
        
        Connection connection= null;
        PreparedStatement statement=null;
        
        try{
           connection=ConnectionFactory.getConnection();
           statement = connection.prepareStatement(sql);// um objeto para ajudar a preparar o comando sql q vai ser usado no bd.
            statement.setInt(1, taskId);
            statement.execute();
            
      
        }catch(Exception e){
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
        
        List<Task>tasks = new ArrayList<Task>();
        
        
        try{
         connection = ConnectionFactory.getConnection();
         statement=connection.prepareStatement(sql);
         statement.setInt(1, idProject);
         resultSet = statement.executeQuery();      //devolve um resultset, que é o valor de resposta da consulta no bd.
           
         while(resultSet.next()){
             Task task= new Task();
            
              task.setId(resultSet.getInt("id"));
              task.setIdProject(resultSet.getInt("idProject"));
              task.setName(resultSet.getString("name"));
              task.setDescription(resultSet.getString("description"));
              task.setNotes(resultSet.getString("notes"));
              task.setIsCompleted(resultSet.getBoolean("completed"));
              task.setDeadline(resultSet.getDate("deadline"));
              task.getCreatedAt(resultSet.getDate("createdAt"));
               
               
               
               
         }
        }catch {
            
        }
                return null;

    }

}
