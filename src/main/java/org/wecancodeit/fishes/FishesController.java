package org.wecancodeit.fishes;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FishesController {

	@Resource
	private FishesRepository fishRepo;
	
	@Resource
	private GenusRepository genusRepo;
	
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
		Fish newSpecies = new Fish(selected, newSpeciesName);
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
}
