package io.github.devatherock.repository;

import io.github.devatherock.domain.Dog;
import io.github.devatherock.domain.SuperDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DogRepository extends JpaRepository<Dog, Long> {
    @Query(name = "dog_names", nativeQuery = true)
    List<SuperDto> withNames();
}
