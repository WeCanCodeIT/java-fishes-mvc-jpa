package org.wecancodeit.fishes;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Food {

	@Id
	@GeneratedValue
	private long id;

	private String name;

	/**
	 * "mappedBy" indicates what instance variable on the other class holds the
	 * other end of this relationship
	 */
	@ManyToMany(mappedBy = "foods")
	private Set<Fish> eatenBy;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Set<Fish> getEatenBy() {
		return eatenBy;
	}

	// for JPA
	private Food() {
	}

	public Food(String name) {
		this.name = name;
	}
}
