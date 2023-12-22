package com.app.FixIt.ENTITIES.Maintenance;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="Client")
public class Client extends User{
   

    public Client(){
        super();
    }
    
    private String num_compte;

    public String getNum_compte() {
        return num_compte;
    }

    public void setNum_compte(String num_compte) {
        this.num_compte = num_compte;
    } 
   
}
