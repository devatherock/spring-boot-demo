package io.github.devatherock.repository;

import io.github.devatherock.domain.User;
import org.hibernate.envers.RevisionListener;

public class MyRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        User audit = (User) revisionEntity;
        audit.setUpdatedBy("web-app");
    }
 }