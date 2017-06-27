package org.wecancodeit.fishes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Fish {

	@Id
	@GeneratedValue
	private Long id;

	private String genus;
	private String species;
	
	private boolean tropical;
	private boolean freshwater;

	private String image;
	
	public Long getId() {
		return id;
	}
	
	public String getGenus() {
		return genus;
	}
	public String getSpecies() {
		return species;
	}
	public boolean isTropical() {
		return tropical;
	}
	public boolean getFreshwater() {
		return freshwater;
	}
	
	public String getImage() {
		return image;
	}
	public boolean hasImage() {
		return image != null;
	}
	
	/**
	 * JPA needs a no argument constructor
	 */
	private Fish() {}
	
	public Fish(String genus, String species) {
		this(genus, species, true, true);
	}
	
	public Fish(String genus, String species, String image) {
		this(genus, species, true, true, image);
	}
	
	public Fish(String genus, String species, boolean tropical, boolean freshwater) {
		this.genus = genus;
		this.species = species;
		this.tropical = tropical;
		this.freshwater = freshwater;
	}
	
	public Fish(String genus, String species, boolean tropical, boolean freshwater, String image) {
		this(genus, species, tropical, freshwater);
		this.image = image;
	}

	public String buildBinomialName() {
		return genus + " " + species;
	}
}
