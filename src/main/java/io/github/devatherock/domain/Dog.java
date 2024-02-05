package io.github.devatherock.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "dogs")
@Getter
@Setter
public class Dog {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String color;
}
