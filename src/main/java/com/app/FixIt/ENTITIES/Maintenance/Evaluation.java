package com.app.FixIt.ENTITIES.Maintenance;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToMany(cascade = CascadeType.ALL) 
    private List<Maintenancier> maintenanciers;
    @OneToMany
    private List<Questions> questions;
    private LocalDate date;
    private String domain;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDomain() {
        return domain;
    }
    public void setDomain(String domain) {
        this.domain = domain;
    }
    public List<Maintenancier> getMaintenanciers() {
        return maintenanciers;
    }
    public void setMaintenanciers(List<Maintenancier> maintenanciers) {
        this.maintenanciers = maintenanciers;
    }
    public List<Questions> getQuestions() {
        return questions;
    }
    public void setQuestions(List<Questions> questions) {
        this.questions = questions;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    
}
