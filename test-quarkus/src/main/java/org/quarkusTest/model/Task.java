package org.quarkusTest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;


@Entity(name = "task")
@Table(name = "task")
@Data
public class Task extends PanacheEntityBase{
    
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long idTask;  

    @NotNull
    @Column(name="nameTask")  
    private String nameTask;  


}
