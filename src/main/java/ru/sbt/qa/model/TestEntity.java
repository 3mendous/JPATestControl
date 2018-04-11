package ru.sbt.qa.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

//JPA entity definition
@Entity
//Table name
@Table(name = "test")
public class TestEntity {

    //Entity name
    @Id
    //Automatically generated getters and setters by project lombok
    @Getter
    @Setter
    //@Column(name = "name") not needed if variable name coincide with column name
    private String name;

    //Entity's owner
    @Getter
    @Setter
    private String owner;

    //Info about entity
    @Getter
    @Setter
    private String info;

    public TestEntity(String name, String owner, String info) {
        this.name = name;
        this.owner = owner;
        this.info = info;
    }

    //Default constructor is mandatory
    public TestEntity() {
    }
}
