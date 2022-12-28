/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author yuri-
 */
public class Project {
    private int id;
    private String name;
    private String description;
    private Date createAt ;        //createAt;
    private Date updateAt;

    public Project(int id, String name, String description, Date creadtedAt, Date updateAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createAt = creadtedAt;
        this.updateAt = updateAt;
    }
  
    
    public   Project(){
        
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreadtedAt() {
        return createAt;
    }

    public void setCreadtedAt(Date creadtedAt) {
        this.createAt = creadtedAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return this.name;
    }

   
    
    
}
