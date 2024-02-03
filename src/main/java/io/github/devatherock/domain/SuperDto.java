package io.github.devatherock.domain;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

@Entity
@NamedNativeQuery(
        name = "dog_names",
        query = "SELECT name from dogs",
        resultSetMapping = "SuperDto"
)
@SqlResultSetMapping(
        name = "SuperDto",
        classes = @ConstructorResult(
                targetClass = SuperDto.class,
                columns = {
                        @ColumnResult(name = "name")
                }
        )
)
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