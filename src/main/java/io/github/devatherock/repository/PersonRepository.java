package io.github.devatherock.repository;
import org.springframework.stereotype.Repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;

import io.github.devatherock.entities.Person;

@Repository
public interface PersonRepository extends CosmosRepository<Person, String> {}