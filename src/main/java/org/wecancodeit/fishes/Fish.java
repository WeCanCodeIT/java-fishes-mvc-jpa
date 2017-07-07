package org.wecancodeit.fishes;

import java.util.HashSet;
import java.util.Arrays;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Fish {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(optional=false)
	private Genus genus;
	private String species;
	
	/**
	 * The owner does NOT have the "mappedBy".
	 */
	@ManyToMany
	private Set<Food> foods;
	
	private boolean tropical;
	private boolean freshwater;

	private String image;
	
	public Long getId() {
		return id;
	}
	
	public Genus getGenus() {
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
	
	public Set<Food> getFoods() {
		return foods;
	}

	/**
	 * JPA needs a no argument constructor
	 */
	private Fish() {}
	
	public Fish(Genus genus, String species, Food... foods) {
		this(genus, species, true, true);
		this.foods = new HashSet<>(Arrays.asList(foods));
	}
	
	public Fish(Genus genus, String species, String image) {
		this(genus, species, true, true, image);
	}
	
	public Fish(Genus genus, String species, boolean tropical, boolean freshwater) {
		this.genus = genus;
		this.species = species;
		this.tropical = tropical;
		this.freshwater = freshwater;
	}
	
	public Fish(Genus genus, String species, boolean tropical, boolean freshwater, String image) {
		this(genus, species, tropical, freshwater);
		this.image = image;
	}

	public String buildBinomialName() {
		return genus.getName() + " " + species;
	}
	
	public void remove(Food food) {
		foods.remove(food);
	}
}
