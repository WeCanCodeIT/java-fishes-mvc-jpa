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

	@ManyToMany(mappedBy="foods")
	private Set<Fish> fishes;
	
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public Set<Fish> getFishes() {
		return fishes;
	}

	// for JPA
	private Food() {}
	
	public Food(String name) {
		this.name = name;
	}
}
