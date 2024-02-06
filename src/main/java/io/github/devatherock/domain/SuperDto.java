package io.github.devatherock.domain;

import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Subselect("select * from dogs") // To prevent creation of a super_dto table
public class SuperDto {
    @Id
    private String id = "";

    private String name;

    public SuperDto()  {

    }

    public SuperDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}