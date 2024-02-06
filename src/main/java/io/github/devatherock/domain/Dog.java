package io.github.devatherock.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;

@Entity(name = "dogs")
@Getter
@Setter
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "dog_names",
                query = "SELECT name from dogs",
                resultSetMapping = "SuperDto"
        ),
})
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "SuperDto",
                classes = @ConstructorResult(
                        targetClass = SuperDto.class,
                        columns = {
                                @ColumnResult(name = "name")
                        }
                )
        ),
        @SqlResultSetMapping(
                name = "dog_names_result",
                classes = @ConstructorResult(
                        targetClass = Animal.class,
                        columns = {
                                @ColumnResult(name = "name")
                        }
                )
        ),
})
public class Dog {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String color;
}
