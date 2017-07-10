package org.wecancodeit.fishes;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TagController {

	@Resource
	private FishRepository fishRepo;
	
	@RequestMapping("/food/cloud")
	public String showCloud(Model model) {
		
		Map<Food, Integer> foodCounts = buildCounts();	
		model.addAttribute("foods", foodCounts);
		
		int lowestCount = Integer.MAX_VALUE;
		int highestCount = 0;
		for(int count: foodCounts.values()) {
			if(count < lowestCount) {
				lowestCount = count;
			}
			if(count > highestCount) {
				highestCount = count;
			}
		}
		model.addAttribute("lowestCount", lowestCount);
		model.addAttribute("highestCount", highestCount);
		
		return "foodCloud";
	}

	private Map<Food, Integer> buildCounts() {
		// this will hold our counts
		Map<Food, Integer> foodCounts = new HashMap<>(); 
		
		// find all the fishes
		Iterable<Fish> fishes = fishRepo.findAll();
		// for each fish...
		for (Fish fish : fishes) {
			// iterate over its foods
			for (Food food: fish.getFoods()) {
				// if we already have a count in our map
				if(foodCounts.containsKey(food)) {
					// increase it by one
					int count = foodCounts.get(food);
					foodCounts.put(food, count + 1);
				} else { // otherwise
					foodCounts.put(food, 1); // add a count of one to the map
				}
			}
		}
		return foodCounts;
	}
}
