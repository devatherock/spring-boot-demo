package io.github.devatherock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.github.devatherock.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	@Query(nativeQuery = true, value = 
			"select * from table_one a where a.uid IN :userIds and a.cdate = (select max(cdate) from table_one)")
	List<User> findByIdAndCreatedDate(List<String> userIds);
}
