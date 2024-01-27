package io.github.devatherock.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.stream.Collectors;

@Slf4j
@Component
public class IdExposingRepositoryRestConfigurer implements RepositoryRestConfigurer {
    @Autowired
    private EntityManager entityManager;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(entityManager.getMetamodel().getEntities()
                .stream()
                .map(Type::getJavaType)
                .collect(Collectors.toList())
                .toArray(new Class[0]));
    }
}