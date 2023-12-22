package com.app.FixIt.DTO.Maintenance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class ClientDTO {
    private String username;
    private String password;
    private String email;
    private Integer telephone ;


    public String getEmail() {
        return email;
    }
    public String getusername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setusername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
