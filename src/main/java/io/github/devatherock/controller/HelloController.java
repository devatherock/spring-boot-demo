package io.github.devatherock.controller;

import io.github.devatherock.domain.Animal;
import io.github.devatherock.domain.Dog;
import io.github.devatherock.domain.SuperDto;
import io.github.devatherock.service.TimeService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class HelloController {
    private final TimeService timeService;
    private final EntityManager entityManager;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello at " + timeService.getTime();
    }

    @GetMapping("/dogs/getNames")
    public List<Animal> getDogNames() {
        val query = entityManager.getCriteriaBuilder().createQuery(Animal.class);
        val queryRoot = query.from(Dog.class);

        return entityManager.createQuery(query.multiselect(queryRoot.get("name"))).getResultList();
    }

    @GetMapping("/dogs/getNamesQuery")
    public List<Animal> getDogNamesQuery() {
        return entityManager.createNativeQuery(
                "select name from dogs", "dog_names_result"
        ).getResultList();
    }
}
