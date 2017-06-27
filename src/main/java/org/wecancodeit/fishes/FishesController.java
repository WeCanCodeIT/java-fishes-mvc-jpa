package org.wecancodeit.fishes;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FishesController {

	@Resource
	private FishesRepository repo;
	
	@RequestMapping("/")
	public String showHomepage() {
		return "home";
	}

	@RequestMapping("/fishes")
	public String fetchFishes(Model model) {
		model.addAttribute("fishes", repo.findAll());
		return "fishesList";
	}
	
	@RequestMapping("/fish")
	public String fetchFishDetail(@RequestParam("id") long id, Model model) {
		Fish selectedFish = repo.findOne(id);
		// same as model.addAttribute("fish", selectedFish)
		model.addAttribute(selectedFish);
		return "fishDetail";
	}
}
