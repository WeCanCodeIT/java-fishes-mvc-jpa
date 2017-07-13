package org.wecancodeit.fishes;

import org.springframework.ui.Model;

public class TddFishesController {

	private FishRepository fishRepo;
	
	public String showAll(Model model) {
		Iterable<Fish> fishes = fishRepo.findAll();
		
		model.addAttribute("allFishes", fishes);
		
		return "allFishesTemplate";
	}

	public void showOne(long fishId, Model model) {
		model.addAttribute(fishRepo.findOne(fishId));
	}

}
