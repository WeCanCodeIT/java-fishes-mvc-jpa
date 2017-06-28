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
}
