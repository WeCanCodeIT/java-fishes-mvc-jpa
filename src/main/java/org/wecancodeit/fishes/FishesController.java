package org.wecancodeit.fishes;

import static java.util.Collections.emptySet;

import java.util.Collections;
import java.util.HashSet;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FishesController {

	@Resource
	private FishRepository fishRepo;
	
	@Resource
	private GenusRepository genusRepo;
	
	@Resource
	private FoodRepository foodRepo;
	
	@RequestMapping("/")
	public String showHomepage() {
		return "home";
	}

	@RequestMapping("/genera")
	public String fetchGenera(Model model) {
		model.addAttribute("genera", genusRepo.findAll());
		return "generaList";
	}
	
	@RequestMapping("/genus")
	public String fetchGenus(@RequestParam("id") int id, Model model) {
		model.addAttribute(genusRepo.findOne(id));
		return "genusDetail";
	}
	
	@RequestMapping("/fishes")
	public String fetchFishes(Model model) {
		model.addAttribute("fishes", fishRepo.findAll());
		return "fishesList";
	}
	
	@RequestMapping("/fish")
	public String fetchFishDetail(@RequestParam("id") long id, Model model) {
		Fish selectedFish = fishRepo.findOne(id);
		// same as model.addAttribute("fish", selectedFish)
		model.addAttribute(selectedFish);
		return "fishDetail";
	}
	
	@RequestMapping("/addSpecies")
	public String addSpecies(@RequestParam("genusId") int id, @RequestParam("name") String newSpeciesName) {
		
		Genus selected = genusRepo.findOne(id);
		Fish newSpecies = new Fish(selected, newSpeciesName, new HashSet<>(Collections.singleton(foodRepo.findByName("flake"))));
		fishRepo.save(newSpecies);
		
		return "redirect:/genus?id=" + id;
	}
	
	@RequestMapping("/species/delete")
	public String deleteSpecies(@RequestParam("speciesId") long speciesId) {
		
		Fish toDelete = fishRepo.findOne(speciesId);
		int genusId = toDelete.getGenus().getId();
		
		fishRepo.delete(toDelete);
		
		return "redirect:/genus?id=" + genusId;
	}
	
	@RequestMapping("/foods")
	public String showFoods(Model model) {
		model.addAttribute("foods", foodRepo.findAll());
		return "foods";
	}
	
	@RequestMapping("/foods/delete/{id}")
	public String deleteFood(@PathVariable long id) {
		Food toDelete = foodRepo.findOne(id);
		for(Fish fish: toDelete.getFishes()) {
			fish.remove(toDelete);
			fishRepo.save(fish);
		}
		foodRepo.delete(toDelete);
		return "redirect:/foods";
	}
	
}
