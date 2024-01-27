
package io.github.devatherock.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

/**
 * The audits can be viewed using a query like below:
 *
 * select id, name, revtstmp as update_time,
 * CASE
 *     WHEN revtype = 0 THEN 'CREATE'
 *     WHEN revtype = 1 THEN 'UPDATE'
 *     ELSE 'DELETE'
 * END AS revision_type
 *     from public.users_aud aud, public.revinfo rev
 *     where aud.rev = rev.rev;
 */
@Entity(name = "users")
@Getter
@Setter
@Audited
public class User {
	@Id
	@GeneratedValue
	private int id;

	private String name;

	@Version
	private Long version;
}