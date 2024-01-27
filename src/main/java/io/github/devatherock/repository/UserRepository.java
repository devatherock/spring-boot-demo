package io.github.devatherock.repository;

import io.github.devatherock.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, RevisionRepository<User, Integer, Integer> {
}