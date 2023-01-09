/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import Util.ConnectionFactory;
import java.sql.Date;

/**
 *
 * @author yuri-
 */
public class ProjectControlle {
    
    
    public void save(Project project) {
         String sql = "INSERT INTO projects (name, "
                + "description, "
                + "createAt, "
                + "updateAt)"
                + "VALUES (?, ?, ?,?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);      
            
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreadtedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdateAt().getTime()));
            
            statement.execute();
            
        }catch (SQLException ex) {
            throw new RuntimeException("Project saving error" + ex.getMessage(), ex);             
        }finally {
            ConnectionFactory.closeConnection(connection, statement);            
        } 
        
    }
    
    public void update(Project project) {
        String sql = "UPDATE projects SET "                
                + "name = ?, "
                + "description = ?, "              
                + "createAt = ?, "
                + "updateAt = ? "
                + "WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());          
            statement.setDate(3, new Date(project.getCreadtedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdateAt().getTime()));
            statement.setInt(5,project.getId());
            
            statement.execute();
            
        }catch (SQLException ex) {
            throw new RuntimeException("Task updating error" + ex.getMessage(), ex);             
        }finally {
            ConnectionFactory.closeConnection(connection, statement);            
        }         
    }
    
    public void removeById(int idProject) {
        String sql = "DELETE FROM projects WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
                
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idProject);   
            statement.execute();
            
        }catch (SQLException ex) {
            throw new RuntimeException("Project deletion error" + ex.getMessage(), ex);             
        }finally {
            ConnectionFactory.closeConnection(connection, statement);
        }        
    }    
    
    public List<Project> getAll() { // sem filtro, busca todos os projetos do bd.
       String sql = "SELECT * FROM projects";
       
       List<Project> projects = new ArrayList<Project>();
       
       Connection connection = null;
       PreparedStatement statement = null;
       ResultSet resultSet = null;

       try {
           connection = ConnectionFactory.getConnection();
           statement = connection.prepareStatement(sql);
           resultSet = statement.executeQuery();

           while(resultSet.next()) {
               
               Project project = new Project();
               
               project.setId(resultSet.getInt("idprojeto"));
               project.setName(resultSet.getString("name"));
               project.setDescription(resultSet.getString("description"));        
               project.setCreadtedAt(resultSet.getDate("createAt"));
               project.setUpdateAt(resultSet.getDate("updateAt"));
               
               projects.add(project);
           }

       }catch (SQLException ex) {
           throw new RuntimeException("GetAll projects error" + ex.getMessage(), ex);             
       }finally {
           ConnectionFactory.closeConnection(connection, statement, resultSet);
       }        

       return projects;
    }
    
    public Project getProjectByName(String name) {
       String sql = "SELECT * FROM projects WHERE NAME = ?";  

       Connection connection = null;
       PreparedStatement statement = null;
       ResultSet resultSet = null;            
       

       try {
           connection = ConnectionFactory.getConnection();
           statement = connection.prepareStatement(sql);
           statement.setString(1, name);
           resultSet = statement.executeQuery();
           
           Project project = new Project();
           resultSet.next();
           project.setId(resultSet.getInt("id"));
           project.setName(resultSet.getString("name"));
           project.setDescription(resultSet.getString("description"));        
           project.setCreadtedAt(resultSet.getDate("createdAt"));
           
           if (project.getUpdateAt() != null) {   
                project. setUpdateAt(resultSet.getDate("updatedAt")); 
            }
           return project;

       }catch (Exception ex) {
           throw new RuntimeException("GetAll projects error" + ex.getMessage(), ex);             
       }finally {
           ConnectionFactory.closeConnection(connection, statement, resultSet);
       }        

       
    }
    
}
