
package io.github.devatherock.domain;

import io.github.devatherock.repository.MyRevisionListener;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

@Entity(name = "users")
@Getter
@Setter
//@Audited
@RevisionEntity(MyRevisionListener.class)
public class User extends DefaultRevisionEntity {
	private String name;

	private String updatedBy;
}