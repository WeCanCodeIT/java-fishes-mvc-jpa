package org.wecancodeit.fishes;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Genus {

	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	private String description;
	
	@OneToMany(mappedBy="genus")
	private Set<Fish> fishes;
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public Set<Fish> getFishes() {
		return fishes;
	}
	
	// JPA needs this
	private Genus() {}
	
	public Genus(String name, String description) {
		this.name = name;
		this.description = description;
	}
}
