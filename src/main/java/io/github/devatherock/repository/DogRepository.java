package io.github.devatherock.repository;

import io.github.devatherock.domain.Dog;
import io.github.devatherock.domain.SuperDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DogRepository extends JpaRepository<Dog, Long> {
    /**
     * Requires the result class to have an {@code @Entity} annotation. Else there will be the below exception
     *
     * <code>
     *     org.springframework.data.mapping.MappingException:
     *     Couldn't find PersistentEntity for type class io.github.devatherock.domain.SuperDto
     * </code>
     *
     * Doesn't happen when querying directly using an {@code EntityManager}
     */
    @Query(name = "dog_names", nativeQuery = true)
    List<SuperDto> withNames();
}
